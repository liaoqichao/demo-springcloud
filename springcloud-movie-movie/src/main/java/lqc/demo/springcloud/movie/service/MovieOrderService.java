package lqc.demo.springcloud.movie.service;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import lqc.demo.springcloud.cinema.bean.Cinema;
import lqc.demo.springcloud.movie.bean.Movie;
import lqc.demo.springcloud.movie.bean.MovieOrder;
import lqc.demo.springcloud.movie.mapper.MovieMapper;
import lqc.demo.springcloud.movie.mapper.MovieOrderMapper;
import lqc.demo.springcloud.user.bean.User;

@Service
public class MovieOrderService {

	@Resource
	private AsyncRestTemplate asyncRestTemplate;
	
	@Resource
	private MovieOrderMapper movieOrderMapper;
	
	@Resource
	private MovieMapper movieMapper;
	
	public MovieOrder save(int updateRows, MovieOrder movieOrder) {
		// 1. 修改库存
		if(updateRows > 0) {
			// 2. 生成订单
			// 返回主键自增长，要save的实体类调用movieOrder.getId()返回， save返回的值是修改记录数
			movieOrderMapper.save(movieOrder);
			return movieOrderMapper.findById(movieOrder.getId());
		}
		return null;
	}

	@Transactional
	public MovieOrder buy(MovieOrder movieOrder) throws InterruptedException, ExecutionException {
		String zuulApi = "http://zuul/api";
		
		String userUrlPath = "/user/id/"+movieOrder.getUserId();
		String userUrl = zuulApi + userUrlPath;
		ListenableFuture<ResponseEntity<User>> userFutrue = asyncRestTemplate.getForEntity(userUrl, User.class);
		
		
		String cinemaUrlPath = "/cinema/cinema/id/"+movieOrder.getCinemaId(); // 第一个cinema是zuul配置的path，第二个是cinemaController的路径
		String cinemaUrl = zuulApi + cinemaUrlPath;
		ListenableFuture<ResponseEntity<Cinema>> cinemaFutrue = asyncRestTemplate.getForEntity(cinemaUrl, Cinema.class);
		
//		userFutrue.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
//
//			@Override
//			public void onSuccess(ResponseEntity<User> result) {
//				System.out.println("调用成功");
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				System.out.println("调用失败");
//				System.out.println(ex.getMessage());
//			}
//			
//		});
		User user = userFutrue.get().getBody(); // future.get()阻塞
		Cinema cinemal = cinemaFutrue.get().getBody();
		
		
		Movie movie = movieMapper.findById(movieOrder.getMovieId());
		
		movieOrder.setCinemaId(cinemal.getId());
		movieOrder.setUserId(user.getId());
		movieOrder.setMovieId(movie.getId());
		
		String stockUrl = "http://cinema/updateStock/"+movieOrder.getCinemaId()+"/"+movieOrder.getMovieId()+"/"+movieOrder.getNum();
		ListenableFuture<ResponseEntity<Integer>> updateRowsFuture = asyncRestTemplate.getForEntity(stockUrl, Integer.class);
		
		int updateRows = updateRowsFuture.get().getBody();
		
		return save(updateRows,movieOrder);
	}
}
