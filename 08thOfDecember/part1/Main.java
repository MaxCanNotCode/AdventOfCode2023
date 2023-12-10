import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        int output = read(input);
        System.out.println(output);
    }
    private static int read(String input) throws IOException {
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
        return counter(strArr, instr);
    }

    private static int counter(String[] strArr, String fuckfuck) {
        int count = 0;
        char [] instr = fuckfuck.toCharArray();
        int check = 0;
        int start = 0; // AAA
        while (check == 0){
            for (int i = 0; i< instr.length; i++){
                if(start==strArr.length-1){
                    return count;
                }
                String [] temp = strArr[start].split(",");
                if(instr[i]=='L'){
                    start = toNumb(temp[0]);
                    count++;
                } else {
                    start = toNumb(temp[1]);
                    count++;
                }
            }
        }
        return count;
    }

    private static int toNumb(String s) {
        char[] charArr = s.toCharArray();
        return (676*(charArr[2] - 'A')) +(26*(charArr[1] - 'A')) +(charArr[0] - 'A');
    }
}