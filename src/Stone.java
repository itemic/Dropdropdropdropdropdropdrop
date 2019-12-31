import java.util.Random;

public class Stone {
    private int value;
    private int layer;

    public Stone(int value, int layer) {
        this.value = value;
        this.layer = layer;
    }

    public Stone(int layer) {
        this.value = new Random().nextInt(7) + 1;
        this.layer = layer;
    }

    public void shatter() {
        if (this.layer > 0) {
            this.layer--;
        }
    }

    public boolean isRevealed() {
        return this.layer == 0;
    }

    public int getValue() { // always check if revealed
        return this.value;
    }

    public String toString() {
        if (this.layer == 2) {
            return "O";
        } else if (this.layer == 1) {
            return "o";
        } else {
            return this.value + "";
        }
    }
}
