package com.young.sort;

public class InsertionSort {

  public static void main(String[] args) {
    int[] sample = new int[]{123, 55, 22, 5000, 88, 99, 100};
    insertionSort(sample);
    print(sample);
  }


  private static void insertionSort(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        int tmp = array[i + 1];
        int j = i;
        for (; j >= 0; j--) {  //单次交换的次数比冒泡少，选择k，冒泡3k
          if (array[j] > tmp) {
            array[j + 1] = array[j]; //从后往前比待插入数据大就往后挪，直到小于等于待插入数据时插入
          } else {
            break;
          }
        }
        array[j + 1] = tmp;
      }
    }
  }

  private static void print(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }

}
