package top.moma.example.processes.processors;

import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import top.moma.example.processes.AbstractOrderProcessor;
import top.moma.example.processes.entity.ExtraInfo;
import top.moma.example.processes.entity.TransOrder;
import top.moma.example.processes.helper.TransCodeEnums;

/**
 * RequestParamsProcessor
 *
 * <p>Description 请求参数处理器</p>
 *
 * @version 1.0
 * @author Created by Ivan at 2024/04/10
 */
@Slf4j
@Component
public class RequestParamsProcessor extends AbstractOrderProcessor {
  
  /**
   * 处理交易订单和额外信息，返回处理结果
   *
   * @param transOrder 交易订单
   * @param extraInfo 额外信息
   * @return 处理结果，如果订单参数非法，则返回TransCodeEnums.TRANS_ILLEGAL_PARAMETER.getTransCode()；否则返回null
   */
  @Override
  public Integer process(TransOrder transOrder, ExtraInfo extraInfo) {
    // 判断订单参数是否合法
    if(invalidOrderParams(transOrder)){
      // 参数不合法，返回非法参数错误码
      return TransCodeEnums.TRANS_ILLEGAL_PARAMETER.getTransCode();
    }
    // 参数合法，继续下一个处理
    if(Objects.nonNull(next)){
        // 调用下一个处理器的process方法
        next.process(transOrder, extraInfo);
    }
    // 参数合法，处理完成
    return TransCodeEnums.TRANS_SUCCESS.getTransCode();
  }

  /**
   * 判断交易订单参数是否无效
   *
   * @param transOrder 交易订单
   * @return 如果参数无效，返回true；否则返回false
   */
  private boolean invalidOrderParams(TransOrder transOrder) {
    if (Objects.isNull(transOrder)) {
      // 对象空
      log.error("RequestParamsProcessor,invalidOrderParams, transOrder is null");
      return true;
    }
    if (!StringUtils.hasText(transOrder.getSenderId())) {
      // 支付方空
      log.error("RequestParamsProcessor,invalidOrderParams, transOrder sender null");
      return true;
    }
    if (!StringUtils.hasText(transOrder.getRecipientId())) {
      // 支付方空
      log.error("RequestParamsProcessor,invalidOrderParams, transOrder recipient null");
      return true;
    }
    if (Objects.isNull(transOrder.getAmount()) || transOrder.getAmount() <= 0) {
      // 交易金额<= 0
      log.error("RequestParamsProcessor,invalidOrderParams, transOrder.getAmount is null or <=0");
      return true;
    }
    // 参数有效
    return false;
  }
}
