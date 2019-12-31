import java.util.Scanner;
import java.util.Random;
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Board b = new Board();
        b.printBoard();
        b.place(5, 0, 3);
        b.place(6, 0,1);
        b.place(6, 1, 2);
        b.place(6, 2, 4);
        b.place(6, 3, 4);
        b.place(5, 3, 1);
        b.place(5, 2, 1);
        b.place(6,4,5);
        b.place(5,4,5);
        b.place(6, 5, 6);
        b.place(6, 6, 1);
        b.printBoard();
        b.resolve();
        b.printBoard();

        Scanner scanner = new Scanner(System.in);
        int next = new Random().nextInt(6)+1;
        System.out.print("next stone: " + next+"\n");
        while (scanner.hasNextInt()) {
            b.place(0, scanner.nextInt(), next);
            b.trickle();
            b.resolve();
            b.printBoard();
            next = new Random().nextInt(6)+1;
            System.out.print("next stone: " + next+"\n");
        }
        b.printBoard();
    }
}
