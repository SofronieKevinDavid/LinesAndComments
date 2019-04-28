package com.company;

import java.util.ArrayList;
import static com.company.FromJavaPathToOutput.fromJavaPathToOutput;
import static com.company.FromJavaToJavaPaths.fromPathToJavaPaths;

public class Main {

    public static void main(String[] args) {
        String argument=args[0];
        ArrayList<String> javaFiles=fromPathToJavaPaths(argument);
        for(int i=0;i<javaFiles.size();i++){
            fromJavaPathToOutput(javaFiles.get(i));
        }
    }


}