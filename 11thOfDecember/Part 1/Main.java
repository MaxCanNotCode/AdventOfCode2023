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
        int[][] tuples = tupleFinder(space);
        space = expanding(space);
        for (int i=0; i< tuples.length; i++){
            sum = sum + scout(space, tuples[i]);
        }
        return sum;
    }

    private static int scout(char[][] space, int[] tuple) {
    }

    private static int[][] tupleFinder(char[][] space) {
        int [] tmp = new int[4];
        ArrayList<int []> list = new ArrayList<>();
        for (int i = 0; i <space.length; i++){
            for (int j = 0; j<space[i].length; i++){
                if (space[i][j]=='#') {
                    for(int k =i; k< space.length; k++){
                        for(int p=j+1;p<space[i].length;p++){
                            if (space[k][p]=='#'){
                                tmp[0] = i;
                                tmp[1] = j;
                                tmp[2] = k;
                                tmp[3] = p;
                                list.add(tmp);
                            }
                        }
                    }
                }
            }
        }
        return convertArrayListToIntArray(list);
    }

    private static char[][] expanding(char[][] space) {
        int [] rowIndices = rowFinder(space);
        int [] columnIndices = columnFinder(space);
        space = rowAdder(space, rowIndices);
        space = columnAdder(space, columnIndices);
        return space;
    }

    private static char[][] columnAdder(char[][] space, int[] columnIndices) {
    }

    private static char[][] rowAdder(char[][] space, int[] rowIndices) {
        for (int i = 0; i<space.length; i++){
            if(inArray(i)){
                
            }
        }
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
        int totalChars = 0;
        for (char[] charArray : charArrayList) {
            totalChars += charArray.length;
        }

        // Create the resulting char[][]
        char[][] resultArray = new char[charArrayList.size()][totalChars];

        // Populate the resultArray
        int rowIndex = 0;
        for (char[] charArray : charArrayList) {
            System.arraycopy(charArray, 0, resultArray[rowIndex], 0, charArray.length);
            rowIndex++;
        }

        return resultArray;
    }
}