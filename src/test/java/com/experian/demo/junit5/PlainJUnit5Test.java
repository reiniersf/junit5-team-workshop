package com.experian.demo.junit5;

import static com.experian.demo.junit5.domain.shape2d.ShapeSpec.fromSpecs;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.experian.demo.junit5.domain.GeometricMaker;
import com.experian.demo.junit5.domain.Shape;
import com.experian.demo.junit5.domain.shape2d.Circle;
import com.experian.demo.junit5.domain.shape2d.ShapeSpec;
import com.experian.demo.junit5.infrastructure.Shape2DMaker;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 1 ✔ Brief about JUnit
 * 2 ✔ A Plain JUnit 5 test story
 *   ✔ The simplest test ever (Basic flow, Better naming test)
 *   ✔ Asserting news (all, throws or not throws)
 *   - Repeating and repeating...
 *   - Parametrizing (same structure, different data)
 *   - Nesting relatives
 * 3 - Extension model
 */
@DisplayName("A Plain JUnit 5 test story:")
class PlainJUnit5Test {

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
  void shouldAllSpecsArePresentInTheShape() {
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


}
