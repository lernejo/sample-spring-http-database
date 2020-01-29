package com.github.lernejo.sample.spring.httpdatabase.adapters.http;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfiguration implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    WebConfiguration() {
        this.objectMapper = new ObjectMapper()
            .findAndRegisterModules().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream()
            .filter(c -> c instanceof MappingJackson2HttpMessageConverter)
            .map(MappingJackson2HttpMessageConverter.class::cast)
            .forEach(c -> c.setObjectMapper(objectMapper));
    }
}
