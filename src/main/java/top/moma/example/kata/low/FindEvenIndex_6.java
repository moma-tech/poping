package top.moma.example.kata.low;

import java.util.Arrays;

public class FindEvenIndex_6 {
  public static int findEvenIndex(int[] arr) {
    // your code
    int head = 0;
    int tail = 0;
    int index = -1;
    int sum = Arrays.stream(arr).sum();
    if ((sum - arr[0]) == 0) {
      return 0;
    }
    for (int i = 0; i < arr.length - 1; i++) {
      head = head + arr[i];
      tail = sum - head - arr[i + 1];
      if (head == tail) {
        index = i + 1;
        break;
      }
    }
    return index;
  }

  public static int findEvenIndex2(int[] arr) {
    int left = 0;
    int right = Arrays.stream(arr).sum();
    for (int i = 0; i < arr.length; i++) {
      right -= arr[i];
      if (left == right) return i;
      left += arr[i];
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] input = new int[] {2824, 1774, -1490, -9084, -9696, 23094};
    System.out.println(FindEvenIndex_6.findEvenIndex(input));
  }
}
