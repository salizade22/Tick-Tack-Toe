/*
 CS 111
Final Project
Shakhzod Ali-zade
*/
import java.util.*;
public class Tick {

    public static char[][] a = new char[4][4];
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        boolean bot = false;
        System.out.println("Welcome to Tick-tack-toe!\n You can play against computer or against your friend. Computer is not very smart, but it can predict the last winning move, so be careful:) ");
	System.out.println();
        System.out.println("To place a character, you should enter the coordinates(row and column) of the square. For example:\n if you want to place a character into upper left corner you should enter: 1 1 (1st row, 1st column)\n into the center : 2 2 (2nd row, 2nd column)\n into bottom right corner: 3 3 (3rd row, 3rd column) etc.");
	System.out.println();
        System.out.println("Let's start the game!");
	System.out.println();
        System.out.println("Do you want to play against computer or against your friend?\n If you want to play against computer press 1, else press 0\n");
        int g = kb.nextInt();
        if(g == 1)bot = true;
        int playerOneTurn = 0; // if it's 1, then second player moves first
        if(bot == true){
            System.out.println("If you want to move first press 0, else press 1");
            playerOneTurn = kb.nextInt();
        }
        Random myrand = new Random();
        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){
                a[i][j] = '.';
            }
        }
        for(int it = 0; ; it ^= 1){
            if(it == playerOneTurn){
                if(printTable()){
                    System.out.println("Draw. Friensdhip wins!");
                    break;
                }
                System.out.println("Player's turn. \n What's your move?");
                int x = kb.nextInt();
                int y = kb.nextInt();
                if(!valid(x, y)){
                    System.out.println("Seems like you made a wrong move... Please try again.");
                    it ^= 1;
                    continue;
                }
                upd(x, y, it);
                if(winState()){
                    printTable();
                    System.out.println("Player wins!!! Congratulations!!!");
                    break;
                }
            }
            else{
                if(bot){
                    boolean gg = false;
                    for(int i = 1; i < 4; i++){
                        for(int j = 1; j < 4 && gg == false; j++){
                            if(a[i][j] == '.'){
                                upd(i, j, it);
                                if(winState()){
                                    gg = true;
                                    System.out.println("Computer's move is " + i + " " + j);
                                    printTable();
                                    System.out.println("Computer won");
                                }
                                a[i][j] = '.';
                            }
                        }
                    }
                    if(gg == true){
                        break;
                    }
                    int rnd = myrand.nextInt(), cnt = 0;
                    rnd = Math.abs(rnd);
                    for(int i = 1; i < 4; i++){
                        for(int j = 1; j < 4; j++){
                            cnt += a[i][j] == '.' ? 1 : 0;
                        }
                    }
                    if(cnt == 0){
                        System.out.println("Draw. Friendship wins!");
                        break;
                    }
                    rnd %= cnt;
                    rnd++;
                    for(int i = 1; i < 4; i++){
                        for(int j = 1; j < 4; j++){
                            if(a[i][j] == '.'){
                                rnd--;
                                if(rnd == 0){
                                    System.out.println("Computer's move is " + i + " " + j);
                                    upd(i, j, it);
                                }
                            }
                        }
                    }
                }
                else{
                    if(printTable()){
                        System.out.println("Draw. Friendship wins!");
                        break;
                    }
                    System.out.println("Time for Player 2 to make a move.\n What's your move?");
                    int x = kb.nextInt();
                    int y = kb.nextInt();
                    if(!valid(x, y)){
                        System.out.println("Seems like you made a wrong move.... Please try again.");
                        it ^= 1;
                        continue;
                    }
                    upd(x, y, it);
                    if(winState()){
                        printTable();
                        System.out.println("Player 2 wins!!! Congratulations!!!");
                        break;
                    }
                }
            }
        }
        
    }
    
    public static boolean valid(int x, int y)
    {
        if(x < 1 || y < 1 || x > 3 || y > 3) return false;
        if(a[x][y] != '.')return false;
        return true;
    }
    
    public static boolean winState()
    {
        for(int i = 1; i < 4; i++){
            if(a[i][1] != '.' && a[i][1] == a[i][2] && a[i][1] == a[i][3]) return true;
            if(a[1][i] != '.' && a[1][i] == a[2][i] && a[1][i] == a[3][i]) return true;
        }
        if(a[1][1] != '.' && a[1][1] == a[2][2] && a[1][1] == a[3][3]) return true;
        if(a[1][3] != '.' && a[1][3] == a[2][2] && a[1][3] == a[3][1]) return true;
        return false;
    }
    
    public static void upd(int x, int y, int tp)
    {
        if(tp == 0){
            a[x][y] = 'X';
        }
        else{
            a[x][y] = 'O';
        }
    }
    public static boolean printTable(){
        int cnt = 0;
        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){
                cnt += a[i][j] == '.' ? 1 : 0;
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        return cnt == 0 ? true : false;
    }
    
}
