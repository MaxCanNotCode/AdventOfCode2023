import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = args[0];
        System.out.println(read(input));
    }

    private static int read(String input) throws IOException {
        String helperLine;
        char[][] maze;
        ArrayList<char[]> charArrayList = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(input));
        while ((helperLine = reader.readLine()) != null) {
            char[] tmp = helperLine.toCharArray();
            charArrayList.add(tmp);
        }
        maze =convertArrayListToCharArray(charArrayList);
        int[] coords = sFinder(maze);
        ArrayList<int[]> used = new ArrayList<>();
        int[] tmpOld ={-1,-1};
        return looping(maze, coords, tmpOld,0, used);
    }

    private static int looping(char[][] maze, int[] coords, int[] lastCoords, int count, ArrayList<int[]> used) {
        int x = coords[1];
        int y = coords[0];
        if (count!=0 && maze[y][x]=='S' || count!=0 && maze[x][y]=='s'){
            return count/2;
        }
        if(maze[y][x]!='-' && maze[y][x]!='7' &&maze[y][x]!='F'){
            if(y>0){ //oben drüber
                if (maze[y-1][x]=='|'||maze[y-1][x]=='F'||maze[y-1][x]=='7'||maze[y-1][x]=='S'){
                    int [] newCords = {y-1, x};
                    if(notIn(newCords, used )&& newCords[0]!=coords[0] && newCords[1]!=coords[1]){
                        used.add(newCords);
                        return looping(maze, newCords, coords,count+1, used);
                    }
                }
            }
        }

        if (maze[y][x]!='-' && maze[y][x]!='L'&&maze[y][x]!='J'){
            if (y+1< maze.length){ //drunter
                if (maze[y+1][x]=='|'||maze[y+1][x]=='J'||maze[y+1][x]=='L'||maze[y+1][x]=='S'){
                    int [] newCords = {y+1, x};
                    if(notIn(newCords, used)&& newCords[0]!=coords[0] && newCords[1]!=coords[1]){
                        used.add(newCords);
                        return looping(maze, newCords,lastCoords, count+1, used);
                    }
                }
            }
        }

        if(maze[y][x]!='|'&&maze[y][x]!='F'&&maze[y][x]!='L'){
            if (x>0){
                if (maze[y][x-1]=='-'||maze[y][x-1]=='F'||maze[y][x-1]=='L'||maze[y][x-1]=='S'){
                    int [] newCords = {y, x-1};
                    if(notIn(newCords, used)&& newCords[0]!=coords[0] && newCords[1]!=coords[1]){
                        used.add(newCords);
                        return looping(maze, newCords,lastCoords, count+1, used);
                    }
                }
            }
        }
        if (maze[y][x]!='|'&&maze[y][x]!='7'&&maze[y][x]!='J'){
            if (x+1<maze[y].length){
                if (maze[y][x+1]=='-'||maze[y][x+1]=='S'||maze[y][x+1]=='J'||maze[y][x+1]=='7'){
                    int [] newCords = {y, x+1};
                    if(notIn(newCords, used)&& newCords[0]!=coords[0] && newCords[1]!=coords[1]){
                        used.add(newCords);
                        return looping(maze, newCords,lastCoords, count+1, used);
                    }
                }
            }
        }
        System.out.println("this should not happen");
        System.out.println(coords[0] + " " + coords[1]);
        System.out.println(maze[y][x]);
        System.out.println(count);
        return 0;
    }

    private static boolean notIn(int[] newCords, ArrayList<int[]> used) {
        for (int i = 0; i < used.size(); i++){
            int count = 0;
            for (int j = 0; j < newCords.length; j++) {
                if (newCords[j] == used.get(i)[j]) {
                    count++;
                }
            }
            if (count!=2){
                return true;
            }
        }
        if(used.size()==0){
            return true;
        }
        return false;
    }

    private static int[] sFinder(char[][] maze) {
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if(maze[i][j]=='S'||maze[i][j]=='s'){
                    return new int[]{i,j};
                }
            }
        }
        return null;
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