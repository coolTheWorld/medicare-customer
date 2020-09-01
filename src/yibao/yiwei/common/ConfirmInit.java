package yibao.yiwei.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * initialize confirm value  "1" to "已确认"
 *
 * @author sunshy
 * @handler ConfirmAnnotationProcessor
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmInit {
    String value() default "已确认";
    String nullValue() default "";
}
