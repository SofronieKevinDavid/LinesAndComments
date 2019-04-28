package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String argument=args[0];

        FromPathToJavaFiles toJava=new FromPathToJavaFiles();
        ArrayList<String> javaFiles=toJava.fromPathToJavaPaths(argument);

        LinesAndCommentsInJavaFile counter=new LinesAndCommentsInJavaFile();
        for(int i=0;i<javaFiles.size();i++){
            System.out.println(counter.linesAndCommentsInJavaFile(javaFiles.get(i)));
        }
    }
}