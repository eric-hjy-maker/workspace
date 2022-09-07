package com.spring;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface ComponentSacn {

    String value();
}
