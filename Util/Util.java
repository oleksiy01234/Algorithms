package Util;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Util.Util.java contains common utility functions used by many other classes.
 */
public class Util {
  public static Random rand = new Random();
  public static Scanner scan = new Scanner(System.in);

  public static int[] constructArray(int size, int min, int max) {
    int[] array = new int[size];

    for (int i = 0; i < array.length; i++) {
      array[i] = rand.nextInt(max - min) + min;
    }

    return array;
  }

  public static int[][] constructArray2D(int size, int max) {
    int[][] array = new int[size][size];

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = rand.nextInt(max);
      }
    }

    return array;
  }

  public static int constructInt(int max) {
    return (int) (Math.random() * max);
  }

  public static int constructInt(int min, int max) {
    int range = (max - min) + 1;
    return (int) (Math.random() * range) + min;
  }

  public static List<Integer> constructLLPalindrome(int length, int max) {
    List<Integer> ll = new LinkedList<>();
    for (int i = 0; i < length; i++) {
      ll.add(i);
      ll.add(0, i);
    }
    return ll;
  }

  public static Scanner getUserInput(String message) {
    System.out.print(message);
    return scan;
  }

  public static void print(int[][] array) {
    System.out.println();
    for (int[] row : array) {
      System.out.print('[');
      for (int n : row) {
        if (n == Integer.MAX_VALUE) {
          System.out.print("\t" + DecimalFormatSymbols.getInstance().getInfinity());
        } else if (n == Integer.MIN_VALUE) {
          System.out.print("\t-" + DecimalFormatSymbols.getInstance().getInfinity());
        } else {
          System.out.print("\t" + n);
        }
      }
      System.out.println(']');
    }
  }

  public static <T> void print(T[] a) {
    System.out.println();
    int count = 0;
    for (T i : a) {
      System.out.print(++count + ": ");
      System.out.println(i);
    }
    System.out.println();
  }

  public static int min(int first, int second) {
    return first < second ? first : second;
  }

  public static int getMax(int a[]) {
    int max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
    }
    return max;
  }

  public static int flip(int i) {
    return i == 1 ? 0 : 1;
  }

  public static void reverse(int[] array) {
    for (int i = 0; i < array.length / 2; i++) {
      int temp = array[i];
      array[i] = array[array.length - 1 - i];
      array[array.length - 1 - i] = temp;
    }
  }

  public static void reverse(int[] array, int start, int end) {
    while (start < end) {
      int temp = array[start];
      array[start] = array[end];
      array[end] = temp;
      start++;
      end--;
    }
  }

  public static void reverse(char[] array, int start, int end) {
    while (start < end) {
      char temp = array[start];
      array[start] = array[end];
      array[end] = temp;
      start++;
      end--;
    }
  }

  public static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
  }

  public static String sort(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

  public static int max(int n1, int n2, int n3) {
    return Math.max(n1, Math.max(n2, n2));
  }

  public static int min(int n1, int n2, int n3) {
    return Math.min(n1, Math.min(n2, n2));
  }

  public static void swap(int[] a, int i1, int i2) {
    if (a[i1] == a[i2]) {
      return;
    }

    int temp = a[i1];
    a[i1] = a[i2];
    a[i2] = temp;
  }

  public static void swap(char[] a, int i1, int i2) {
    if (a[i1] == a[i2]) {
      return;
    }

    char temp = a[i1];
    a[i1] = a[i2];
    a[i2] = temp;
  }

  public static List<Character> toList(char[] a) {
    List<Character> list = new ArrayList<>();

    for (char n : a) {
      list.add(n);
    }

    return list;
  }

  public static Map<Character, Integer> fillMap(String s) {
    Map<Character, Integer> m = new HashMap<>();

    for (char c : s.toCharArray()) {
      m.put(c, m.getOrDefault(c, 0) + 1);
    }

    return m;
  }

  public static Map<Integer, Integer> fillMap(int[] a) {
    Map<Integer, Integer> m = new HashMap<>();

    for (int n : a) {
      m.put(n, m.getOrDefault(n, 0) + 1);
    }

    return m;
  }
}