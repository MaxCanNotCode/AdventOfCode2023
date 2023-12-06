import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        long output = read(input); // Change the data type to long
        System.out.println(output);
    }

    private static long read(String input) throws IOException {
        String helperLine;
        String[] strArr;
        BufferedReader reader;
        int count = 0;
        List<long[]> soilList = new ArrayList<>();
        List<long[]> fertilizerList = new ArrayList<>();
        List<long[]> waterList = new ArrayList<>();
        List<long[]> lightList = new ArrayList<>();
        List<long[]> temperatureList = new ArrayList<>();
        List<long[]> humidityList = new ArrayList<>();
        List<long[]> locationList = new ArrayList<>();
        long[] seedArr = null;

        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            if (helperLine.equals("")){
                count =1;
            }
            if (count == 0) {
                strArr = helperLine.split(" ");
                seedArr = new long[strArr.length - 1];
                for (int i = 1; i < strArr.length; i++) {
                    seedArr[i - 1] = Long.parseLong(strArr[i]);
                }
                count = 1;
            }
            if (count == 2) {
                long[] soil =new long[3];
                strArr = helperLine.split(" ");
                soil[0]=Long.parseLong(strArr[0]);
                soil[1]=Long.parseLong(strArr[1]);
                soil[2]=Long.parseLong(strArr[2]);
                soilList.add(soil);
            }
            if (count == 3) {
                long[] fertilizer =new long[3];
                strArr = helperLine.split(" ");
                fertilizer[0]=Long.parseLong(strArr[0]);
                fertilizer[1]=Long.parseLong(strArr[1]);
                fertilizer[2]=Long.parseLong(strArr[2]);
                fertilizerList.add(fertilizer);
            }
            if (count == 4) {
                long[] water =new long[3];
                strArr = helperLine.split(" ");
                water[0]=Long.parseLong(strArr[0]);
                water[1]=Long.parseLong(strArr[1]);
                water[2]=Long.parseLong(strArr[2]);
                waterList.add(water);
            }
            if (count == 5) {
                long[] light =new long[3];
                strArr = helperLine.split(" ");
                light[0]=Long.parseLong(strArr[0]);
                light[1]=Long.parseLong(strArr[1]);
                light[2]=Long.parseLong(strArr[2]);
                lightList.add(light);
            }
            if (count == 6) {
                long[] temperature =new long[3];
                strArr = helperLine.split(" ");
                temperature[0]=Long.parseLong(strArr[0]);
                temperature[1]=Long.parseLong(strArr[1]);
                temperature[2]=Long.parseLong(strArr[2]);
                temperatureList.add(temperature);
            }
            if (count == 7) {
                long[] humidity =new long[3];
                strArr = helperLine.split(" ");
                humidity[0]=Long.parseLong(strArr[0]);
                humidity[1]=Long.parseLong(strArr[1]);
                humidity[2]=Long.parseLong(strArr[2]);
                humidityList.add(humidity);
            }
            if (count == 8) {
                long[] location =new long[3];
                strArr = helperLine.split(" ");
                location[0]=Long.parseLong(strArr[0]);
                location[1]=Long.parseLong(strArr[1]);
                location[2]=Long.parseLong(strArr[2]);
                locationList.add(location);
            }
            if (helperLine.equals("seed-to-soil map:")){
                count =2;
            }
            if (helperLine.equals("soil-to-fertilizer map:")){
                count =3;
            }
            if (helperLine.equals("fertilizer-to-water map:")){
                count =4;
            }
            if (helperLine.equals("water-to-light map:")){
                count =5;
            }
            if (helperLine.equals("light-to-temperature map:")){
                count=6;
            }
            if (helperLine.equals("temperature-to-humidity map:")){
                count=7;
            }
            if (helperLine.equals("humidity-to-location map:")){
                count=8;
            }
        }

        return check(seedArr,soilList,fertilizerList,waterList,lightList,temperatureList,humidityList,locationList);
    }

    private static long check(long[] seedArr, List<long[]> soilList, List<long[]> fertilizerList, List<long[]> waterList, List<long[]> lightList, List<long[]> temperatureList, List<long[]> humidityList, List<long[]> locationList) {
    long smallest = Long.MAX_VALUE;
    for (int i = 0; i < seedArr.length; i=i+2){
        long var = Long.MAX_VALUE;
        for (long j = 0;j<seedArr[i+1];j++){
            var = j + seedArr[i];
            var = map(var, soilList);
            var = map(var, fertilizerList);
            var = map(var, waterList);
            var = map(var, lightList);
            var = map(var, temperatureList);
            var = map(var, humidityList);
            var = map(var, locationList);
            if (var<smallest){
                smallest=var;
            }
        }
    }
    return smallest;
    }

    private static long map(long var, List<long[]> list) {
        for (int i = 0; i< list.size();i++) {
            if (var >= list.get(i)[1] && var <= list.get(i)[1] + list.get(i)[2] - 1) {
                var =list.get(i)[0]+ (var - list.get(i)[1]);
                return var;
            }
        }
        return var;
    }
}