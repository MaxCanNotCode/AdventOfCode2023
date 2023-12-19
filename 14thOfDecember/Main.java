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
        ArrayList<char[]> arr = new ArrayList<>();
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            char[] tmp = helperLine.toCharArray();
            arr.add(tmp);
        }

        return shifting(arr);
    }

    private static int shifting(ArrayList<char[]> arr) {
        char[][] workingArr = arrCreate(arr);
        boolean shiftable =true;
        int count= 0;
        while(shiftable){
            for (int i= 1; i<workingArr.length; i++){
                for (int j  = 0; j <workingArr[i].length; j++){
                    if(workingArr[i-1][j]=='.' && workingArr[i][j]=='O'){
                        count=1;
                        workingArr[i-1][j]=workingArr[i][j];
                        workingArr[i][j]='.';
                    }
                }
            }
            if(count==0){
                shiftable=false;
            }
            count=0;
        }
        return load(workingArr);
    }

    private static int load(char[][] workingArr) {
        int count = 0;
        for (int i= 0; i<workingArr.length; i++){
            for (int j  = 0; j <workingArr[i].length; j++){
                if(workingArr[i][j]=='O'){
                    count = count + (workingArr.length-i);
                }
            }
        }
        return count;
    }

    private static char[][] arrCreate(ArrayList<char[]> arr) {
        char [][] retArr = new char[arr.size()][arr.get(0).length];
        for(int i = 0; i< arr.size(); i++){
            retArr[i]=arr.get(i);
        }
        return retArr;
    }
}