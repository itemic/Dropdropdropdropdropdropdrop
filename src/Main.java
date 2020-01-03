import java.util.Scanner;
import java.util.Random;
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Board b = new Board();
        b.printBoard();
        b.place(0,0, new Stone(2));
        b.place(0,1, new Stone(2));
        b.place(0,2, new Stone(2));
        b.place(0,3, new Stone(2));
        b.place(0,4, new Stone(2));
        b.place(0,5, new Stone(2));
        b.place(0,6, new Stone(2));
//        b.printBoard();
        b.trickle();
        b.resolve();
        b.printBoard();

        Scanner scanner = new Scanner(System.in);
        b.printAtStart();
        boolean canContinuePlaying = true;
        while (canContinuePlaying) {
            canContinuePlaying = b.takeTurn(scanner.nextInt());
        }

//        int next = new Random().nextInt(6)+1;
//        System.out.print("next stone: " + next+"\n");
//        while (scanner.hasNextInt()) {
//            b.dropAt(scanner.nextInt());
//            b.trickle();
//            b.resolve();
//            b.printBoard();
//            next = new Random().nextInt(6)+1;
//            System.out.print("next stone: " + next+"\n");
//        }
//        b.printBoard();
    }
}
