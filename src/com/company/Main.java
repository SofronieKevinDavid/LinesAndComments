package com.company;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

import java.util.ArrayList;

public class Main {
    public static final String special="\\"+'"';
    public static void main(String[] args) {
        String one=args[0];
        firstMethod(one);
    }
    public static void firstMethod(String path){
        String[] arrayy=new String[1];
        arrayy[0]="java";
        File directory=new File(path);
        Collection<File> files= FileUtils.listFiles(directory,arrayy,true);

        for(File file:files){
            System.out.println(file.getName()+":");
            lastMethod(file.getAbsolutePath());
            System.out.println("/////////////////////////////////////////");
        }
    }
    public static void lastMethod(String path){
        int numberOfLines=0;
        int numberOfComments=0;
        try{
            ArrayList<String> arrayList=new ArrayList<>();

            try {

                BufferedReader bufferreader = new BufferedReader(new FileReader(path));

                String line;
                while ((line = bufferreader.readLine()) != null) {
                    arrayList.add(line);
                }
                bufferreader.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }


            Iterator<String> iterator=getTextBetweenQuotationsOut(getQuotesInQuotesOut(arrayList)).iterator();

            while(iterator.hasNext()) {
                String string = iterator.next();

                if (string.trim().startsWith("//")) {
                    numberOfComments++;
                } else {
                    if (string.trim().startsWith("/*") || string.trim().startsWith("/**")) {

                        int y = 1;
                        boolean b = false;
                        while (!b) {
                            if (iterator.hasNext()) {
                                y++;
                                String str = iterator.next();
                                if (str.contains("*/")) {
                                    b = true;
                                    if (str.trim().endsWith("*/")) {
                                        numberOfComments = numberOfComments + y;
                                    } else {
                                        numberOfComments = numberOfComments + y;
                                        numberOfLines++;
                                    }
                                }
                            }
                        }
                    } else {
                        if (string.trim().contains("/*") && string.trim().contains("*/")) {
                            numberOfLines++;
                            numberOfComments++;
                        } else {
                            if (string.trim().contains("/*") || string.trim().contains("/**")) {
                                numberOfLines++;
                                int number = 1;
                                boolean b = false;
                                while (b == false) {
                                    if (iterator.hasNext()) {
                                        number++;
                                        String str = iterator.next();
                                        if (str.contains("*/")) {
                                            b = true;
                                            if (str.trim().endsWith("*/")) {
                                                numberOfComments = numberOfComments + number;
                                            } else {
                                                numberOfComments = numberOfComments + number;
                                                numberOfLines++;
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (string.contains("//")) {
                                    numberOfComments++;
                                    numberOfLines++;
                                } else
                                    numberOfLines++;
                            }
                        }
                    }
                }
            }
            System.out.println("Total lines of comments: " + numberOfComments);
            System.out.println("Total lines of code: " + numberOfLines);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public static ArrayList<String> getQuotesInQuotesOut(ArrayList<String> array){
        ArrayList<String> last=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            if(array.get(i).contains(special)) {
                last.add(array.get(i).replace(special, ""));
            }else{
                last.add(array.get(i));
            }
        }

        return last;
    }

    public static ArrayList<String> getTextBetweenQuotationsOut(ArrayList<String> array){

        //for when there are quotation marks on the same line:
        for(int i=0;i<array.size();i++){

            String x=array.get(i).replaceAll("\\\".*?\\\"|\\'.*?\\'|`.*`", "");
            array.set(i,x);
        }

        //for when there are quotation marks on different lines:
        for(int i=0;i<array.size();i++){
            boolean b=false;
            if(array.get(i).contains("\"")){
                b=true;
                int x=array.get(i).indexOf("\"");
                String string=array.get(i).substring(x,(array.get(i).length()-0));
                array.set(i,array.get(i).replaceAll(string,""));
            }
            if(b){
                cod:
                {
                    int y=array.size()-1;
                    while(y!=i){
                        if (array.get(y).contains("\"")) {
                            int z = array.get(y).indexOf("\"");
                            String string2 = array.get(y).substring(0, (z + 1));
                            array.set(y, array.get(y).replaceAll(string2, ""));
                            int k=y-1;
                            while(k!=i){
                                array.set(k,"");
                                k--;
                            }
                            break cod;
                        }
                        y--;
                    }
                }
            }
        }

        return array;
    }
}