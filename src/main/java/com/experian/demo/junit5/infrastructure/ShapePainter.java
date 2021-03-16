package com.experian.demo.junit5.infrastructure;

import com.experian.demo.junit5.domain.GeometryPainter;
import java.awt.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapePainter implements GeometryPainter {

  private static final Logger logger = LoggerFactory.getLogger(ShapePainter.class);

  @Override
  public void paintShape(Color color) {
    logger.info("Painting with color: {}", color);
  }
}
