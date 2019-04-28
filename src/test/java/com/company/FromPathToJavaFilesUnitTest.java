package com.company;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class FromPathToJavaFilesUnitTest {

    @Test
    public void test(){
        FromPathToJavaFiles javaFiles=new FromPathToJavaFiles();
        ArrayList<String> output=javaFiles.fromPathToJavaPaths("C:\\Users\\D\\Desktop");
        ArrayList<String> expectedOutput=new ArrayList<>();
        expectedOutput.add("C:\\Users\\D\\Desktop\\test.java");
        Assert.assertEquals(expectedOutput,output);
    }
}
