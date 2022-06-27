package top.moma.example.dynamicenum.enums;

public enum TestEnumTwo {
  NPAY(1, "npay"),
  GOOGLE(2, "google"),
  TWITTER(3, "twitter");

  int code;
  String msg;

  TestEnumTwo(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
