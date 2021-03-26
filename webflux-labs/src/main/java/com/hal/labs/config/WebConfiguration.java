package com.hal.labs.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hal.labs.common.Constants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @Author: Steven HUANG
 * @Date: 2019/11/4
 */
@Configuration
@EnableWebFlux
public class WebConfiguration implements WebFluxConfigurer {

  @Value("${server.time-zone}")
  private String jacksonTimeZone;

  @Override
  public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    ObjectMapper objectMapper = this.objectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    ServerCodecConfigurer.ServerDefaultCodecs defaultCodecs = configurer.defaultCodecs();
    defaultCodecs.jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MimeTypeUtils.APPLICATION_JSON));
    defaultCodecs.jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MimeTypeUtils.APPLICATION_JSON));
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
    mapper.registerModule(new Jdk8Module());

    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDateTime.class,
      new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constants.yyyy_MM_ddHHmmss)));
    javaTimeModule.addSerializer(LocalDateTime.class,
      new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.yyyy_MM_ddHHmmss)));

    javaTimeModule.addDeserializer(LocalDate.class,
      new LocalDateDeserializer(DateTimeFormatter.ofPattern(Constants.yyyy_MM_dd)));
    javaTimeModule.addSerializer(LocalDate.class,
      new LocalDateSerializer(DateTimeFormatter.ofPattern(Constants.yyyy_MM_dd)));
    mapper.registerModule(javaTimeModule);

    mapper.setTimeZone(TimeZone.getTimeZone(jacksonTimeZone));
    mapper.setSerializationInclusion(Include.NON_NULL);

    return mapper;
  }


}
