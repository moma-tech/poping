package top.moma.example.dynamicenum;

import top.moma.example.dynamicenum.utils.ReflectHelper;

import java.util.Arrays;

public class DynamicEnumHelper<E extends Enum<E>> {

  public void addValue(Class<E> enumClass, Class<?>[] enumParameterTypes, Object[] newEnumValues)
      throws Throwable {
    E[] oldValues = (E[]) ReflectHelper.getStaticFieldValue(enumClass, "$VALUES");
    E[] newValues = Arrays.copyOf(oldValues, oldValues.length + 1);
    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
    E newValue =
        (E) ReflectHelper.invokeEnumConstructor(enumClass, enumParameterTypes, newEnumValues);
    newValues[newValues.length - 1] = newValue;
    ReflectHelper.setStaticFieldValue(enumClass, "$VALUES", newValues);
  }
}
