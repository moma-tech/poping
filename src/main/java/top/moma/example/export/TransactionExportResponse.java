package top.moma.example.export;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionExportResponse {
  private String name;
  private String stmtDate;
  private String stmtStartDate;
  private String stmtEndDate;
  private List<Trans> transList;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Trans {
    private String date;
    private String accountNo;
    private String name;
    private String amount;
    private String type;
    private String channel;
  }
}
