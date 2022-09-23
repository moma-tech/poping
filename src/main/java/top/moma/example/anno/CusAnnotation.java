package top.moma.example.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface CusAnnotation {
  String name() default "123";
}
