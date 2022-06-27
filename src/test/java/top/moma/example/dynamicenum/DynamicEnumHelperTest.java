package top.moma.example.dynamicenum;

import org.junit.jupiter.api.Test;
import top.moma.example.dynamicenum.enums.TestEnumOne;

import java.lang.reflect.InvocationTargetException;

class DynamicEnumHelperTest {
  @Test
  public void testAddEnumOne() {
    Class<?>[] enumParameterTypes = {String.class,int.class,int.class, String.class};
    Object[] newEnumValues = {"OTHER",4,10, "abcdefg"};

    DynamicEnumHelper<TestEnumOne> dynamicEnumHelper = new DynamicEnumHelper<>();
    try {
      dynamicEnumHelper.addValue(TestEnumOne.class, enumParameterTypes, newEnumValues);
      System.out.println(TestEnumOne.getEnumByCode(10));
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}
