/*
 * @(#)JavaLab2.java    1.01 24/03/23
 */


import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
*The program accepts two file names from the user: input and output.
*Next, the input file is opened (if it exists),
*the number of different characters of the English alphabet
*A-Z, a-z is counted and the results of counting for each character are written to the output file.
*
*@version 1.01 23 Mar 2024
*@autor Nikanova Darya
*/

public class JavaLab2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputFileName, outputFileName;
        File inputFile, outputFile;
        Map<Character, Integer> dict = new TreeMap<>();
        FileReader fileReader;
        FileWriter fileWriter;

        System.out.println("Enter input file name:");
        inputFileName = in.next();
        System.out.println("Enter output file name:");
        outputFileName = in.next();

        inputFile = new File(inputFileName);
        outputFile = new File(outputFileName);

        if (!(inputFile.isFile() && inputFile.canRead() &&
                inputFileName.substring(inputFileName.length() - 3, inputFileName.length()).equals("txt"))) {
            System.out.println("It is impossible to read input file.");
        }
        do {
            if (outputFile.exists() || outputFile.isDirectory()) {
                System.out.printf("%s. Enter another name:\n",
                        (!outputFile.isFile()) ?
                                ("Output file is not a file") :
                                ("Output file with this name is already exists"));
                outputFileName = in.next();
                outputFile = new File(outputFileName);
            } else {
                try {
                    outputFile.createNewFile();
                } catch (IOException e) {
                    System.out.printf("File creating error: %s\n", e.getMessage());
                }
                break;
            }
        } while (true);

        try {
            fileReader = new FileReader(inputFile);
            fileWriter = new FileWriter(outputFile);
        } catch (FileNotFoundException e) {
            System.out.printf("Reading file error: %s\n", e.getMessage());
            return;
        } catch (IOException e) {
            System.out.printf("Writing file error: %s\n", e.getMessage());
            return;
        }

        for (char c = 'A'; c <= 'Z'; ++c)
            dict.put(c, 0);
        for (char c = 'a'; c <= 'z'; ++c)
            dict.put(c, 0);

        try {
            char[] buf = new char[1024];
            int result = fileReader.read(buf);
            while (result != -1) {
                for (int i = 0; i < result; ++i) {
                    if (!dict.containsKey(buf[i])) {
                        continue;
                    }
                    dict.put(buf[i], dict.get(buf[i]) + 1);
                }
                result = fileReader.read(buf);
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.printf("Reading input file error: %s\n", e.getMessage());
            return;
        }

        try {
            for (Character it : dict.keySet()){
                StringBuilder str = new StringBuilder();
                str.append(it);
                str.append(' ');
                str.append(dict.get(it));
                str.append('\n');
                fileWriter.write(str.toString());
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.printf("Writing file error: %s\n", e.getMessage());
        }
    }
}
