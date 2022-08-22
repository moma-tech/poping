package top.moma.example.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MapBean {

  /**
   * { "1": "Menor al nivel Primaria", "2": "Primaria", "3": "Secundaria", "4":
   * "Preparatoria/Bachillerato", "5": "Universidad", "6": "Especialización/Maestría/Doctorado" }
   */
  @Value("#{${user.profile.education:{'ZZ':'empty'}}}")
  private Map<String, String> education;

  private String findStringMapValueWithDefault(
      Map<String, String> bizMap, String key, String defValue) {
    if (!StringUtils.hasLength(key)) {
      return defValue;
    }
    return bizMap.entrySet().stream()
        .filter(edu -> key.equals(edu.getKey()))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse(key);
  }
}
