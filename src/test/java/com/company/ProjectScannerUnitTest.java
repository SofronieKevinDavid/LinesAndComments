package com.company;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ProjectScannerUnitTest {

    @Test
    public void test(){
        ProjectScanner javaFiles=new ProjectScanner("C:\\Users\\D\\Desktop");
        ArrayList<LinesAndComments> output=javaFiles.scan();
        int comments=output.get(0).getComments();
        int lines=output.get(0).getLines();


        Assert.assertEquals(comments,4);
        Assert.assertEquals(lines,35);
    }
}
