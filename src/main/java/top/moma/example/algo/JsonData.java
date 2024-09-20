package top.moma.example.algo;

import java.util.List;

public class JsonData {

  public List<DataDTO> getData() {
    return data;
  }

  public void setData(List<DataDTO> data) {
    this.data = data;
  }

  private List<DataDTO> data;

  public static class DataDTO {

    public String getDisplayName() {
      return displayName;
    }

    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }

    public String getAltCode() {
      return altCode;
    }

    public void setAltCode(String altCode) {
      this.altCode = altCode;
    }

    public String getShortCode() {
      return shortCode;
    }

    public void setShortCode(String shortCode) {
      this.shortCode = shortCode;
    }

    public String getOpayCode() {
      return opayCode;
    }

    public void setOpayCode(String opayCode) {
      this.opayCode = opayCode;
    }

    private String opayCode;
    private String displayName;
    private String altCode;
    private String shortCode;

    @Override
    public String toString() {
      return "DataDTO{"
          + "displayName='"
          + displayName
          + '\''
          + ", altCode='"
          + altCode
          + '\''
          + ", shortCode='"
          + shortCode
          + '\''
          + '}';
    }
  }
}
