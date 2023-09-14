package com.hackerrank.springboot.tests;

import java.util.List;

class Result {

    

    public static void plusMinus(List<Integer> arr) {
       int positive = 0;
       int negative = 0;
       int zero = 0;
       int arr_size = arr.size();
       for (int i = 0; i < arr_size; i++) {
    	   if (arr.get(i) > 0) positive++ ;
    	   else if (arr.get(i) < 0) negative++;
    	   else zero++;
       }
    }
}