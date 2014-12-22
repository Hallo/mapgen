package fi.hallo.mapgen;

public class HexMap {
    private int height, width = 0;
    private Hex[][] map;

    public HexMap(int height, int width) {
        this.height = height;
        this.width = width;
        map = new Hex[height][width];
    }
}
