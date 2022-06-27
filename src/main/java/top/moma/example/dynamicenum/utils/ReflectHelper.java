package top.moma.example.dynamicenum.utils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;

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
    while (null != targetClass && null == field) {
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
    Field modifier = Field.class.getDeclaredField("modifiers");
    modifier.setAccessible(true);
    modifier.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    field.setAccessible(true);
    try {
      field.set(beanClass, newValue);
    } finally {
      field.setAccessible(accessible);
    }
  }

  /** Method */
  /**
   * getMethod
   *
   * @param beanClass beanClass
   * @param methodName methodName
   * @param parameterTypes parameterTypes
   * @return java.lang.reflect.Method
   * @author Created by ivan
   * @since 2022/6/15 10:43
   */
  public static Method getMethod(
      final Class<?> beanClass, final String methodName, final Class<?>... parameterTypes)
      throws NoSuchMethodException {
    Method method = null;
    Class<?> targetClass = beanClass;
    while (null != targetClass && null == method) {
      try {
        method = targetClass.getDeclaredMethod(methodName, parameterTypes);
      } catch (NoSuchMethodException e) {
        targetClass = targetClass.getSuperclass();
      }
    }
    if (null == method) {
      throw new NoSuchMethodException();
    }
    return method;
  }
  /**
   * invokeMethod
   *
   * @param bean bean
   * @param methodName methodName
   * @param values values
   * @param parameterTypes parameterTypes
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2022/6/15 10:43
   */
  public static Object invokeMethod(
      final Object bean,
      final String methodName,
      final Object[] values,
      final Class<?>... parameterTypes)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = getMethod(bean.getClass(), methodName, parameterTypes);
    boolean accessible = method.isAccessible();
    Object value = null;
    method.setAccessible(true);
    try {
      value = method.invoke(bean, values);
    } finally {
      method.setAccessible(accessible);
    }
    return value;
  }

  /** Constructor */
  /**
   * getConstructor
   *
   * @param beanClass beanClass
   * @param parameterTypes parameterTypes
   * @return java.lang.reflect.Constructor<?>
   * @author Created by ivan
   * @since 2022/6/15 10:43
   */
  public static Constructor<?> getConstructor(
      final Class<?> beanClass, final Class<?>... parameterTypes) throws NoSuchMethodException {
    Constructor[] constructors = beanClass.getDeclaredConstructors();
    return beanClass.getDeclaredConstructor(parameterTypes);
  }
  /**
   * invokeConstructor
   *
   * @param beanClass beanClass
   * @param parameterTypes parameterTypes
   * @param values values
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2022/6/15 10:43
   */
  public static Object invokeConstructor(
      final Class<?> beanClass, final Class<?>[] parameterTypes, final Object[] values)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException,
          IllegalAccessException {
    Constructor<?> constructor = getConstructor(beanClass, parameterTypes);
    boolean accessible = constructor.isAccessible();
    Object instance = null;
    constructor.setAccessible(true);
    try {
      instance = constructor.newInstance(values);
    } finally {
      constructor.setAccessible(accessible);
    }
    return instance;
  }

  @SuppressWarnings("restriction")
  public static Object invokeEnumConstructor(
      final Class<?> beanClass, final Class<?>[] parameterTypes, final Object[] values)
      throws Throwable {
    Constructor<?> constructor = getConstructor(beanClass, parameterTypes);
    boolean accessible = constructor.isAccessible();
    Object value = null;

    constructor.setAccessible(true);
    try {
      MethodHandle handle = MethodHandles.lookup().unreflectConstructor(constructor);
      value = handle.invokeWithArguments(values);
    } finally {
      constructor.setAccessible(accessible);
    }

    return value;
  }
}
