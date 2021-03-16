package com.experian.demo.junit5.domain;

import static com.experian.demo.junit5.domain.shape2d.ShapeSpec.AS_DOUBLE;

import com.experian.demo.junit5.domain.shape2d.Circle;
import com.experian.demo.junit5.domain.shape2d.Rectangle;
import com.experian.demo.junit5.domain.shape2d.ShapeSpec;
import com.experian.demo.junit5.domain.shape2d.Triangle;
import com.experian.demo.junit5.domain.shape2d.UnknownShape;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
public final class ShapeSelector {

  private ShapeSelector() {
  }
  private static final String HEIGHT_FIELD = "height";
  private static final Predicate<ShapeSpec> IS_A_CIRCLE_SPEC = shapeSpec -> shapeSpec
      .containsField("radio");
  private static final Predicate<ShapeSpec> HAS_HEIGHT = shapeSpec -> shapeSpec
      .containsField(HEIGHT_FIELD);
  private static final Predicate<ShapeSpec> HAS_LENGTH = shapeSpec -> shapeSpec
      .containsField("length");
  private static final Predicate<ShapeSpec> HAS_BASE = shapeSpec -> shapeSpec.containsField("base");


  private static final Map<Predicate<ShapeSpec>, Function<ShapeSpec, Shape>> strategies = Map
      .ofEntries(
          new SimpleEntry<>(IS_A_CIRCLE_SPEC,
              shapeSpec -> new Circle(shapeSpec.extract("radio", AS_DOUBLE))),
          new SimpleEntry<>(HAS_HEIGHT.and(HAS_LENGTH),
              shapeSpec -> new Rectangle(
                  shapeSpec.extract(HEIGHT_FIELD, AS_DOUBLE),
                  shapeSpec.extract("length", AS_DOUBLE))),
          new SimpleEntry<>(HAS_HEIGHT.and(HAS_BASE),
              shapeSpec -> new Triangle(
                  shapeSpec.extract("base", AS_DOUBLE),
                  shapeSpec.extract(HEIGHT_FIELD, AS_DOUBLE)
              ))
      );

  public static Shape buildFormSpec(ShapeSpec spec) {
    return strategies.entrySet().stream()
        .filter(entry -> entry.getKey().test(spec))
        .findAny()
        .map(entry -> entry.getValue().apply(spec))
        .orElse(new UnknownShape(spec));
  }
}
