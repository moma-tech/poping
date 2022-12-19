package top.moma.example.jsontest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

public class JsonRead {
  public static void main(String[] args) {
    JSONParser parser = new JSONParser();
    try {
      Object obj = parser.parse(new FileReader("src/movies.json"));
      JSONObject jsonObject = (JSONObject) obj;
      JSONArray upcoming = (JSONArray) jsonObject.get("Upcoming");

      System.out.println("Upcoming Movies:");
      Iterator upcomingIterator = upcoming.iterator();
      while (upcomingIterator.hasNext()) {
        String title = (String) ((HashMap) upcomingIterator.next()).get("title");
        System.out.println("title: " + title);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
