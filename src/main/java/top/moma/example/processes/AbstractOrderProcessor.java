package top.moma.example.processes;

import top.moma.example.processes.entity.ExtraInfo;
import top.moma.example.processes.entity.TransOrder;

/**
 * AbstractOrderProcessor
 *
 * <p>订单处理器 - 责任链
 *
 * @version 1.0
 * @author Created by ivan at 11:43.
 */
public abstract class AbstractOrderProcessor {
  /** 后续处理器 */
  protected AbstractOrderProcessor next;

  /**
   * setNextProcessor
   *
   * @param next next
   * @author Created by ivan
   * @since 2024/1/11 11:44
   */
  public void setNextProcessor(AbstractOrderProcessor next) {
    this.next = next;
  }

  /**
   * process
   *
   * <p>抽象处理方法
   *
   * <p>返回结果码
   *
   * @return int
   * @author Created by ivan
   * @since 2024/1/11 11:44
   */
  public abstract Integer process(TransOrder transOrder, ExtraInfo extraInfo);
}
