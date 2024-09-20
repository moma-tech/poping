package top.moma.example.algo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Create {

  public static void main(String[] args) {
    String sufx = "_";
    String orign = "opay_transaction_card.card_transaction_record";
    String sqlTem = "create table %s like card_transaction_record;";
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
//    for (int i = 0; i < 100; i++) {
//      // String sql = String.format(template, String.format("%04d", i));
//      // System.out.println(sql);
//      String ending = orign + sufx + String.format("%04d", i);
//      System.out.printf(sqlTem, ending, ending, String.format("%02d", i));
//      System.out.println();
//    }
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDateTime startTime = localDateTime.minusYears(2);
    LocalDateTime endTime = localDateTime;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy");
    String startYear = dateTimeFormatter.format(startTime.withNano(0));
    String endYear = dateTimeFormatter.format(endTime.withNano(0));
    int start = Integer.parseInt(startYear);
    int end = Integer.parseInt(endYear);
    StringBuilder stringBuilder = new StringBuilder(30);
    for (int i = start; i <= end; ++i) {
      for (int j = 1; j <= 12; ++j) {
        for (int n = 0; n < 3; n++) {
          String table = String.format("%s_%s%02d_%s", orign, i, j, n);
          System.out.printf(sqlTem,table);
          System.out.println();
        }
      }
    }
  }
}
