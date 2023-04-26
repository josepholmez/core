package com.olmez.core.temp;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Temp {

  public static void main(String[] args) throws FileNotFoundException {

    int[] list = { 1, 2, 3, 4, 5, 6, 7, 8 };

    Arrays.sort(list);

    System.out.println("min:" + list[0] + " max:" + list[list.length - 1]);

  }

}
