package com.company;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LinesAndCommentsInJavaFile {

    private static final String specialQuoteInQuote ="\\"+'"';
    private LinesAndComments linesAndComments;


    public LinesAndComments linesAndCommentsInJavaFile(String path){
        linesAndComments=new LinesAndComments();
        int numberOfLines=0;
        int numberOfComments=0;
        try{
            ArrayList<String> javaFileLineToString=new ArrayList<String>();

            try {

                BufferedReader javaFile = new BufferedReader(new FileReader(path));

                String line;
                while ((line = javaFile.readLine()) != null) {
                    javaFileLineToString.add(line);
                }
                javaFile.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }


            Iterator<String> iterator=getTextBetweenQuotationsOut(getQuotesInQuotesOut(javaFileLineToString)).iterator();

            while(iterator.hasNext()) {
                String line = iterator.next();

                if (line.trim().startsWith("//")) {
                    numberOfComments++;
                } else {
                    if (line.trim().startsWith("/*") || line.trim().startsWith("/**")) {

                        int plusNrOfComments = 1;
                        boolean ifContains = false;
                        while (!ifContains) {
                            if (iterator.hasNext()) {
                                plusNrOfComments++;
                                String str = iterator.next();
                                if (str.contains("*/")) {
                                    ifContains = true;
                                    if (str.trim().endsWith("*/")) {
                                        numberOfComments = numberOfComments + plusNrOfComments;
                                    } else {
                                        numberOfComments = numberOfComments + plusNrOfComments;
                                        numberOfLines++;
                                    }
                                }
                            }
                        }
                    } else {
                        if (line.trim().contains("/*") && line.trim().contains("*/")) {
                            numberOfLines++;
                            numberOfComments++;
                        } else {
                            if (line.trim().contains("/*") || line.trim().contains("/**")) {
                                numberOfLines++;
                                int plusNrOfComments = 1;
                                boolean ifContains = false;
                                while (!ifContains) {
                                    if (iterator.hasNext()) {
                                        plusNrOfComments++;
                                        String str = iterator.next();
                                        if (str.contains("*/")) {
                                            ifContains = true;
                                            if (str.trim().endsWith("*/")) {
                                                numberOfComments = numberOfComments + plusNrOfComments;
                                            } else {
                                                numberOfComments = numberOfComments + plusNrOfComments;
                                                numberOfLines++;
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (line.contains("//")) {
                                    numberOfComments++;
                                    numberOfLines++;
                                } else
                                    numberOfLines++;
                            }
                        }
                    }
                }
            }
            linesAndComments.setLines(numberOfLines);
            linesAndComments.setComments(numberOfComments);
        }catch (Exception e){
            System.out.println("Error");
        }
        return linesAndComments;
    }

    private ArrayList<String> getQuotesInQuotesOut(ArrayList<String> array){

        ArrayList<String> codeWithoutQuotesInQuotes=new ArrayList<String>();

        for(int i=0;i<array.size();i++){
            if(array.get(i).contains(specialQuoteInQuote)) {
                codeWithoutQuotesInQuotes.add(array.get(i).replace(specialQuoteInQuote, ""));
            }else{
                codeWithoutQuotesInQuotes.add(array.get(i));
            }
        }

        return codeWithoutQuotesInQuotes;
    }

    private ArrayList<String> getTextBetweenQuotationsOut(ArrayList<String> array){

        //for when there are quotation marks on the same line:
        for(int i=0;i<array.size();i++){

            String withoutQuotes=array.get(i).replaceAll("\\\".*?\\\"|\\'.*?\\'|`.*`", "");
            array.set(i,withoutQuotes);
        }

        //for when there are quotation marks on different lines:
        for(int i=0;i<array.size();i++){
            boolean ifContains=false;
            if(array.get(i).contains("\"")){
                ifContains=true;
                int index=array.get(i).indexOf("\"");
                String toReplace=array.get(i).substring(index,(array.get(i).length()-0));
                array.set(i,array.get(i).replaceAll(toReplace,""));
            }
            if(ifContains){
                cod:
                {
                    int size=array.size()-1;
                    while(size!=i){
                        if (array.get(size).contains("\"")) {
                            int firstIndex = array.get(size).indexOf("\"");
                            String toReplace = array.get(size).substring(0, (firstIndex + 1));
                            array.set(size, array.get(size).replaceAll(toReplace, ""));
                            int fullLineInQuotes=size-1;
                            while(fullLineInQuotes!=i){
                                array.set(fullLineInQuotes,"");
                                fullLineInQuotes--;
                            }
                            break cod;
                        }
                        size--;
                    }
                }
            }
        }

        return array;
    }
}
