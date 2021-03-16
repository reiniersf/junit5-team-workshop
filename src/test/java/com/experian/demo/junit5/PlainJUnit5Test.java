package com.experian.demo.junit5;

import static com.experian.demo.junit5.domain.shape2d.ShapeSpec.fromSpecs;
import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.experian.demo.junit5.domain.GeometricMaker;
import com.experian.demo.junit5.domain.Shape;
import com.experian.demo.junit5.domain.shape2d.Circle;
import com.experian.demo.junit5.domain.shape2d.Rectangle;
import com.experian.demo.junit5.domain.shape2d.ShapeSpec;
import com.experian.demo.junit5.domain.shape2d.Triangle;
import com.experian.demo.junit5.infrastructure.Shape2DMaker;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1 ✔ Brief about JUnit
 * 2 ✔ A Plain JUnit 5 test story
 *   ✔ The simplest test ever (Basic flow, Better naming test)
 *   ✔ Asserting news (all, throws or not throws)
 *   ✔ Repeating and repeating...
 *   ✔ Parametrizing (same structure, different data)
 *   ✔ Nesting relatives
 * 3 - Extension model
 */
@DisplayName("A Plain JUnit 5 test story:")
class PlainJUnit5Test {

  private static final Logger logger = LoggerFactory.getLogger(PlainJUnit5Test.class);

  @Test
  @DisplayName("The simplest test ever")
  void shouldIntroduceItself() {
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    //WHEN
    String yourself = maker.introduceYourself();
    //THEN
    assertEquals("I'm the 2D shape maker...indeed there is One greater than me!", yourself);
  }

  @Nested
  @DisplayName("About throwing exceptions:")
  class AboutThrowingException {
    @Test
    @DisplayName("The new \"throw exception\" flow")
    void shouldThrowExceptionWhenEmptySpecIsProvided() {
      //GIVEN
      GeometricMaker maker = new Shape2DMaker();
      //THEN
      assertThrows(IllegalArgumentException.class,
          () -> maker.createFromSpec(ShapeSpec.EMPTY_SPEC));
    }

    @Test
    @DisplayName("The new \"no exception thrown\" flow")
    void shouldNotThrowExceptionWhenAValidSpecIsProvided() {
      //GIVEN
      GeometricMaker maker = new Shape2DMaker();
      //THEN
      assertDoesNotThrow(() -> maker.createFromSpec(fromSpecs(Map.of("radio", 2.3))));
    }
  }

  @RepeatedTest(value = 3,  name = "The fix of a bug...trying with: "
      + RepeatedTest.CURRENT_REPETITION_PLACEHOLDER)
  @DisplayName("Fix a bug in the specs:")
  void shouldNotFailWithAnInteger(RepetitionInfo repetitionInfo) {
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    int value = repetitionInfo.getCurrentRepetition();
    //WHEN
    Shape shape = maker.createFromSpec(fromSpecs(Map.of("radio", value)));
    //THEN
    assertAll(
        () -> assertTrue(shape instanceof Circle),
        () -> {
          double area = PI * pow(value, 2);
          logger.info("Expected area value is: {}", area);
          assertEquals(area, shape.area());
        },
        () -> assertThat(shape.describeIt()).contains("radio=" + value + ".0")
    );
  }

  @MethodSource("shapeCases")
  @ParameterizedTest(name = "[{index}] Shape with [{0}]")
  @DisplayName("Check correct creation of shapes:")
  void shouldCreateAndCheckAllSpecsArePresentInTheShape(ShapeSpec shapeSpec,
      Consumer<Shape> shapeAssertions) {
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    //WHEN
    Shape shape = maker.createFromSpec(shapeSpec);
    //THEN
    assertThat(shape).satisfies(shapeAssertions);
  }

  static Stream<Object[]> shapeCases() {
    Consumer<Shape> circleAssertions = shape -> {
      assertTrue(shape instanceof Circle);
      assertEquals(12.566370614359172, shape.area());
      assertThat(shape.describeIt()).contains("radio=2.0");
    };

    Consumer<Shape> triangleAssertions = shape -> {
      assertTrue(shape instanceof Triangle);
      assertEquals(3.0, shape.area());
      assertThat(shape.describeIt()).contains("height=2.0", "base=3.0");
    };

    Consumer<Shape> rectangleAssertions = shape -> {
      assertTrue(shape instanceof Rectangle);
      assertEquals(6.0, shape.area());
      assertThat(shape.describeIt()).contains("height=2.0", "length=3.0");
    };

    return Stream.of(
        new Object[]{fromSpecs(Map.of("radio", 2.0)), circleAssertions},
        new Object[]{fromSpecs(Map.of("height", 2.0, "base", 3.0)), triangleAssertions},
        new Object[]{fromSpecs(Map.of("height", 2.0, "length", 3.0)), rectangleAssertions}
    );
  }

}
