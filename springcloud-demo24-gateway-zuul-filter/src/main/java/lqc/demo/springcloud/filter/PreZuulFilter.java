package lqc.demo.springcloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

// 更复杂的过滤器看zuul的核心包， DebugFilter附近的Filter
public class PreZuulFilter extends ZuulFilter{

	private static final Logger LOGGER = LoggerFactory.getLogger(PreZuulFilter.class);
	
	// filter具体逻辑
	@Override
	public Object run() throws ZuulException {
		// 获得servlet的request（zuul -> microservice的request）
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String uri = request.getRequestURI();
		// 请求的uri：/microservices-provider-user/user/id/3
		PreZuulFilter.LOGGER.info("请求的uri：{}", uri);
		
		return null;
	}

	/*
	 *  是否使用过滤器,为false就不执行过滤器
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	// 过滤器的执行顺序，数字越大越靠后
	@Override
	public int filterOrder() {
		return 1;
	}

	// 过滤器类型
	@Override
	public String filterType() {
		return "pre";
	}

}
