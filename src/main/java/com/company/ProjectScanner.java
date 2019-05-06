package com.company;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class ProjectScanner extends LinesAndCommentsInJavaFile {
    String path;

    public ProjectScanner(String path) {
        this.path=path;
    }

    public ArrayList<LinesAndComments> scan(){
        ArrayList<LinesAndComments> linesAndComments =new ArrayList<LinesAndComments>();

        String[] typeOfFiles=new String[1];
        typeOfFiles[0]="java";
        File directory=new File(path);

        Collection<File> files= FileUtils.listFiles(directory,typeOfFiles,true);

        ArrayList<String> javaPaths=new ArrayList<String>();
        for(File file:files){
            javaPaths.add(file.getAbsolutePath());
        }
        for(int i=0;i<javaPaths.size();i++){
            linesAndComments.add(linesAndCommentsInJavaFile(javaPaths.get(i)));
        }
        return linesAndComments;
    }
}
