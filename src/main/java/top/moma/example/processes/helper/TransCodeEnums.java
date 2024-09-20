package top.moma.example.processes.helper;

import lombok.Getter;
/*
 * TransCodeEnums
 *
 * <p>Description 交易码</p>
 *
 * @version 1.0
 * @author Created by Ivan at 2024/04/10
 */
@Getter
public enum TransCodeEnums {
  // common
  TRANS_SUCCESS("000000", 0, "Success Processed"),
  TRANS_ILLEGAL_PARAMETER("090000", 4001, "Illegal Parameter"),
  TRANS_REMOTE_SERVICE_FAILED("030000", 4000, "Remote Service Failed"),
  TRANS_SYSTEM_ERROR("010000", 4060, "System Unknown Error"),
  ;
  private final String code;
  private final Integer transCode;
  private final String msg;

  TransCodeEnums(String code, Integer transCode, String msg) {
    this.code = code;
    this.transCode = transCode;
    this.msg = msg;
  }
}
