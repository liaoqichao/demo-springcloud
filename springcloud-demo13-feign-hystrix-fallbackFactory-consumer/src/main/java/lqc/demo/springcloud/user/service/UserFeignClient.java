package lqc.demo.springcloud.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.hystrix.FallbackFactory;
import lqc.demo.springcloud.user.bean.User;
import lqc.demo.springcloud.user.service.UserFeignClient.UserFeignClientFallbackFactory;

@FeignClient(name="microservices-provider-user"
// fallback和fallbackFactory有冲突，都配的话使用fallback
//, fallback=UserFeignClientFallback.class
, fallbackFactory=UserFeignClientFallbackFactory.class)
public interface UserFeignClient {

	@GetMapping("/user/id/{id}")
	public User findById(@PathVariable("id") long id);
	
	// 创建UserFeignClient的fallback工厂
	@Component
	static class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient>{
		
		private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientFallbackFactory.class);
		
		@Override
		public UserFeignClient create(Throwable cause) {
			// 打印日志， slf4j打印日志支持占位符
			LOGGER.info("fallback reason was : {}" , cause.getMessage()); 
			// 匿名类转换成lambda表达式
			return new UserFeignClientWithFactory() {

				@Override
				public User findById(long id) {
					System.out.println(this.getClass().getName());
					User user = new User();
					user.setId(-2L);
					user.setName("UserFeignClientFallbackFactory.create().UserFeignClientWithFactory.findById()");
					return user;
				}
				
			};
		}
	}
	
}

// 实现UserFeignClient里面的方法，这里实现的方法就是断路器打开时执行的方法
interface UserFeignClientWithFactory extends UserFeignClient{
	
}

