package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FromPathToJavaFiles {
    public static ArrayList<String> fromPathToJavaPaths(String path){

        String[] typeOfFiles=new String[1];
        typeOfFiles[0]="java";
        File directory=new File(path);

        Collection<File> files= FileUtils.listFiles(directory,typeOfFiles,true);

        ArrayList<String> javaPaths=new ArrayList<>();
        for(File file:files){
            javaPaths.add(file.getAbsolutePath());
        }

        return javaPaths;
    }
}
