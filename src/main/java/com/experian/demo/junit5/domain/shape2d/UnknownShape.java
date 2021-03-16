package com.experian.demo.junit5.domain.shape2d;

import com.experian.demo.junit5.domain.Shape;

public class UnknownShape implements Shape {

  private final ShapeSpec unknownSpecs;

  public UnknownShape(ShapeSpec unknownSpecs) {
    this.unknownSpecs = unknownSpecs;
  }

  @Override
  public String describeIt() {
    return "UnknownShape: "+ unknownSpecs;
  }

  @Override
  public Double area() {
    return -1d;
  }
}
