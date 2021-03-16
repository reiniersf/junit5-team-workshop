package com.experian.demo.junit5;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(PaintShapeWithColors.class)
public @interface ShapeColor {
    int R() default 255;
    int G() default 255;
    int B() default 255;
}
