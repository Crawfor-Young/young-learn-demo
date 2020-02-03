package com.young.sort;

import java.util.Arrays;

public class MergeSort {

  /**
   * 第一种算法额外的空间没有及时释放，会被上层引用，增加了额外的内存开销
   *
   * @param args
   */

  public static void main(String[] args) {
    int[] sample = new int[]{123, 55,711, 22, 5000, 88, 99, 100};
    /*
    int[] result = mergeSort1(sample, 0, sample.length - 1);
    System.out.println(Arrays.toString(result));
    */
    mergeSort2(sample, 0, sample.length - 1);
    System.out.println(Arrays.toString(sample));


  }

  private static int[] mergeSort1(int[] array, int start, int end) {
    if (start >= end) {
      int[] res = new int[end - start + 1];
      System.arraycopy(array, start, res, 0, end - start + 1);
      return res;

    } else {
      return merge1(mergeSort1(array, start, (start + end) / 2),
          mergeSort1(array, (start + end) / 2 + 1, end));
    }

  }

  private static int[] merge1(int[] array1, int[] array2) {
    int pos1 = 0;
    int pos2 = 0;
    int posTemp = 0;
    int[] temp = new int[array1.length + array2.length];
    while (posTemp < temp.length) {
      if (pos2 >= array2.length && pos1 < array1.length) {
        System.arraycopy(array1, pos1, temp, posTemp, array1.length - pos1);
        return temp;
      }
      if (pos1 >= array1.length && pos2 < array2.length) {
        System.arraycopy(array2, pos2, temp, posTemp, array2.length - pos2);
        return temp;
      }
      if (array1[pos1] > array2[pos2]) {
        temp[posTemp] = array2[pos2];
        pos2++;
      } else {
        temp[posTemp] = array1[pos1];
        pos1++;
      }
      posTemp++;

    }
    return temp;
  }

  private static void mergeSort2(int[] array, int start, int end) {
    if (start >= end) {
      return;
    } else {
      mergeSort2(array, start, (start + end) / 2);
      mergeSort2(array, (start + end) / 2 + 1, end);
      merge2(array, start, end);
    }

  }

  private static void merge2(int[] array, int start, int end) {
    int pos1 = start;
    int mid = (start + end) / 2;
    int pos2 = mid + 1;
    int posTemp = 0;
    int[] temp = new int[end - start + 1];
    while (pos2 <= end && pos1 <= mid) {

      if (array[pos1] > array[pos2]) {
        temp[posTemp] = array[pos1];
        pos1++;
      } else {
        temp[posTemp] = array[pos2];
        pos2++;
      }
      posTemp++;
      if (pos1 > mid) {
        System.arraycopy(array, pos2, temp, posTemp, end - pos2 + 1);
        break;
      }
      if (pos2 > end) {
        System.arraycopy(array, pos1, temp, posTemp, mid - pos1 + 1);
        break;
      }
    }
    System.arraycopy(temp, 0, array, start, temp.length);


  }


}
