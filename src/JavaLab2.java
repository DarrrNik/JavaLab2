/**
 * JavaLab2.java
 *
 * Version: 1
 *
 * 21/03/2024
 */

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class JavaLab2 {
    /*
    The program accepts two file names from the user: input and output.
    Next, the input file is opened (if it exists),
    the number of different characters of the English alphabet
    A-Z, a-z is counted and the results of counting for each character are written to the output file.
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter input file name:");
        String inputFileName = in.next();
        System.out.println("Enter output file name:");
        String outputFileName = in.next();

        try{
            in = new Scanner(new FileReader(inputFileName));
            Map<Character, Integer> dict = new TreeMap<>();
            for (char c = 'A'; c <= 'Z'; ++c)
                dict.put(c, 0);
            for (char c = 'a'; c <= 'z'; ++c)
                dict.put(c, 0);
            while(in.hasNext()){
                String buf = in.next();
                for (int i = 0; i < buf.length(); ++i){
                    if (!dict.containsKey(buf.charAt(i)))
                        continue;
                    dict.put(buf.charAt(i), dict.get(buf.charAt(i)) + 1);
                }
            }
            try {
                FileWriter fileOut = new FileWriter(outputFileName);
                for (Character it : dict.keySet()){
                    String str = "";
                    str += it;
                    str += ' ';
                    str += dict.get(it);
                    str += '\n';
                    fileOut.write(str);
                }
                fileOut.close();
            }
            catch (IOException e){
                System.out.printf("Output file error: %s\n", e.getMessage());
            }
            in.close();
        }
        catch (FileNotFoundException e){
            System.out.printf("Input file error: %s\n", e.getMessage());
        }
    }
}
