import java.io.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        long output = read(input); // Change the data type to long
        System.out.println(output);
    }

    private static long read(String input) throws IOException {
        String helperLine;
        String[] strArr;
        BufferedReader reader;
        long out = Long.MAX_VALUE; // Change the data type to long
        int count = 0;
        long[] seedArr = null;
        long[] bitmap = null;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            if (count == 0) {
                strArr = helperLine.split(" ");
                seedArr = new long[strArr.length - 1];
                bitmap = new long[seedArr.length];
                for (int i = 1; i < strArr.length; i++) {
                    seedArr[i - 1] = Long.parseLong(strArr[i]);
                }
                count = 1;
            }
            if (helperLine.equals("")) {
                System.out.println(seedArr[0] + " " + seedArr[1] + " " + seedArr[2] + " " + seedArr[3]);
                long [] newBitmap = new long[bitmap.length];
                bitmap= newBitmap;
                count = 1;
            }
            if (count == 2) {
                String[] helperLineArray = helperLine.split(" ");
                long[] map = new long[helperLineArray.length];
                for (int i = 0; i < helperLineArray.length; i++) {
                    map[i] = Long.parseLong(helperLineArray[i]);
                }
                for (int i = 0; i < seedArr.length; i++) {
                    if (seedArr[i] >= map[1] && seedArr[i] <= map[1] + map[2] - 1&&bitmap[i]==0) {
                        seedArr[i] =map[0] + (seedArr[i] - map[1]);
                        bitmap[i]=1;
                    }
                }
            }
            if (helperLine.equals("seed-to-soil map:") || helperLine.equals("soil-to-fertilizer map:")
                    || helperLine.equals("fertilizer-to-water map:") || helperLine.equals("water-to-light map:")
                    || helperLine.equals("temperature-to-humidity map:")
                    || helperLine.equals("light-to-temperature map:") || helperLine.equals("humidity-to-location map:")) {
                count = 2;
            }
        }
        for (int i = 0; i < seedArr.length; i++) {
            if (seedArr[i] < out) {
                out = seedArr[i];
            }
        }
        return out;
    }
}
