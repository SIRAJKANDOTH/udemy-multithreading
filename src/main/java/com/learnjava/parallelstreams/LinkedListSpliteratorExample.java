package com.learnjava.parallelstreams;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;

public class LinkedListSpliteratorExample {
    public List<Integer> multiplyEachValue(LinkedList<Integer> inputList, int multiplyValue, boolean isParallel) {
        startTimer();
        Stream<Integer> integerStream = inputList.stream();//this is seqential
        if(isParallel)
            integerStream.parallel();
        List<Integer> resultList = integerStream
                .map(integer -> integer * multiplyValue)
                .collect(Collectors.toList());
        timeTaken();
        stopWatchReset();
        return resultList;
    }
}
