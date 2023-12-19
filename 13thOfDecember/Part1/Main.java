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
        int sum =0;
        ArrayList<char[]> arr = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            if(helperLine.isEmpty()){
                sum= sum + mirror(arr);
                arr = new ArrayList<>();
            } else {
                char [] xyz = helperLine.toCharArray();
                arr.add(xyz);
            }
        }
        return sum;
    }

    private static int mirror(ArrayList<char[]> arr) {
        char [][] workingArr = arrCreate(arr);
        int checkVar=0;
        //Check is not correct like this. recursive check for every row column needed
        for (int j = 0; j< workingArr[0].length-1; j++){
            for (int i = 0; i < workingArr.length; i++){
                if(workingArr[i][j]!=workingArr[i][j+1]){
                    checkVar++;
                    i = workingArr.length;
                }
            }
            if(checkVar==0&&col(workingArr, j, 1)){
                return j;
            }
            checkVar=0;
        }
        for(int i = 0; i< workingArr.length-1; i++ ){
            if(Arrays.equals(workingArr[i], workingArr[i + 1])&& row(workingArr, i, 1)){
                return i*100;
            }
        }
        return 0;
    }

    private static boolean row(char[][] workingArr, int i, int count) {
        if(i-count>=0&&i+1 +count<workingArr.length){
            if(Arrays.equals(workingArr[i-count], workingArr[i+count+1])){
                return row(workingArr,i, count+1);
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean col(char[][] workingArr, int j, int count) {
        if (1+count+j<workingArr[0].length&& j-count >= 0){
            for (int i = 0; i < workingArr.length; i++){
                if(workingArr[i][j-count]!=workingArr[i][j+1+count]){
                    return false;
                }
            }
            return col(workingArr, j, count+1);
        }
        return true;
    }

    private static char[][] arrCreate(ArrayList<char[]> arr) {
        char [][] retArr = new char[arr.size()][arr.get(0).length];
        for(int i = 0; i< arr.size(); i++){
            retArr[i]=arr.get(i);
        }
        return retArr;
    }
}