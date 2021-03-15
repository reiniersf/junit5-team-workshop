package com.experian.demo.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.experian.demo.junit5.domain.GeometricMaker;
import com.experian.demo.junit5.infrastructure.Shape2DMaker;
import org.junit.jupiter.api.Test;

/**
 * 1 - Brief about JUnit
 * 2 - A Plain JUnit 5 test story
 *   - The simplest test ever (Basic flow, Better naming test)
 *   - Asserting news (all, throws or not throws)
 *   - Repeating and repeating...
 *   - Parametrizing (same structure, different data)
 *   - Nesting relatives
 * 3 - Extension model
 */
class PlainJUnit5Test {

  @Test
  void shouldIntroduceItself() {
    //GIVEN
    GeometricMaker maker = new Shape2DMaker();
    //WHEN
    String yourself = maker.introduceYourself();
    //THEN
    assertEquals(yourself, "I'm the 2D shape maker...indeed there is One greater than me!");
  }


}
