package com.cgt.testComponent;

import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TestComponentApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(TestComponentApplication.class)
		.properties(getProperties())
		.registerShutdownHook(true)
		.run(args);
	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.additional-location", "/config/custom/application.properties");
		return props;
	}
}
