package fi.hallo.mapgen;

import java.util.Random;

public class Hex {

    public enum Type {

        GRASSLAND, HILL, FOREST, MOUNTAIN, WASTELAND, WATER, PLAIN
    };

    /**
     * GRASSLAND: Ruins, Stone, Wood, Weirwood 
     * HILL: Ruins, Wood, Iron, Gold, Silver 
     * FOREST: Ruins, Stone, Wood(automatic), Weirwood 
     * MOUNTAIN: Ruins, Iron, Gold, Silver 
     * WASTELAND: Ruins, Iron, Gold, Silver, Stone, Wood, Weirwood 
     * WATER: Island 
     * PLAIN: Ruins, Stone, Wood, Weirwood
     */
    public enum Feature {

        NONE, RUINS, STONE, WOOD, WEIRWOOD, IRON, GOLD, SILVER, ISLAND
    };

    private Type type;
    private Feature[] features;

    public Hex(Type type) {
        this.type = type;
        this.features = new Feature[1];
        this.features[0] = Feature.NONE;
        createFeatureByType(type);
    }

    public Type getType() {
        return type;
    }

    public Feature[] getFeatures() {
        return features;
    }

    private void createFeatureByType(Type type){
        //Tällä hetkellä ~25% hexoista omaa featuren(pl vesi.)
        Random rand = new Random();
        double val;

        if (type == Type.GRASSLAND) {
            val = rand.nextDouble();
            if (val < 0.05){
                addFeature(Feature.RUINS);
            }
            //Ruins 0.05
            val = rand.nextDouble();
            if (val < 0.1){
                addFeature(Feature.STONE);
            }
            //Stone 0.1
            val = rand.nextDouble();
            if (val < 0.1){
                addFeature(Feature.WOOD);
                val = rand.nextDouble();
                if (val < 0.02){
                    addFeature(Feature.WEIRWOOD);
                }
            }
            //Wood 0.1, Weirwood 0.02(if wood)
        } else if (type == Type.HILL) {
            val = rand.nextDouble();
            if (val < 0.125){
                addFeature(Feature.RUINS);
            }
            //Ruins 0.125
            val = rand.nextDouble();
            if (val < 0.05){
                addFeature(Feature.WOOD);
            }
            //Wood 0.05
            val = rand.nextDouble();
            if (val < 0.075){
                addFeature(Feature.IRON);
            }
            //Iron 0.075
            val = rand.nextDouble();
            if (val < 0.025){
                addFeature(Feature.GOLD);
            }
            //Gold 0.025
            val = rand.nextDouble();
            if (val < 0.05){
                addFeature(Feature.SILVER);
            }
            //Silver 0.05
        } else if (type == Type.FOREST) {
            val = rand.nextDouble();
            if (val < 0.05){
                addFeature(Feature.RUINS);
            }
            //Ruins 0.05
            val = rand.nextDouble();
            if (val < 0.075){
                addFeature(Feature.STONE);
            }
            //Stone 0.075
            addFeature(Feature.WOOD);
            val = rand.nextDouble();
            if (val < 0.02){
                addFeature(Feature.WEIRWOOD);
            }
            //Wood(automatic) 1, Weirwood 0.02
        } else if (type == Type.MOUNTAIN) {
            val = rand.nextDouble();
            if (val < 0.15){
                addFeature(Feature.RUINS);
            }
            //Ruins 0.15
            val = rand.nextDouble();
            if (val < 0.1){
                addFeature(Feature.IRON);
            }
            //Iron 0.1
            val = rand.nextDouble();
            if (val < 0.05){
                addFeature(Feature.GOLD);
            }
            //Gold 0.05
            val = rand.nextDouble();
            if (val < 0.075){
                addFeature(Feature.SILVER);
            }
            //Silver 0.075
        } else if (type == Type.WASTELAND) {
            val = rand.nextDouble();
            if (val < 0.04){
                addFeature(Feature.RUINS);
            }
            //Ruins 0.04
            val = rand.nextDouble();
            if (val < 0.04){
                addFeature(Feature.IRON);
            }
            //Iron 0.02
            val = rand.nextDouble();
            if (val < 0.02){
                addFeature(Feature.GOLD);
            }
            //Gold 0.04
            val = rand.nextDouble();
            if (val < 0.04){
                addFeature(Feature.SILVER);
            }
            //Silver 0.04
            val = rand.nextDouble();
            if (val < 0.04){
                addFeature(Feature.STONE);
            }
            //Stone 0.04
            val = rand.nextDouble();
            if (val < 0.04){
                addFeature(Feature.WOOD);
                val = rand.nextDouble();
                if (val < 0.02){
                    addFeature(Feature.WEIRWOOD);
                }
            }
            //Wood 0.04, Weirwood 0.02(if forest)
        } else if (type == Type.WATER) {
            val = rand.nextDouble();
            if (val < 0.05){
                addFeature(Feature.ISLAND);
            }
            //Island 0.05
        } else if (type == Type.PLAIN) {
            val = rand.nextDouble();
            if (val < 0.1){
                addFeature(Feature.RUINS);
            }
            //Ruins 0.1
            val = rand.nextDouble();
            if (val < 0.1){
                addFeature(Feature.STONE);
            }
            //Stone 0.1
            val = rand.nextDouble();
            if (val < 0.1){
                addFeature(Feature.WOOD);
                val = rand.nextDouble();
                if (val < 0.02){
                    addFeature(Feature.WEIRWOOD);
                }
            }
            //Wood 0.1, Weirwood 0.02(if forest)
        }
    }

    //adds specified feature to features list.
    public void addFeature(Feature feature){
        //no features yet, we can just overwrite.
        if (features[0] == Feature.NONE){
            features[0] = feature;
        }
        else{
            int len = features.length + 1;
            Feature[] nArr = new Feature[len];
            System.arraycopy(features, 0, nArr, 0, features.length);
            nArr[features.length] = feature;
            features = nArr;
        }
    }

    public String[] listFeatures(){
        StringBuilder me = new StringBuilder();
        for (int i = 0; i < features.length-1; i++) {
            me.append(features[i] + ",");

        }
        me.append(features[features.length-1]);

        return me.toString().split(",");
    }
}