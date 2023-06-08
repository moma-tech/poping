package top.moma.example.annotations;

// @Component
public class SecondJob implements Job {
  @Override
  public String getName() {
    return "Second";
  }
}
