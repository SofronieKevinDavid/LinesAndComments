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
        LinesAndComments output=counter.linesAndCommentsInJavaFile("C:\\Users\\D\\Desktop\\test.java");

        int comments=output.getComments();
        int lines=output.getLines();
        Assert.assertEquals(comments,4);
        Assert.assertEquals(lines,35);
    }
}
