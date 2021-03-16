package com.experian.demo.junit5.domain.shape2d;

import com.experian.demo.junit5.domain.Shape;

public class Rectangle implements Shape {

  private final Double height;
  private final Double length;

  public Rectangle(Double height, Double length) {
    this.height = height;
    this.length = length;
  }

  @Override
  public String describeIt() {
    return this.toString();
  }

  @Override
  public Double area() {
    return height * length;
  }

  @Override
  public String toString() {
    return "Rectangle{" +
        "height=" + height +
        ", length=" + length +
        '}';
  }
}
