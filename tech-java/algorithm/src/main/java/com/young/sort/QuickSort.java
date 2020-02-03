package com.young.sort;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    int[] sample = new int[]{123, 55, 711,91, 22, 5000, 88, 99, 100};
    quickSort(sample, 0, sample.length - 1);
    System.out.println(Arrays.toString(sample));

  }

  private static void quickSort(int[] array, int start, int end) {
    if (start >= end) {
      return;
    }
    int splitIndex = split(array, start, end);
    quickSort(array, start, splitIndex-1);
    quickSort(array, splitIndex + 1, end);

  }

  private static int split(int[] array, int start, int end) {
    int i = start;
    int j = start;
    int splitNum = array[end];
    while (i < end) {
      if (array[i] < splitNum) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        j++;
      }
      i++;
    }
    //swap(array[end], array[j]);
    int temp = array[end];
    array[end] = array[j];
    array[j] = temp;
    return j;
  }


}
