package yibao.yiwei.common;

import yibao.yiwei.entity.DataConfirm;

import java.lang.reflect.Field;

/**
 * handler @confirmInit
 *
 * @author sunshy
 */
public class ConfirmAnnotationProcessor {
    public static void init(DataConfirm confirm) throws IllegalAccessException {
        Class<?> clazz = confirm.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(ConfirmInit.class)) {
                continue;
            }
            ConfirmInit confirmInit = field.getAnnotation(ConfirmInit.class);
            if (field.get(confirm) != null && field.get(confirm).equals("1")) {
                field.set(confirm, confirmInit.value());
            } else if (field.getType().getSimpleName().equals("String")) {
                field.set(confirm, confirmInit.nullValue());
            }
        }
    }

    public static Boolean emptyOrNot(DataConfirm confirm,Class<?> confirmClazz) throws IllegalAccessException {
        Field[] fields = confirmClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(confirm) != null && field.get(confirm).equals("1")) {
                return true;
            }
        }
        return false;
    }
}
