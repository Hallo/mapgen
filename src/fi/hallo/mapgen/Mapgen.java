package fi.hallo.mapgen;

import fi.hallo.mapgen.HexMap.Region;

public class Mapgen {


    public static void main(String[] args) {
        HexMap hm = new HexMap(50, 50, Region.NORTH);

        hm.createMap();
    }
}
