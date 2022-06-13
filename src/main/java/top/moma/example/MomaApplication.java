package top.moma.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import top.moma.example.apollo.RemoteConfBean;
import top.moma.example.utils.spring.ContextHelper;

@SpringBootApplication
//@EnableApolloConfig
@EnableCaching
public class MomaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MomaApplication.class, args);
    RemoteConfBean remoteConfBean = ContextHelper.getBeanByType(RemoteConfBean.class);
    System.out.println(remoteConfBean.getDbName());
  }
}
