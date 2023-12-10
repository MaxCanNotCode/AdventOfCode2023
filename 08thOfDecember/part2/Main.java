import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        long output = read(input);
        System.out.println(output);
    }
    private static long read(String input) throws IOException {
        String[] strArr = new String[17576];
        int[] bitmap = new int [17576];
        String instr="AMK";
        String helperLine;
        BufferedReader reader;
        int count = 0;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            if (count ==0){
                instr=helperLine;
            }
            if(count>1){
                String[] tmp =helperLine.split("=");
                tmp[0]=tmp[0].replaceAll(" ", "");
                strArr[toNumb(tmp[0])] = tmp[1].replaceAll("[()\\s]+", "");
                bitmap[toNumb(tmp[0])] = 1;
            }
            count++;
        }
        ArrayList<Integer> aList = new ArrayList<>();
        for(int i= 0; i< bitmap.length; i= i + 26){
            if(bitmap[i]==1){
                aList.add(i);
            }
        }
        ArrayList<Integer> zList = new ArrayList<>();
        for(int i=25; i< bitmap.length;i= i +26){
            if(bitmap[i]==1){
                zList.add(i);
            }
        }
        int[] zMap = new int[17576];
        for (int i = 0; i<zList.size();i++){
            if(bitmap[zList.get(i)]==1){
                zMap[zList.get(i)]=1;
            }
        }
        int[] dist = new int [aList.size()];
        for(int i  =0; i< aList.size(); i++){
            int start = aList.get(i);
            dist[i]=counter(strArr, instr, bitmap, zMap, start);
        }

        return calculateLCM(dist);
    }

    private static int counter(String[] strArr, String fuckfuck, int[] bitmap, int[] zMap, int start) {
        int count = 0;
        char [] instr = fuckfuck.toCharArray();
        while (count!=-1){
            for(int i = 0; i<instr.length; i++){
                if(zMap[start]==1){
                    return count;
                }
                String[] tmp = strArr[start].split(",");
                if(instr[i]=='L'){
                    start = toNumb(tmp[0]);
                } else {
                    start = toNumb(tmp[1]);
                }
                count++;
            }
        }
        return count;
    }

    private static int toNumb(String s) {
        char[] charArr = s.toCharArray();
        return (26 * 26 * (charArr[0] - 'A')) + (26 * (charArr[1] - 'A')) + (charArr[2] - 'A');
    }
    private static long calculateLCM(int[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Array must contain at least one element");
        }

        long lcm = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            lcm = calculateLCM(lcm, numbers[i]);
        }

        return lcm;
    }

    private static long calculateLCM(long a, long b) {
        return Math.abs(a * b) / calculateGCD(a, b);
    }

    private static long calculateGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

}