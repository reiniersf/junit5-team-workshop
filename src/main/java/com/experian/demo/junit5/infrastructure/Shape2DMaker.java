package com.experian.demo.junit5.infrastructure;

import static com.experian.demo.junit5.domain.shape2d.ShapeSpec.EMPTY_SPEC;

import com.experian.demo.junit5.domain.GeometryMaker;
import com.experian.demo.junit5.domain.Shape;
import com.experian.demo.junit5.domain.ShapeSelector;
import com.experian.demo.junit5.domain.shape2d.ShapeSpec;
import org.springframework.stereotype.Component;

@Component
public class Shape2DMaker implements GeometryMaker {

  @Override
  public String introduceYourself() {
    return "I'm the 2D shape maker...indeed there is One greater than me!";
  }

  @Override
  public Shape createFromSpec(ShapeSpec shapeSpec) {
    if(shapeSpec.equals(EMPTY_SPEC))
      throw new IllegalArgumentException("Not Possible to create a shape without specs...");

    return ShapeSelector.buildFormSpec(shapeSpec);
  }

}
