package top.moma.example.dynamicenum.utils;

import java.lang.reflect.Field;

public class ReflectHelper {
  /** Field Oprations */
  /**
   * get Field
   *
   * @param beanClass beanClass
   * @param fieldName fieldName
   * @return java.lang.reflect.Field
   * @author Created by ÍÍ
   * @since 2022/6/14 20:11
   */
  public static Field getField(final Class<?> beanClass, final String fieldName)
      throws NoSuchFieldException {
    Field field = null;
    Class<?> targetClass = beanClass;
    while (null != targetClass && null == targetClass) {
      try {
        field = targetClass.getDeclaredField(fieldName);
      } catch (NoSuchFieldException e) {
        targetClass = targetClass.getSuperclass();
      }
    }
    if (null == field) {
      throw new NoSuchFieldException(fieldName + " in class " + beanClass);
    }
    return field;
  }

  /**
   * get Field Value from bean
   *
   * @param bean bean
   * @param fieldName fieldName
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2022/6/14 20:18
   */
  public static Object getFieldValue(Object bean, String fieldName)
      throws NoSuchFieldException, IllegalAccessException {
    Field field = getField(bean.getClass(), fieldName);
    boolean accessible = field.isAccessible();
    Object value = null;
    field.setAccessible(true);
    try {
      value = field.get(bean);
    } finally {
      field.setAccessible(accessible);
    }
    return value;
  }

  /**
   * get Static Field Value from class
   *
   * @param beanClass beanClass
   * @param fieldName fieldName
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2022/6/14 20:36
   */
  public static Object getStaticFieldValue(final Class<?> beanClass, final String fieldName)
      throws NoSuchFieldException, IllegalAccessException {
    Field field = getField(beanClass, fieldName);
    boolean accessible = field.isAccessible();
    Object value = null;
    field.setAccessible(true);
    try {
      value = field.get(beanClass);
    } finally {
      field.setAccessible(accessible);
    }
    return value;
  }

  /**
   * set Field Value
   *
   * @param bean bean
   * @param fieldName fieldName
   * @param newValue newValue
   * @return void
   * @author Created by ivan
   * @since 2022/6/14 20:58
   */
  public static void setFieldValue(final Object bean, final String fieldName, Object newValue)
      throws NoSuchFieldException, IllegalAccessException {
    Field field = getField(bean.getClass(), fieldName);
    boolean accessible = field.isAccessible();
    field.setAccessible(true);
    try {
      field.set(bean, newValue);
    } finally {
      field.setAccessible(accessible);
    }
  }
  /**
   * description setStaticFieldValue
   *
   * @param beanClass beanClass
   * @param fieldName fieldName
   * @param newValue newValue
   * @return void
   * @author Created by ivan
   * @since 2022/6/14 21:04
   */
  public static void setStaticFieldValue(
      final Class<?> beanClass, final String fieldName, Object newValue)
      throws NoSuchFieldException, IllegalAccessException {
    Field field = getField(beanClass, fieldName);
    boolean accessible = field.isAccessible();
    field.setAccessible(true);
    try {
      field.set(beanClass, newValue);
    } finally {
      field.setAccessible(accessible);
    }
  }
}
