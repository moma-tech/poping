package top.moma.example.dynamicenum.enums;

public enum TestEnumOne {
  PROCESSING(1, "待审核"),
  PASS(2, "已通过"),
  REJECT(3, "已拒绝");

  int code;
  String msg;

  TestEnumOne(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static TestEnumOne getEnumByCode(int code) {
    TestEnumOne[] var1 = values();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      TestEnumOne value = var1[var3];
      if (value.getCode() == code) {
        return value;
      }
    }

    return null;
  }

  public int getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.msg;
  }
}
