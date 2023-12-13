import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(read(args[0]));
    }

    private static int read(String input) throws IOException {
        String helperLine;
        int sum = 0;
        char[][] space;
        ArrayList<char[]> charArrayList = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            char[] tmp = helperLine.toCharArray();
            charArrayList.add(tmp);
        }
        space =convertArrayListToCharArray(charArrayList);
        space = expanding(space);
        int[][] tuples = tupleFinder(space);
        for (int i=0; i< tuples.length; i++){
            sum = sum + scout(space, tuples[i]);
        }
        return sum;
    }

    private static int scout(char[][] space, int[] tuple) {
        int a =tuple[0];
        int b =tuple[1];
        int c =tuple[2];
        int d =tuple[3];
        return Math.abs(a-c) + Math.abs(b-d);
    }

    private static int[][] tupleFinder(char[][] space) {
        ArrayList<int []> list = new ArrayList<>();
        for (int i = 0; i <space.length; i++){
            for (int j = 0; j<space[i].length; j++){
                if(space[i][j]=='#'){
                    int [] tmp = {i,j};
                    list.add(tmp);
                }
            }
        }
        ArrayList<int []> ret = new ArrayList<>();
        for (int i = 0; i< list.size(); i++){
            for (int j = i+1; j< list.size(); j++){
                int[] tmp ={list.get(i)[0],list.get(i)[1],list.get(j)[0],list.get(j)[1]};
                ret.add(tmp);
            }
        }
        return convertArrayListToIntArray(ret);
    }
    public static int[][] convertArrayListToIntArray(ArrayList<int[]> arrayList) {
        int size = arrayList.size();
        int[][] result = new int[size][];

        for (int i = 0; i < size; i++) {
            int[] currentArray = arrayList.get(i);
            int currentSize = currentArray.length;
            result[i] = new int[currentSize];

            for (int j = 0; j < currentSize; j++) {
                result[i][j] = currentArray[j];
            }
        }

        return result;
    }
    private static char[][] expanding(char[][] space) {
        int [] rowIndices = rowFinder(space);
        int [] columnIndices = columnFinder(space);
        space = rowAdder(space, rowIndices);
        space = columnAdder(space, columnIndices);
        return space;
    }

    private static char[][] columnAdder(char[][] space, int[] columnIndices) {
        ArrayList<char[]> tmp = new ArrayList<>();
        for (int i = 0; i<space[0].length; i++){
            if(inArray(i,columnIndices)){
                tmp.add(colConv(i, space));
            }
            tmp.add(colConv(i, space));
        }
        return convertColArrayListToCharArray(tmp);
    }
    public static char[][] convertColArrayListToCharArray(ArrayList<char[]> arrayList) {
        int numRows = arrayList.get(0).length; // Assuming all columns have the same length
        int numColumns = arrayList.size();
        char[][] resultArray = new char[numRows][numColumns];

        for (int i = 0; i < numColumns; i++) {
            char[] currentColumn = arrayList.get(i);

            for (int j = 0; j < numRows; j++) {
                resultArray[j][i] = currentColumn[j];
            }
        }

        return resultArray;
    }

    private static char[] colConv(int i, char[][] space) {
        char[] tmp = new char[space.length];
        for (int j =0; j< space.length; j++){
            tmp[j]=space[j][i];
        }
        return tmp;
    }

    private static char[][] rowAdder(char[][] space, int[] rowIndices) {
        ArrayList<char[]> tmp = new ArrayList<>();
        for (int i = 0; i<space.length; i++){
            if(inArray(i,rowIndices)){
                tmp.add(space[i]);
            }
            tmp.add(space[i]);
        }
        return convertArrayListToCharArray(tmp);
    }

    private static boolean inArray(int i, int[] rowIndices) {
        for (int j = 0; j< rowIndices.length; j++){
            if(rowIndices[j]==i){
                return true;
            }
        }
        return false;
    }

    private static int[] rowFinder(char[][] space) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i< space.length; i++){
            int test=0;
            for (int j=0; j< space[i].length;j++){
                if (space[i][j]!='.'){
                    test++;
                }
            }
            if(test==0){
                list.add(i);
            }
        }
        int [] tmp=list.stream().mapToInt(Integer::intValue).toArray();
        return tmp;
    }

    private static int[] columnFinder(char[][] space) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i< space[0].length; i++){
            int test=0;
            for (int j=0; j< space.length;j++){
                if (space[j][i]!='.'){
                    test++;
                }
            }
            if(test==0){
                list.add(i);
            }
        }
        int [] tmp=list.stream().mapToInt(Integer::intValue).toArray();
        return tmp;
    }

    private static char[][] convertArrayListToCharArray(ArrayList<char[]> charArrayList) {
        // Calculate the total number of characters across all char arrays
        char[][] resultArray = new char[charArrayList.size()][charArrayList.get(0).length];
        for (int i = 0; i < charArrayList.size(); i++){
            resultArray[i]=charArrayList.get(i);
        }
        return resultArray;
    }
}