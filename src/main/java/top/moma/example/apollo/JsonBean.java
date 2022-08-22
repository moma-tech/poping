package top.moma.example.apollo;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import top.moma.example.utils.spring.ContextHelper;

import java.util.List;

public class JsonBean {

  @Value("${profileEnum}")
  private String jsonConfig;

  public UserProfileDict getProfileDict() {
    return JSON.parseObject(jsonConfig, UserProfileDict.class);
  }

  @Data
  public class UserProfileDict {
    /**
     * { "education":[{"code":"1","value":"Menor al nivel Primaria"}],
     * "marital":[{"code":"1","value":"Soltero / Soltera"}], "job":[{"code":"1","value":"Empleado
     * tiempo completo"}], "industrial":[{"code":"1","value":"Agricultura"}],
     * "relationship":[{"code":"1","value":"Padre / Madre"}],
     * "monthly_income":[{"code":"1","value":"0～3,000MXN"}] }
     */
    /*
    教育
     */
    private List<EducationDTO> education;
    /*
    婚姻
     */
    private List<MaritalDTO> marital;
    /*
    工作类型
     */
    private List<JobDTO> job;
    /*
    行业类型
     */
    private List<IndustrialDTO> industrial;
    /*
    亲属关系
     */
    private List<RelationshipDTO> relationship;
    /*
    月收入
     */
    private List<MonthlyIncomeDTO> monthlyIncome;

    @NoArgsConstructor
    @Data
    public class EducationDTO {
      private String code;
      private String value;
    }

    @NoArgsConstructor
    @Data
    public class MaritalDTO {
      private String code;
      private String value;
    }

    @NoArgsConstructor
    @Data
    public class JobDTO {
      private String code;
      private String value;
    }

    @NoArgsConstructor
    @Data
    public class IndustrialDTO {
      private String code;
      private String value;
    }

    @NoArgsConstructor
    @Data
    public class RelationshipDTO {
      private String code;
      private String value;
    }

    @NoArgsConstructor
    @Data
    public class MonthlyIncomeDTO {
      private String code;
      private String value;
    }
  }

  public static void main(String[] args) {
    JsonBean jsonBean = ContextHelper.getBeanByType(JsonBean.class);
    System.out.println(jsonBean.getProfileDict().getEducation().get(0));
  }
}
