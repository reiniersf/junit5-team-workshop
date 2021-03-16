package com.experian.demo.junit5.domain.shape2d;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class ShapeSpec {

  private final Map<String, Object> specs;

  public static final ShapeSpec EMPTY_SPEC = new ShapeSpec(Collections.emptyMap());
  public static final Function<Object, Double> AS_DOUBLE = (Object o) -> Double.valueOf(o.toString());

  private ShapeSpec(Map<String, Object> specs) {
    this.specs = specs;
  }

  public static ShapeSpec fromSpecs(Map<String, Object> specs) {
    if (specs.isEmpty()) {
      return EMPTY_SPEC;
    } else {
      return new ShapeSpec(specs);
    }
  }

  public <T> T extract(String field, Function<Object, T> mapper) {
    return mapper.apply(specs.get(field));
  }

  public boolean containsField(String field) {
    return specs.containsKey(field);
  }

  @Override
  public String toString() {
    return "ShapeSpec{" +
        "specs=" + specs +
        '}';
  }
}
