package com.experian.demo.junit5;

import com.experian.demo.junit5.app.SimpleSpringApp;
import com.experian.demo.junit5.domain.GeometricMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SimpleSpringApp
public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);


  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
    GeometricMaker maker = applicationContext.getBean(GeometricMaker.class);
    logger.info(maker.introduceYourself());
  }
}
