package top.moma.example.apollo;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MapBean {

  private static final String CONFIG_MAP_KEY = "transfer.order.fund.type";

  /**
   * Apollo 配置
   *
   * <pre>
   *    {
   *      OwealthOkash:'{"fundType":1,"fundTypeDesc":"(AA)OWealth Transfers To Okash"}',
   *      OwealthEasemoni:'{"fundType":2,"fundTypeDesc":"(AA)OWealth Transfers To EaseMoni "}',
   *      OwealthMerchantLoan:'{"fundType":3,"fundTypeDesc":"(AA)OWealth Transfers To MerchantLoan"}',
   *      OkashOwealth:'{"fundType":4,"fundTypeDesc":"(AA)Okash Transfers To OWealth"}',
   *      OwealthSafebox:'{"fundType":5,"fundTypeDesc":"(AA)Owealth Transfers To SAFEBOX"}',
   *      Investment:'{"fundType":6,"fundTypeDesc":"(AC)BR Investment Transfers To Investment Banking"}',
   *      RM:'{"fundType":7,"fundTypeDesc":"(RM)Investment Banking Transfers To BR SAFEBOX"}',
   *      SafeboxOwealth:'{"fundType":8,"fundTypeDesc":"(AA)SAFEBOX Transfers To Owealth"}',
   *      OkashCollectfund:'{"fundType":9,"fundTypeDesc":"(AA)Okash Transfers To Collect Fund"}',
   *      CollectfundFlexFixed:'{"fundType":10,"fundTypeDesc":"(AA)Collect Fund Transfers To FlexFixed Commission"}'
   *    }
   * </pre>
   */
  @Value("#{${" + CONFIG_MAP_KEY + ":{}}}")
  private LinkedHashMap<String, String> configMap;
  /** Fund Type 枚举 */
  private LinkedHashMap<String, TransferOrderFundType> fundTypeMap;

  @PostConstruct
  public void initMap() {
    fundTypeMap = new LinkedHashMap<>();
    if (Objects.nonNull(configMap)) {
      configMap.forEach(
          (key, value) -> {
            TransferOrderFundType fundType = JSON.parseObject(value, TransferOrderFundType.class);
            fundTypeMap.put(key.toLowerCase(Locale.ROOT), fundType);
          });
    }
  }

  @ApolloConfigChangeListener(value = "application", interestedKeys = CONFIG_MAP_KEY)
  //@SuppressWarnings("unchecked")
  private void configMapChanged(ConfigChangeEvent configChangeEvent) {
    for (String changeKey : configChangeEvent.changedKeys()) {
      if (changeKey.equals(CONFIG_MAP_KEY)) {
        log.info(
            "TransferOrderFundTypeConfig, ApolloConfigChangeListener, old:{}",
            JSON.toJSONString(fundTypeMap));

        //        try {
        //
        // this.getClass().getDeclaredField("configMap").set(this,configChangeEvent.getChange(CONFIG_MAP_KEY));
        //        } catch (IllegalAccessException e) {
        //          e.printStackTrace();
        //        } catch (NoSuchFieldException e) {
        //          e.printStackTrace();
        //        }
        String newV = configChangeEvent.getChange(CONFIG_MAP_KEY).getNewValue();
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(newV);
        configMap =  exp.getValue(LinkedHashMap.class);
        fundTypeMap = new LinkedHashMap<>();
        if (Objects.nonNull(configMap)) {
          configMap.forEach(
              (key, value) -> {
                TransferOrderFundType fundType =
                    JSON.parseObject(value, TransferOrderFundType.class);
                fundTypeMap.put(key.toLowerCase(Locale.ROOT), fundType);
              });
        }
        log.info(
            "TransferOrderFundTypeConfig, ApolloConfigChangeListener, new:{}",
            JSON.toJSONString(fundTypeMap));
      }
    }
  }

  @Data
  @AllArgsConstructor
  public static class TransferOrderFundType {
    private Integer fundType;
    private String fundTypeDesc;
  }
}
