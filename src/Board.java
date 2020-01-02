import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    public static int BOARD_SIZE = 7;
    private Stone[][] board;

    public Board() {
        board = new Stone[BOARD_SIZE][BOARD_SIZE];
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] == null ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---");
    }

    public void dropStoneAtPosition(int pos) {
        if (pos >= 0 || pos < BOARD_SIZE) {

        }
    }

    public void place(int x, int y, Stone stone) {

        board[x][y] = stone;
        trickle();
    }

    public void resolve() {
        trickle();
        boolean changeDetected = false;

        do {
            changeDetected = false;
            Set<Coordinate> toDestroy = new HashSet<>();
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    // check vertical
//                    System.out.println("resolving " + i + ", " + j + " val: " + (board[i][j] == null ? "X" : board[i][j]));
                    boolean resolution = resolveVerticalAt(i, j);
                    if (resolution) {
                        changeDetected = changeDetected || resolution;
                        toDestroy.add(new Coordinate(i, j));
                    }

                    // check horizontal
                    resolution = resolveHorizontalAt(i, j);
                    if (resolution) {
                        changeDetected = changeDetected || resolution;
                        toDestroy.add(new Coordinate(i, j));
                    }



                }



//                printBoard();
            }
            for (Coordinate c: toDestroy) {
//                    System.out.println("Destroying..." + c.x + ", " + c.y);
                board[c.x][c.y] = null;

                if (c.x+1 < BOARD_SIZE && board[c.x+1][c.y] != null) {board[c.x+1][c.y].shatter();}
                if (c.x-1 >= 0 && board[c.x-1][c.y] != null) {board[c.x-1][c.y].shatter();}
                if (c.y+1 < BOARD_SIZE && board[c.x][c.y+1] != null) {board[c.x][c.y+1].shatter();}
                if (c.y-1 >= 0 && board[c.x][c.y-1] != null) {board[c.x][c.y-1].shatter();}


            }
            toDestroy.clear();
            trickle();
        } while (changeDetected);

        //1. go through each element and check if it should be removed
        //2. if so, remove and continue onwards
        //3. trickle()
        //4. if changeDetected, run resolve again


    }

    private boolean resolveVerticalAt(int x, int y) {
        boolean change = false;
        if (board[x][y] == null) return false;
        if (board[x][y].isRevealed()) {
            int nullCtr = 0;
            for (int a = 0; a < BOARD_SIZE; a++) {
                if (board[a][y] == null) { nullCtr++; }
            }
            int height = BOARD_SIZE - nullCtr;
//            System.out.println("height is " + height);
            if (height == board[x][y].getValue()) {
                change = true;
            }
//            System.out.println("vertical resolved at: " + x + ", " + y +": " + board[x][y] + "  h" + height);
        }
//        if (change) System.out.println("mark to destroy... " + board[x][y]);
        return change;
    }

    private boolean resolveHorizontalAt(int x, int y) {
        boolean change = false;
        if (board[x][y] == null) return false;
        if (board[x][y].isRevealed()) {
            int j = y-1;
            // go left and right until we hit end or null
            // let's go left first
            int leftCtr = 0;
            while (j >= 0) {
                if (board[x][j] != null) {
                    leftCtr++;
                    j--;
                } else {
                    break;
                }
            }
            j = y +1;
            int rightCtr = 0;
            while (j < BOARD_SIZE) {
                if (board[x][j] != null) {
                    rightCtr++;
                    j++;
                } else {
                    break;
                }
            }
            int length = leftCtr + rightCtr + 1;
//            System.out.println("ctrs: L" + leftCtr + " R" + rightCtr);
            if (length == board[x][y].getValue()) {
                change = true;
            }
        }
//        System.out.println("horizont resolved at: " + x + ", " + y);
//        if (change) System.out.println("mark to destroy...");
        return change;
    }

    public void trickle() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            int size = 0;
            Stone[] compressed = new Stone[BOARD_SIZE];
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[j][i] != null) {
//                    System.out.println("value: " + board[j][i]);
                    compressed[size] = board[j][i];
                    size++;
                }
            }

//            for (Stone e: compressed) {
//                System.out.print(e + " ");
//            }
//            System.out.println();
            int nullSize = BOARD_SIZE - size;
            for (int j = 0; j < nullSize; j++) {
                board[j][i] = null;
            }

            for (int j = nullSize; j < BOARD_SIZE; j++) {
//                System.out.println("error: " + nullSize + " " + j);
                board[j][i] = compressed[j - nullSize];
            }
        }



        //
        for (int i = 0; i < BOARD_SIZE; i++) {
//            board[BOARD_SIZE - 1][i] = new Stone(8);
//            System.out.println("post: " + board[BOARD_SIZE - 1][i]);
            if (board[BOARD_SIZE - 1][i] == null) {
                // if null, go up and find first element
                for (int j = BOARD_SIZE-2; j >= 0; j--) {
                    if (board[j][i] != null) {
                        // get difference, drag everything down
                        int delta = Math.abs(j - (BOARD_SIZE-1));

                        //make temporary array to rotate
                        Stone[] temp = new Stone[delta];
                        int ctr = 0;

                        for (int x = BOARD_SIZE - delta; x < BOARD_SIZE; x++) {
                            temp[ctr] = board[x][i];
                        }

                        for (int x = (BOARD_SIZE - delta) - 1; x >= 0; x--) {

                            board[x+delta][i] = board[x][i];
                        }

                        for (int x = 0; x < delta; x++) {
                            board[x][i] = null;
                        }
                        break;
                    }
                }
            }
        }
    }
}

