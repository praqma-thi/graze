package graze.engine

class MapGenerator {

    int width = 50
    int height = 50
    WeightedMap tiles = [(Tile.DIRT): 1]
    Tile border = null

    /**
    *   Returns a 2D array of tiles
    */
    def generate() {
        def map = []
        for (int w = 0; w < width; w++) {
            map[w] = []
            for (int h = 0; h < height; h++) {
                def isBorder = border && ((w == 0 || w == width -1) || (h == 0) || h == height - 1)
                map[w][h] = isBorder ? border : tiles.pick()
            }
        }
        return map
    }

    // Factory methods below

    /**
    *   Sets the width of the map to generate
    */
    MapGenerator width(int w) {
        width = w
        return this
    }
    
    /**
    *   Sets the height of the map to generate
    */
    MapGenerator height(int h) {
        height = h
        return this
    }
    
    
    /**
    *   Sets the map of tile densities to use
    */
    MapGenerator tiles(WeightedMap t) {
        tiles = t
        return this
    }

    /**
    *   Sets the border tile of the map
    */
    MapGenerator border(Tile b) {
        border = b
        return this
    }
}
