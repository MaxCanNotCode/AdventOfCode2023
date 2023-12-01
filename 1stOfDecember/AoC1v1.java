import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class AoC1v1 {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        int var = readMethod(input);
        System.out.println("Final Sum: " + var);
    }

    private static int readMethod(String input) throws IOException {
        String helperLine;
        int var = 0;
        int xyz;
        BufferedReader reader;
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            xyz = count(helperLine);
            var = var + xyz;
            // Write the current var value to the output file
            writer.write(String.valueOf(xyz));
            writer.newLine(); // Move to the next line in the file
        }

        writer.close(); // Close the BufferedWriter to ensure the changes are saved
        return var;
    }
    private static int count(String helperLine) {
        int retVal = 0;
        String helperLine2;
        String helperLine3;
        int charStart = 0;
        int charEnd =0;
        List<Character> charList = new ArrayList<>();
        List<Character> charList2 = new ArrayList<>();
        for (int i = 0; i < helperLine.length(); i++){
            if(isDigit(helperLine.charAt(i))){
                break;
            }
            charList.add(helperLine.charAt(i));
            helperLine2= charListToString(charList);

            if (helperLine2.contains("one")){
                charStart = 1;
                break;
            } else if (helperLine2.contains("two")) {
                charStart = 2;
                break;
            } else if (helperLine2.contains("three")) {
                charStart = 3;
                break;
            } else if (helperLine2.contains("four")) {
                charStart = 4;
                break;
            } else if (helperLine2.contains("five")) {
                charStart = 5;
                break;
            } else if (helperLine2.contains("six")) {
                charStart = 6;
                break;
            } else if (helperLine2.contains("seven")) {
                charStart = 7;
                break;
            } else if (helperLine2.contains("eight")) {
                charStart = 8;
                break;
            } else if (helperLine2.contains("nine")) {
                charStart = 9;
                break;
            }
        }

        for (int i = 0; i < helperLine.length(); i++) {
            if (charStart!=0){
                retVal=charStart*10;
                break;
            } else if (isDigit(helperLine.charAt(i))){
                retVal = Integer.parseInt(String.valueOf(helperLine.charAt(i)))*10;
                break;
            }
        }
        for (int i= helperLine.length()-1; i >= 0; i--){
            if(isDigit(helperLine.charAt(i))){
                charEnd = Integer.parseInt(String.valueOf(helperLine.charAt(i)));
                break;
            }
            charList2.add(helperLine.charAt(i));
            helperLine3= reverseString(charListToString(charList2));

            if (helperLine3.contains("one")){
                charEnd = 1;
                break;
            } else if (helperLine3.contains("two")) {
                charEnd = 2;
                break;
            } else if (helperLine3.contains("three")) {
                charEnd = 3;
                break;
            } else if (helperLine3.contains("four")) {
                charEnd = 4;
                break;
            } else if (helperLine3.contains("five")) {
                charEnd = 5;
                break;
            } else if (helperLine3.contains("six")) {
                charEnd = 6;
                break;
            } else if (helperLine3.contains("seven")) {
                charEnd = 7;
                break;
            } else if (helperLine3.contains("eight")) {
                charEnd = 8;
                break;
            } else if (helperLine3.contains("nine")) {
                charEnd = 9;
                break;
            }
        }
        for (int i= helperLine.length()-1; i >= 0; i--) {
            if (charEnd!=0){
                retVal=retVal + charEnd;
                break;
            } else if (isDigit(helperLine.charAt(i))){
                retVal = retVal+Integer.parseInt(String.valueOf(helperLine.charAt(i)));
                break;
            }
        }
        return retVal;
    }
    private static String charListToString(List<Character> charList) {
        StringBuilder stringBuilder = new StringBuilder(charList.size());

        // Append each character to the StringBuilder
        for (Character character : charList) {
            stringBuilder.append(character);
        }

        // Convert StringBuilder to String
        return stringBuilder.toString();
    }
    private static String reverseString(String input) {
        char[] charArray = input.toCharArray();
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            // Swap characters at left and right indices
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // Move indices towards the center
            left++;
            right--;
        }

        // Convert the char array back to a String
        return new String(charArray);
    }
}