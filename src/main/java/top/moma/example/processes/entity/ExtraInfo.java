package top.moma.example.processes.entity;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ExtraInfo
 *
 * <p>附加信息
 *
 * @version 1.0
 * @author Created by ivan at 18:12.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtraInfo implements java.io.Serializable {
  @Serial private static final long serialVersionUID = -4997478817205648664L;

  /** 渠道 */
  String channel;

  /** 消息 */
  String message;

  /** 指定渠道的订单号 */
  String referNo;
}
