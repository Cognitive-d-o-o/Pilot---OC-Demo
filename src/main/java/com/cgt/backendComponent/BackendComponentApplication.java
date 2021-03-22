package com.cgt.backendComponent;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendComponentApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(BackendComponentApplication.class).properties(getProperties());
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendComponentApplication.class, args);
	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.additional-location", "/config/custom/application.properties");
		return props;
	}
}
