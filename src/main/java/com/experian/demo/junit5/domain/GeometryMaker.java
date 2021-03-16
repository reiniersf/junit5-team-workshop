package com.experian.demo.junit5.domain;


import com.experian.demo.junit5.domain.shape2d.ShapeSpec;

public interface GeometryMaker {

  String introduceYourself();

  Shape createFromSpec(ShapeSpec emptySpec);
}
