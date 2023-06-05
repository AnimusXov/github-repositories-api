package com.example.githubrepos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
// Just for compatibility with Firefox and its Network.http.accept.default setting
@Configuration
public class WebConfig implements WebMvcConfigurer {

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	configurer.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
}

@Override
public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	converters.add(xmlConverter());
}

@Bean
public MappingJackson2XmlHttpMessageConverter xmlConverter() {
	MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
	converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_XML));
	return converter;
}
}
