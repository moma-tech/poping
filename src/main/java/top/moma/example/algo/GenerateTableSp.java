package top.moma.example.algo;

public class GenerateTableSp {

  public static void main(String[] args) {
    String sufx = "_";
    String orign = "opay_transaction_contacts.transaction_other_recent_topup_contacts";
    String sqlTem = "delete from %s where remarks ='DW';";
    //
    //    String template =
    //        "ALTER TABLE `opay_transaction_contacts`.`transaction_other_recent_topup_contacts_%s`
    // \n"
    //            +"DROP INDEX `idx_unique_user_topup`,\n"
    //            +"ADD COLUMN `type_mark` tinyint(1) NOT NULL DEFAULT 0 COMMENT
    // '保存类型标识;0-交易联系人，1-保存联系人' AFTER `remarks`,\n"
    //            +"ADD UNIQUE INDEX `idx_unique_user_topup`(`user_id` ASC, `topup_service_type`
    // ASC, `topup_service_provider` ASC, `topup_account_no` ASC, `type_mark` ASC) USING BTREE
    // COMMENT '其他充值缴费联系人唯一';";
    for (int i = 0; i < 100; i++) {
      // String sql = String.format(template, String.format("%04d", i));
      // System.out.println(sql);
      String ending = orign + sufx + String.format("%04d", i);
      System.out.printf(sqlTem, ending);
      System.out.println();
    }
  }
}
