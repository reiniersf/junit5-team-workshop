package com.experian.demo.junit5.domain.shape2d;

import com.experian.demo.junit5.domain.Shape;

public class Triangle implements Shape {
  private final Double base;
  private final Double height;

  public Triangle(Double base, Double height) {
    this.base = base;
    this.height = height;
  }

  @Override
  public String describeIt() {
    return this.toString();
  }

  @Override
  public Double area() {
    return (height *base)/2;
  }

  @Override
  public String toString() {
    return "Triangle{" +
        "base=" + base +
        ", height=" + height +
        '}';
  }
}
