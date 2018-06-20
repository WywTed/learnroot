package com.huhusky.microservice.server.b.configure;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	/*@Bean
	public ServletRegistrationBean servletRegistrationBean() {  
		return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");// ServletName默认值为首字母小写，即myServlet1  
	}  */

	/*@Bean
	public HystrixMetricsStreamServlet hystrixMetricsStreamServlet(){
		return new HystrixMetricsStreamServlet();
	}

	@Bean
	public ServletRegistrationBean registration(HystrixMetricsStreamServlet servlet){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(servlet);
		registrationBean.setEnabled(true);//是否启用该registrationBean
		registrationBean.addUrlMappings("/hystrix.stream");
		return registrationBean;
	}*/

}
