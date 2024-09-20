package top.moma.example.processes.entity;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransOrder
 *
 * <p>订单
 *
 * @version 1.0
 * @author Created by ivan at 18:12.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransOrder implements java.io.Serializable {
  @Serial private static final long serialVersionUID = -8887939807795995828L;

  /** opay交易单号 */
  private String transOrderNo;

  /** 国家编码 */
  private String country;

  /** 货币单位 */
  private String currency;

  /** 金额单位货币最小单位 */
  private Long amount;

  /** 手续费金额 */
  private Long feeAmount;

  /** 手续费类型 内扣外扣 */
  private String feePattern;

  /** 付款用户id */
  private String senderId;

  /** 收款方id */
  private String recipientId;
}
