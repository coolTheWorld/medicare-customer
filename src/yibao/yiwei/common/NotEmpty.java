package yibao.yiwei.common;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunshy
 * @handler ConfirmAnnotationProcessor
 * judge value is null or not equals "1"
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NotEmpty {
}
