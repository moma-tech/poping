package top.moma.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MomaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MomaApplication.class, args);
  }
}
