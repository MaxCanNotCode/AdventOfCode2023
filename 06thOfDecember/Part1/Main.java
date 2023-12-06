public class Main {
    public static void main(String[] args) {
        int []time = {62,73,75,65};
        int []distance ={644,1023,1240,1023};
        int count = 1;
        for (int i = 0; i<time.length;i++){
            count = count*check(time[i], distance[i]);
        }
        System.out.println(count);
    }

    private static int check(int time, int distance) {
        int count = 0;
        int race;
        for(int i = 0; i<=time; i++){
            race = i * (time -i);
            if(race>distance){
                count++;
            }
        }
        return count;
    }
}