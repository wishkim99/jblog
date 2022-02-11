package com.poscoict.jblog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class BootInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {//톰캣을 실행시키기 위해 사용
		// TODO Auto-generated method stub
		return builder.sources(MySiteApplication.class); //maven을 만들때 initializer가 추가된 war를 만듦
	}

}
