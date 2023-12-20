import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        System.out.println(read(input));
    }
    private static int read(String input) throws IOException {
        String helperLine;
        int sum = 0;
        String[] stringArr;
        ArrayList<String> stringList =  new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            stringArr=helperLine.split(",");
            stringList.addAll(Arrays.asList(stringArr));
        }
        for(int i = 0; i< stringList.size(); i++){
            sum = sum + hashAlgo(stringList.get(i));
        }
        return sum;
    }

    private static int hashAlgo(String s) {
        char[] charArr = s.toCharArray();
        int currVal = 0;
        for(int i  =0; i< charArr.length; i++){
            int x = charArr[i];
            currVal = currVal +x;
            currVal = currVal * 17;
            currVal = currVal % 256;
        }
        return currVal;
    }
}