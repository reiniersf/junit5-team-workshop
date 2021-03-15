package com.experian.demo.junit5.infrastructure;

import com.experian.demo.junit5.domain.GeometricMaker;
import org.springframework.stereotype.Component;

@Component
public class Shape2DMaker implements GeometricMaker {

  @Override
  public String introduceYourself() {
    return "I'm the 2D shape maker...indeed there is One greater than me!";
  }

}
