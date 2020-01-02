import java.util.Scanner;
import java.util.Random;
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Board b = new Board();
        b.printBoard();
        b.place(6, 2, 5);
        b.place(1, 0, 3);
//        b.printBoard();
        b.trickle();
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
