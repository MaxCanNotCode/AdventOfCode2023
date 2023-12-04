import java.io.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws IOException {
        String Input = args[0];
        int Output = read(Input);
        System.out.println(Output);
    }
    private static int read(String input) throws IOException {
        String helperLine;
        String[] idString;
        int[] winners;
        int ls = 0;
        int[] numbs;
        int[] quant = new int[201];
        for(int i = 0; i< quant.length; i++){
            quant[i] = 1;
        }
        int count = 1;
        int var = 0;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            idString = helperLine.split(":");
            idString= idString[1].split("\\|");
            winners = assign(idString[0]);
            numbs = assign(idString[1]);
            ls = ls + check2(winners, numbs);
            quant = check(winners, numbs, quant, count);
            count++;
        }
        for (int i = 0; i< quant.length; i++){
            var = var + quant[i];
        }
        System.out.println(ls);
        return var;
    }

    private static int check2(int[] winners, int[] numbs) {
        int x = 0;
        for(int i =0; i< winners.length; i++){
            for (int j= 0; j< numbs.length;j++){
                if(winners[i]==numbs[j]){
                    if (x == 0){
                        x = 1;
                    } else {
                        x = x *2;
                    }
                }
            }
        }
        return x;
    }

    private static int[] assign(String s) {
        String [] arr = s.split(" ");
        ArrayList<Integer> intList = new ArrayList<>();
        for(int i= 0; i< arr.length;i++){
            if(arr[i]!="") {
                intList.add(Integer.parseInt(arr[i]));
            }
        }
        return intList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] check(int[] winners, int[] numbs, int[] quant, int count) {
        int val = 0;
        int run = count-1;
        for(int k = 0; k<quant[run]; k++){
            for(int i =0; i< winners.length; i++){
                for (int j= 0; j< numbs.length;j++){
                    if(winners[i]==numbs[j]&&count< quant.length){
                        quant[count]++;
                        count++;
                    }
                }
            }
            count=run+1;
        }
        return quant;
    }
}