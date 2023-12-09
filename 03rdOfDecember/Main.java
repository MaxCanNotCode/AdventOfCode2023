import java.io.*;
import static java.lang.Character.isDigit;
public class Main {
    public static final int XVAR = 140;
    public static final int YVAR = 140;
    public static void main(String[] args) throws IOException {
        String input = args[0];
        int result = read(input);
        System.out.println(result);
    }

    private static int read(String input) throws IOException {
        int result = 0;
        int number = 0;
        int [] numberArr = new int[2];
        int yCount = 0;
        char c;
        int multi =1;
        int flag = 0;
        char[][] arr = new char[XVAR][YVAR];
        String helperLine;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            arr = arrCreator(arr, helperLine, yCount);
            yCount++;
        }
        for (int i = 0; i < YVAR; i++){
            for (int j = XVAR-1; j >= 0; j--){
                c =arr[i][j];
                if (isDigit(c)){
                    flag = 1;
                    number = number + Integer.parseInt(String.valueOf(c)) * multi;
                    numberArr[0] = number;
                    multi = multi * 10;
                    numberArr[1] = Math.max(check(arr,i,j),numberArr[1]);
                } else {
                    if (flag == 1){
                        if(numberArr[1]==1){
                            result = result + numberArr[0];
                        }
                        flag = 0;
                        multi = 1;
                        number = 0;
                        numberArr[0]=0;
                        numberArr[1]=0;
                    }
                }
            }
            if (flag == 1){
                if(numberArr[1]==1){
                    result = result + numberArr[0];
                }
                flag = 0;
                multi = 1;
                number = 0;
                numberArr[0]=0;
                numberArr[1]=0;
            }
        }

        int gears = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j< arr.length; j++){
                if (arr[i][j]=='*'){
                        int star = starCheck(i, j, arr);
                        gears = gears + star;
                    }
                }
            }
        System.out.println(gears);
        return result;
    }

    private static int starCheck(int i, int j, char[][] arr) {
        int a = 0;
        int b= 0;
        String temp;
        int count = 0;

        if(j+1<=XVAR){
            if(isDigit(arr[i][j+1])){
                temp = checkNumb(arr, i,j+1);
                a = Integer.parseInt(temp);
                count++;
            }
        }
        if(j>0){
            if(isDigit(arr[i][j-1])){
                temp = checkNumb(arr, i,j-1);
                if(a==0){
                    a = Integer.parseInt(temp);
                } else {
                    b = Integer.parseInt(temp);
                }
                count++;
            }
        }

        if(i>0) {
            if (isDigit(arr[i - 1][j])) {
                temp = checkNumb(arr, i - 1, j);
                if (a == 0) {
                    a = Integer.parseInt(temp);
                } else {
                    b = Integer.parseInt(temp);
                }
                count++;
            } else {
                if (j + 1 <= XVAR && isDigit(arr[i - 1][j + 1])) {
                    temp = checkNumb(arr, i - 1, j + 1);
                    if (a == 0) {
                        a = Integer.parseInt(temp);
                    } else {
                        b = Integer.parseInt(temp);
                    }
                    count++;
                }
                if (j > 0 && isDigit(arr[i - 1][j - 1])) {
                    temp = checkNumb(arr, i - 1, j - 1);
                    if (a == 0) {
                        a = Integer.parseInt(temp);
                    } else {
                        b = Integer.parseInt(temp);
                    }
                    count++;
                }
            }
        }

        if(i+1<=YVAR){
            if(isDigit(arr[i+1][j])){
                temp = checkNumb(arr,i+1,j);
                if(a==0){
                    a = Integer.parseInt(temp);
                } else {
                    b = Integer.parseInt(temp);
                }
                count++;
            } else {
                if (j+1 <= XVAR && isDigit(arr[i+1][j+1])) {
                    temp = checkNumb(arr,i+1,j+1);
                    if(a==0){
                        a = Integer.parseInt(temp);
                    } else {
                        b = Integer.parseInt(temp);
                    }
                    count++;
                }  if (j>0 && isDigit(arr[i+1][j-1])) {
                    temp = checkNumb(arr,i+1,j-1);
                    if(a==0){
                        a = Integer.parseInt(temp);
                    } else {
                        b = Integer.parseInt(temp);
                    }
                    count++;
                }
            }
        }
        if(count!=2){
            System.out.println(i+ " "+j);
            return 0;
        }
        return a * b;
    }

    private static String checkNumb(char[][] arr, int i, int j) {
        String ret ="";
        for(int k = j; k<XVAR; k++){
            if (isDigit(arr[i][k])){
                String s = String.valueOf(arr[i][k]);
                ret=ret + s;
            } else {
                k=XVAR+1;
            }
        }
        if(j-1>=0){
            for (int k = j-1; k>=0; k--){
                if (isDigit(arr[i][k])){
                    String s = String.valueOf(arr[i][k]);
                    ret=s + ret;
                } else {
                    k=-1;
                }
            }
        }

        return ret;
    }


    private static int check(char[][] arr, int i, int j) {
        int returnFlag = 0;
        if (j - 1 >= 0){
            if (!isDigit(arr[i][j-1]) && arr[i][j-1]!='.'){
                returnFlag =1;
            }
            if(i-1>= 0){
                if (!isDigit(arr[i - 1][j-1]) && arr[i-1][j-1]!='.'){
                    returnFlag =1;
                }
            }
            if(i+1<XVAR){
                if (!isDigit(arr[i + 1][j-1]) && arr[i+1][j-1]!='.'){
                    returnFlag =1;
                }
            }
        }
        if (j+1<XVAR){
            if (!isDigit(arr[i][j+1]) && arr[i][j+1]!='.'){
                returnFlag =1;
            }
            if(i-1>= 0){
                if (!isDigit(arr[i - 1][j+1]) && arr[i-1][j+1]!='.'){
                    returnFlag =1;
                }
            }
            if(i+1<XVAR){
                if (!isDigit(arr[i + 1][j+1]) && arr[i+1][j+1]!='.'){
                    returnFlag =1;
                }
            }
        }
        if(i-1>= 0){
            if (!isDigit(arr[i - 1][j]) && arr[i-1][j]!='.'){
                returnFlag =1;
            }
        }
        if(i+1<XVAR){
            if (!isDigit(arr[i + 1][j]) && arr[i+1][j]!='.'){
                returnFlag =1;
            }
        }
        return returnFlag;
    }

    private static char[][] arrCreator(char[][] arr, String helperLine, int yCount) {
        char[][] returnArr = arr;
        for (int i = 0; i < XVAR; i++){
            returnArr[yCount][i]=helperLine.charAt(i);
        }
        return returnArr;
    }
}