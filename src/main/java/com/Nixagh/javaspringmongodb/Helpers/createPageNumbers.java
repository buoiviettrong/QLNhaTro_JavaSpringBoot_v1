package com.Nixagh.javaspringmongodb.Helpers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class createPageNumbers {
  public static List<Integer> createPageNumbers(int getTotalPages) {
    if(getTotalPages > 0)
      return IntStream.rangeClosed(1, getTotalPages)
            .boxed()
            .collect(Collectors.toList());
    return null;
  }
}
