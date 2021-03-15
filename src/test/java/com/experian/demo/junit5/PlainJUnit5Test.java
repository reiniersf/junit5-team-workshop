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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1 ✔ Brief about JUnit
 * 2 ✔ A Plain JUnit 5 test story
 *   ✔ The simplest test ever (Basic flow, Better naming test)
 *   ✔ Asserting news (all, throws or not throws)
 *   ✔ Repeating and repeating...
 *   - Parametrizing (same structure, different data)
 *   - Nesting relatives
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

  @Test
  @DisplayName("The new way of \"checking all conditions\" ")
  void shouldCheckAllSpecsArePresentInTheCircle() {
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    //WHEN
    Shape shape = maker.createFromSpec(fromSpecs(Map.of("radio", 2.0)));
    //THEN
    assertAll(
        () -> assertTrue(shape instanceof Circle),
        () -> assertEquals(12.566370614359172, shape.area()),
        () -> assertThat(shape.describeIt()).contains("radio=2.0")
    );
  }

  @RepeatedTest(value = 3, name = "The fix of a bug...trying with: "+ RepeatedTest.CURRENT_REPETITION_PLACEHOLDER)
  void shouldNotFailWithAnInteger(RepetitionInfo repetitionInfo){
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
        () -> assertThat(shape.describeIt()).contains("radio="+value+".0")
    );
  }

  @Test
  @DisplayName("Check all specs for a triangle shape")
  void shouldCheckAllSpecsArePresentInTheTriangle(){
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    //WHEN
    Shape shape = maker.createFromSpec(fromSpecs(Map.of("height", 2.0, "base", 3.0)));
    //THEN
    assertAll(
        () -> assertTrue(shape instanceof Triangle),
        () -> assertEquals(3.0, shape.area()),
        () -> assertThat(shape.describeIt()).contains("height=2.0", "base=3.0")
    );
  }

  @Test
  @DisplayName("Check all specs for a rectangle shape")
  void shouldCheckAllSpecsArePresentInTheRectangle(){
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    //WHEN
    Shape shape = maker.createFromSpec(fromSpecs(Map.of("height", 2.0, "length", 3.0)));
    //THEN
    assertAll(
        () -> assertTrue(shape instanceof Rectangle),
        () -> assertEquals(6.0, shape.area()),
        () -> assertThat(shape.describeIt()).contains("height=2.0", "length=3.0")
    );
  }


}
