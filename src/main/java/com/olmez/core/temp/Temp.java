package com.olmez.core.temp;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Temp {

    public static void main(String[] args) throws InterruptedException {

        int arr[] = { 12, 11, 13, 5, 6, 7 };

        log.info("Sorted array: {}", Arrays.toString(arr));

    }

}
