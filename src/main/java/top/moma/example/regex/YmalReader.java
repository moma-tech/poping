package top.moma.example.regex;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;

public class YmalReader {
  private Map ymalData;

  public YmalReader(InputStream inputStream) {
    Yaml yaml = new Yaml(new SafeConstructor());
    ymalData = yaml.load(inputStream);
  }

  public YmalReader(Reader io) {
    Yaml yaml = new Yaml(new SafeConstructor());
    ymalData = yaml.load(io);
  }

  public String readRules() {
    if (Objects.nonNull(ymalData)) {
      return (String) ymalData.get("exclude-names-regex");
    }
    return "";
  }
}
