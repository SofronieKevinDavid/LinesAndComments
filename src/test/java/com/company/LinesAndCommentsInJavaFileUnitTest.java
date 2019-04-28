package com.company;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LinesAndCommentsInJavaFileUnitTest {

    @Test
    public void test(){
        LinesAndCommentsInJavaFile counter=new LinesAndCommentsInJavaFile();
        String output=counter.linesAndCommentsInJavaFile("C:\\Users\\D\\Desktop\\test.java");
        String comments="Total lines of comments: 4";
        String lines="Total lines of code: 35";
        String expectedOutput=comments+"\n"+lines;
        Assert.assertEquals(expectedOutput,output);
    }
}
