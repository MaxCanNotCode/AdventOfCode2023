import java.io.*;
import java.util.ArrayList;

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
        return counter(strArr, instr, bitmap);
    }

    private static int counter(String[] strArr, String fuckfuck, int[] bitmap) {
        int[] ghostArr;
        ArrayList<Integer> ghostList = new ArrayList<>();
        ArrayList<Integer> zList = new ArrayList<>();
        for(int i= 0; i< bitmap.length; i= i + 26){
            if(bitmap[i]==1){
                ghostList.add(i);
            }
        }
        for(int i=25; i< bitmap.length;i= i +26){
            if(bitmap[i]==1){
                zList.add(i);
            }
        }
        int [] zbitmap = new int[bitmap.length];
        for (int i = 0; i< zList.size(); i++){
            zbitmap[zList.get(i)]=1;
        }
        ghostArr=ghostList.stream().mapToInt(Integer::intValue).toArray();
        int count = 0;
        int [] updateArr;
        ArrayList<Integer> updateList = new ArrayList<>();
        updateList.addAll(ghostList);
        updateList.add(0);
        updateList.add(0);
        updateArr=updateList.stream().mapToInt(Integer::intValue).toArray();
        char [] instr = fuckfuck.toCharArray();
        int check = 0;
        int start = 0; // AAA
        while (check == 0){
            for (int i = 0; i< instr.length; i++){
                if(start != 0 && zbitmap[start]==1){
                    updateArr=zCheck(ghostArr, count, strArr, instr, updateArr);
                    if(updateArr[ghostArr.length]==0){
                        return count;
                    }
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
    private static int[] zCheck(int[] ghostArr, int count, String[] strArr, char[] instr, int[] updateArr) {
        int inCount= 0;
        int newCount =count - updateArr[ghostArr.length+1];
        int checkVar = 0;
        for (int i = 0; i< ghostArr.length; i++){
            int start = updateArr[i];
            for (int j = 0; j<=newCount;j++){
                if(j>=instr.length){
                    inCount=0;
                }
                String [] tmp =strArr[start].split(",");
                if(instr[inCount]=='L'){
                    start =toNumb(tmp[0]);
                } else {
                    start = toNumb(tmp[1]);
                }
                inCount++;
            }
            updateArr[i]=start;
            if(start%25!=0){
                checkVar++;
            }
        }
        updateArr[ghostArr.length]=checkVar;
        updateArr[ghostArr.length+1]=count;
        return updateArr;
    }

    private static int toNumb(String s) {
        char[] charArr = s.toCharArray();
        return (676*(charArr[2] - 'A')) +(26*(charArr[1] - 'A')) +(charArr[0] - 'A');
    }
}