package com.young.search;

public class BinarySearch {

  public static void main(String[] args) {

    int[] array = new int[]{22, 55, 88, 99, 100, 123, 711, 5000};
    System.out.println(search1(array,711,0,array.length-1));

  }

  /*
     递归
   */
  private static int search1(int[] array, int value, int start, int end) {
    if(start>=end){
      return 0;
    }
    int index = (start + end) / 2;
    if (array[index] == value) {
      return index;
    } else if (value < array[index]) {
      return search1(array, value, start, index - 1);
    } else {
      return search1(array, value, index + 1, end);
    }
  }
}
