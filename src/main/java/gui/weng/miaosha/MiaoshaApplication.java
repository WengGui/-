package gui.weng.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@SpringBootApplication
//@MapperScan("gui.weng.miaosha.dao")
//public class MiaoshaApplication extends SpringBootServletInitializer{
//
//	public static void main(String[] args) {
//		SpringApplication.run(MiaoshaApplication.class, args);
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(MiaoshaApplication.class);
//	}
//}

@SpringBootApplication
//@MapperScan("gui.weng.miaosha.dao")
public class MiaoshaApplication{

	public static void main(String[] args) {
		SpringApplication.run(MiaoshaApplication.class, args);
	}
}

