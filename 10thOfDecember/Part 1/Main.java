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
        return iterativeApproach(maze, coords);
    }

    private static int iterativeApproach(char[][] maze, int[] coords) {
        int count = 1;
        int y = coords[0];
        int x = coords[1];
        int oldY = y;
        int oldX = x;
        if (maze[y+1][x]=='|'||maze[y+1][x]=='J'||maze[y+1][x]=='L'){
            y++;
        } else if (maze[y-1][x]=='|'||maze[y-1][x]=='F'||maze[y-1][x]=='7') {
            y--;
        } else if (maze[y][x-1]=='-'||maze[y][x-1]=='F'||maze[y][x-1]=='L') {
            x--;
        } else if (maze[y][x+1]=='-'||maze[y][x+1]=='S'||maze[y][x+1]=='J') {
            x++;
        } else {
            System.out.println("not surrounded??");
        }
        while (maze[y][x]!='S'){
            if(maze[y][x]=='.'){
                System.out.println("error");
                break;
            } else if (maze[y][x]=='|') {
                if (oldY + 1 == y) {
                    oldX=x;
                    oldY=y;
                    y=y+1;
                    count++;
                } else {
                    oldX=x;
                    oldY=y;
                    y=y-1;
                    count++;
                }
            } else if (maze[y][x]=='-') {
                if (oldX +1 == x) {
                    oldX=x;
                    oldY=y;
                    x=x+1;
                    count++;
                } else {
                    oldX=x;
                    oldY=y;
                    x=x-1;
                    count++;
                }
            } else if (maze[y][x]=='L') {
                if (oldY+1 == y) {
                    oldX=x;
                    oldY=y;
                    x=x+1;
                    count++;
                } else {
                    oldX=x;
                    oldY=y;
                    y=y-1;
                    count++;
                }
            } else if (maze[y][x]=='J') {
                if (oldY+1 == y) {
                    oldX=x;
                    oldY=y;
                    x=x-1;
                    count++;
                } else {
                    oldX=x;
                    oldY=y;
                    y=y-1;
                    count++;
                }
            } else if (maze[y][x]=='7') {
                if (oldY-1 == y) {
                    oldX=x;
                    oldY=y;
                    x=x-1;
                    count++;
                } else {
                    oldX=x;
                    oldY=y;
                    y=y+1;
                    count++;
                }
            } else if (maze[y][x]=='F'){
                if (oldY-1 == y) {
                    oldX=x;
                    oldY=y;
                    x=x+1;
                    count++;
                } else {
                    oldX=x;
                    oldY=y;
                    y=y+1;
                    count++;
                }
            } else {
                System.out.println("Major error");
            }
        }
        return count/2;
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