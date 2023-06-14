package top.moma.example.infrastructure.common.constants;

/**
 * GeneralConstants
 *
 * @version 1.0
 * @author Created by ivan at 17:18.
 */
public class GeneralConstants {

  /** 逻辑删除 */
  public static final int DB_DATA_DELETED = 1;
  /** 逻辑有效 */
  public static final int DB_DATA_NOT_DELETED = 0;

  private GeneralConstants() {
    throw new IllegalStateException("Constant class");
  }
}
