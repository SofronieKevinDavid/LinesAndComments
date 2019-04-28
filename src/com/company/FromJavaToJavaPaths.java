package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FromJavaToJavaPaths {
    public static ArrayList<String> fromPathToJavaPaths(String path){
        String[] arrayy=new String[1];
        arrayy[0]="java";
        File directory=new File(path);
        Collection<File> files= FileUtils.listFiles(directory,arrayy,true);

        ArrayList<String> javaPaths=new ArrayList<>();
        for(File file:files){
            javaPaths.add(file.getAbsolutePath());
        }

        return javaPaths;
    }
}