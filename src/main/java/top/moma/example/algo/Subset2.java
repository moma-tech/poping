package top.moma.example.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subset2
 *
 * <p>Leecode 90
 *
 * <p>Back Tracking
 *
 * @version 1.0
 * @author Created by ivan at 11:19.
 */
public class Subset2 {

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    subset(0, nums, new ArrayList<>(), result);
    return result;
  }

  public void subset(int i, int[] nums, List<Integer> subset, List<List<Integer>> result) {
    if (i == nums.length) {
      result.add(List.copyOf(subset));
    } else {
      int pointer = nums[i];
      subset.add(pointer);
      subset(i + 1, nums, subset, result);
      subset.remove(subset.size() - 1);
      while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
        i = i + 1;
      }
      subset(i + 1, nums, subset, result);
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 2, 3};
    Subset2 s = new Subset2();
    List<List<Integer>> result = s.subsetsWithDup(nums);
    result.forEach(System.out::println);
  }
}
