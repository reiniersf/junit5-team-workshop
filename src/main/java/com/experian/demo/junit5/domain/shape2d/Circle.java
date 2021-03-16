package com.experian.demo.junit5.domain.shape2d;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import com.experian.demo.junit5.domain.Shape;

public class Circle implements Shape {

  private final Double radio;

  public Circle(Double radio) {
    this.radio = radio;
  }

  public String describeIt() {
    return this.toString();
  }

  @Override
  public Double area() {
    return PI * pow(radio, 2);
  }

  @Override
  public String toString() {
    return "Circle{" +
        "radio=" + radio +
        '}';
  }
}
