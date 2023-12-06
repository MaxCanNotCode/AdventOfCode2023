import java.io.*;
import static java.lang.Character.isDigit;
public class Main {
    public static final int GREEN = 13;
    public static final int RED = 12;
    public static final int BLUE = 14;

    public static void main(String[] args) throws IOException {
        String input = args[0];
        int idSum = read(input);
        System.out.println(idSum);
    }

    private static int read(String input) throws IOException {
        String helperLine;
        int x = 0;
        String[] idString;
        String[] cubeString;
        int id;
        int var = 0;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            idString = helperLine.split(":");
            idString[1]= idString[1].replace(",", ";");
            id = idSetter(idString[0]);
            cubeString = idString[1].split(";");
            int[] powerArr =check(cubeString);
            x = x + powerArr[1];
            if (powerArr[0]==1) {
                var = var + id;
            }
        }
        System.out.println(x);
        return var;
    }

    private static int[] check(String[] cubeString) {
        int[] retArr =new int[2];
        int[] checkArr =new int[3];
        String[] numbString;
        int var = 0;
        int count=1;
        for (int i = 0; i< cubeString.length; i++){
            if (cubeString[i].contains("blue")) {
                numbString = cubeString[i].split(" ");
                for (int j = numbString[1].length(); j>0;j--){
                    char c = numbString[1].charAt(j-1);
                    var = var + Integer.parseInt(String.valueOf(c))*count;
                    count=count*10;
                }
                if(var>checkArr[0]){
                    checkArr[0]=var;
                }
                var=0;
                count=1;
            } else if (cubeString[i].contains("red")) {
                numbString = cubeString[i].split(" ");
                for (int j = numbString[1].length(); j>0;j--){
                    char c = numbString[1].charAt(j-1);
                    var = var + Integer.parseInt(String.valueOf(c))*count;
                    count=count*10;
                }
                if(var>checkArr[1]){
                    checkArr[1]=var;
                }
                var=0;
                count=1;
            } else if (cubeString[i].contains("green")) {
                numbString = cubeString[i].split(" ");
                for (int j = numbString[1].length(); j>0;j--){
                    char c = numbString[1].charAt(j-1);
                    var = var + Integer.parseInt(String.valueOf(c))*count;
                    count=count*10;
                }
                if(var>checkArr[2]){
                    checkArr[2]=var;
                }
                var=0;
                count=1;
            }
        }
        int power = checkArr[0]*checkArr[1]*checkArr[2];
        retArr[1]=power;
        if(BLUE>=checkArr[0] && RED>=checkArr[1] && GREEN>=checkArr[2]){
            retArr[0] =1;
        } else {
            retArr[0] =0;
        }
        return retArr;
    }

    private static int idSetter(String s) {
        int id = 0;

        int var2 = 1;
        for (int i = 5; i < s.length(); i++) {
            for (int j = 0; j < s.length() - 1 - i; j++) {
                var2 = var2 * 10;
            }
            char c = s.charAt(i);
            if (isDigit(c)) {
                id = id + var2 * Integer.parseInt(String.valueOf(c));
                var2 = 1;
            }
        }
        return id;
    }

}