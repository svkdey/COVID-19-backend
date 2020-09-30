package com.example.Covid19.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BinaryOperator;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.BodyFilters;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.JsonHttpLogFormatter;
import org.zalando.logbook.Logbook;

@Configuration
public class LogBookConfiguration {
/*
	@Bean
	public Logbook logbook() {
//        BinaryOperator<String> filterSecretHeader = (s1, s2)-> s1.toLowerCase().equals("x-secret") ? "<secret>" : s2;
		Logbook logbook = Logbook.builder()
				.bodyFilter(BodyFilters.replaceJsonStringProperty(new HashSet<>(Arrays.asList("password")), "<secret>"))
				.formatter(new JsonHttpLogFormatter())
				.writer(new DefaultHttpLogWriter(LoggerFactory.getLogger(LogBookConfiguration.class),
						DefaultHttpLogWriter.Level.INFO))
				.build();
		return logbook;
	}
//                Logbook.builder().queryFilter(accessToken())
//                .queryFilter(replaceQuery("password", "<secret>"))
//                .headerFilter(authorization())
//                .headerFilter(eachHeader(filterSecretHeader))
//                .bodyFilter(
//                        BodyFilters.replaceJsonStringProperty(
//                                new HashSet<>(Arrays.asList("password")), "<secret>"))
//                .formatter(new JsonHttpLogFormatter())
//                .writer(new DefaultHttpLogWriter(
//                        LoggerFactory.getLogger(LogBookConfiguration.class),
//                        DefaultHttpLogWriter.Level.INFO))
//                .build();
*/
}
