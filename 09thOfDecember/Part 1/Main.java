import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        System.out.println(read(input));
    }

    private static int read(String input) throws IOException {
        String helperLine;
        BufferedReader reader;
        ArrayList<int[]> listofArrays = new ArrayList<>();
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            String[] stringNumbers = helperLine.split(" ");
            int[] numbers = new int[stringNumbers.length];

            for (int i = 0; i < stringNumbers.length; i++) {
                numbers[i] = Integer.parseInt(stringNumbers[i]);
            }
            listofArrays.add(numbers);
        }
        return calc(listofArrays);
    }

    private static int calc(ArrayList<int[]> listofArrays) {
        int value = 0;
        for (int i= 0; i< listofArrays.size(); i++){
            value =  value + check(listofArrays.get(i));
        }
        return value;
    }

    private static int check(int[] ints) {
        int test = 0;
        for(int i = 0; i< ints.length; i++){
            if (ints[i]!=0){
                test = 1;
            }
        }
        if (test==0){
            return 0;
        } else {
            int [] newArr = new int [ints.length-1];
            for (int i=0; i< newArr.length;i++){
                newArr[i]= ints[i+1] - ints[i];
            }
            return ints[ints.length-1] + check(newArr);
        }
    }
}