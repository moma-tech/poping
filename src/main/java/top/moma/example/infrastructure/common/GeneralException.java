package top.moma.example.infrastructure.common;

public class GeneralException extends RuntimeException {

  public GeneralException() {}

  public GeneralException(String message) {
    super(message);
  }

  public GeneralException(String message, Throwable cause) {
    super(message, cause);
  }

  public GeneralException(Throwable cause) {
    super(cause);
  }

  public GeneralException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
