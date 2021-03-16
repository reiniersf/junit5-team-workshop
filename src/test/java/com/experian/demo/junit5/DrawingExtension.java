package com.experian.demo.junit5;

import com.experian.demo.junit5.domain.GeometryPainter;
import com.experian.demo.junit5.infrastructure.ShapePainter;
import java.awt.Color;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private static final Logger logger = LoggerFactory.getLogger(DrawingExtension.class);
  GeometryPainter painter = new ShapePainter();

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    if (AnnotationSupport
        .isAnnotated(context.getRequiredTestMethod(), PaintShapeWithColors.class)) {
      logger.info(context.getRequiredTestMethod().getName() + ": STARTED");
    }
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    if (AnnotationSupport
        .isAnnotated(context.getRequiredTestMethod(), PaintShapeWithColors.class)) {

      AnnotationSupport.findRepeatableAnnotations(context.getRequiredTestMethod(), ShapeColor.class)
          .forEach(a -> painter.paintShape(new Color(a.R(), a.G(), a.B())));
    }
  }
}
