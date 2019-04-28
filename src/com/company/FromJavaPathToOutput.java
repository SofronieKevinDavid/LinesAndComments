package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class FromJavaPathToOutput {

    public static final String specialQuoteInQuote ="\\"+'"';

    public static void fromJavaPathToOutput(String path){
        int numberOfLines=0;
        int numberOfComments=0;
        try{
            ArrayList<String> javaFileLineToString=new ArrayList<>();

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
                                while (ifContains == false) {
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
            System.out.println("Total lines of comments: " + numberOfComments);
            System.out.println("Total lines of code: " + numberOfLines);

        }catch (Exception e){
            System.out.println("Error");
        }
    }
    //pana aici am schimbat numele sa fie explicite
    public static ArrayList<String> getQuotesInQuotesOut(ArrayList<String> array){
        ArrayList<String> last=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            if(array.get(i).contains(specialQuoteInQuote)) {
                last.add(array.get(i).replace(specialQuoteInQuote, ""));
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
