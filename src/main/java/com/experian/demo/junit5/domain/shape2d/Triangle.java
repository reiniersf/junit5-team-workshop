package com.experian.demo.junit5.domain.shape2d;

import com.experian.demo.junit5.domain.Shape;

public class Triangle implements Shape {
  private final Double base;
  private final Double heightFromBase;

  public Triangle(Double base, Double heightFromBase) {
    this.base = base;
    this.heightFromBase = heightFromBase;
  }

  @Override
  public String describeIt() {
    return this.toString();
  }

  @Override
  public Double area() {
    return (heightFromBase*base)/2;
  }

  @Override
  public String toString() {
    return "Triangle{" +
        "base=" + base +
        ", height=" + heightFromBase +
        '}';
  }
}
