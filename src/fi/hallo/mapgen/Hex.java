package fi.hallo.mapgen;


public class Hex {

    public enum Type {GRASSLAND, HILL, FOREST, MOUNTAIN, WASTELAND, WATER, PLAIN};

    private Type type;

    public Hex(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }


}
