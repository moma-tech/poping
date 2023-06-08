package top.moma.example.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
public class JobConfig {
  //@Bean
  public String jobList(List<Job> jobList) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Job job : jobList) {
      stringBuilder.append(job.getName() + "---");
    }
    return stringBuilder.toString();
  }
}
