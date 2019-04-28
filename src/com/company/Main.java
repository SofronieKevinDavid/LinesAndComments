package com.company;

import java.util.ArrayList;
import static com.company.FromJavaPathToOutput.fromJavaPathToOutput;
import static com.company.FromJavaToJavaPaths.fromPathToJavaPaths;

public class Main {

    public static void main(String[] args) {
        String one=args[0];
        ArrayList<String> arrayList=fromPathToJavaPaths(one);
        for(int i=0;i<arrayList.size();i++){
            fromJavaPathToOutput(arrayList.get(i));
        }
    }


}