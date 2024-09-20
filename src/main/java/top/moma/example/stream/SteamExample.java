package top.moma.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

public class SteamExample {

  public static void main(String[] args) {
    Person person1 = new Person("1", "a");
    Person person2 = new Person("2", "b");
    Person person3 = new Person("2", "c");
    List<Person> personList = new ArrayList<>();
    personList.add(person1);
    personList.add(person2);
    personList.add(person3);
    Map<String, String> map =
        personList.stream()
            .filter(p -> StringUtils.hasText(p.getPid()) && StringUtils.hasText(p.getName()))
            .collect(
                Collectors.toMap(
                    Person::getPid, Person::getName, (key1, key2) -> key1 + "||" + key2));
    for (String key : map.keySet()) {
      System.out.println(key + ": " + map.get(key));
    }
  }

  @Data
  @AllArgsConstructor
  public static class Person implements java.io.Serializable {
    private String pid;
    private String name;
  }
}
