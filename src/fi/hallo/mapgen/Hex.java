package fi.hallo.mapgen;


public class Hex {

    public enum Type {GRASSLAND, HILL, FOREST, MOUNTAIN, WASTELAND, ISLAND, WATER, PLANE};

    private Type type;

    public Hex(Type type) {
        this.type = type;
    }
}
