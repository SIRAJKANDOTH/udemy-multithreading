package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

 class ParallelStreamExampleTest {
    ParallelStreamExample parallelStreamExample=new ParallelStreamExample();



    @Test
    void stringTransform() {
        List<String> inputList = DataSet.namesList();
        startTimer();
        List<String> resultList = parallelStreamExample.stringTransform(inputList);
        timeTaken();
        assertEquals(4,resultList.size());
        resultList.forEach(name->{
            assertTrue(name.contains("-"));
        });

    }


     @ParameterizedTest
     @ValueSource(booleans = {false,true})
     void stringTransform_1(boolean isParallel) {
         List<String> inputList = DataSet.namesList();
         startTimer();
         List<String> resultList = parallelStreamExample.stringTransform_1(inputList,isParallel);
         timeTaken();
         stopWatchReset();
         assertEquals(4,resultList.size());
         resultList.forEach(name->{
             assertTrue(name.contains("-"));
         });

     }
}