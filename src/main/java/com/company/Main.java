package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String path=args[0];

        ProjectScanner scanner=new ProjectScanner(path);
        ArrayList<LinesAndComments> javaFiles=scanner.scan();


        for(int i=0;i<javaFiles.size();i++){
            System.out.println("Lines Of Code:"+javaFiles.get(i).getLines());
            System.out.println("Lines Of Comments:"+javaFiles.get(i).getComments());
        }
    }
}