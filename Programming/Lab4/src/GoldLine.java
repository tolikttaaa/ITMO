public class GoldLine extends Item {
    private static int ind = 1;

    GoldLine (String name) {
        super (name);
    }

    GoldLine () {
        super ("Gold Line" + ind++);
    }

    @Override
    public GoldPiece produce() {
        //TODO
        this.breakDown();
        return new GoldPiece();
    }
}