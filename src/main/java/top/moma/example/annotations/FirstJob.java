package top.moma.example.annotations;

// @Component
public class FirstJob implements Job {
  @Override
  public String getName() {
    return "First";
  }
}
