package top.moma.example.dynamicenum.enums;

public enum TestEnumTwo {
  NANOPAY(1, "nanopay"),
  GOOGLE(2, "google"),
  TWITTER(3, "twitter");

  int code;
  String msg;

  TestEnumTwo(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
