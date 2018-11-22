class GoldPiece extends Item implements Comparable<GoldPiece> {
    private double size; // size is the double in range of 0 to 10
    private static int ind = 1;

    GoldPiece() {
        this.name = "piece" + ind++ + " of gold";
        this.size = Math.random() * 10;
        this.hp = 1.0d;
    }

    private String sizeToString() {
        StringBuilder res = new StringBuilder();
        if (this.size <= 0) {
            System.err.println("Size of " + this.name + " isn't correct!!!");
        } else if (this.size <= 2.5) {
            res.append("small");
        } else if (this.size <= 5.0) {
            res.append("medium");
        } else if (this.size <= 7.5) {
            res.append("large");
        } else if (this.size <= 10.0) {
            res.append("huge");
        } else {
            System.err.println("Size of " + this.name + " isn't correct!!!");
        }
        return res.toString();
    }

    @Override
    public void breakDown() {}

    @Override
    public String toString() {
        return sizeToString() + " " + this.name;
    }

    @Override
    public int compareTo(GoldPiece that) {
        return Math.abs(this.size - that.size) < EPS ? 0 : ((this.size - that.size) >= EPS ? 1 : -1);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        GoldPiece other = (GoldPiece) that;
        return this.name.equals(other.name) && (Math.abs(this.hp - other.hp) < EPS) &&
                (Math.abs(this.size - other.size) < EPS);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + Double.hashCode(this.size) + Double.hashCode(this.hp);
    }
}