package graze.pasture

class PastureGenerator {

    int width = 50
    int height = 50
    WeightedMap tiles = [(Tile.DIRT): 1]
    Tile border = null

    /**
    *   Returns a 2D array of tiles
    */
    Pasture generate() {
        def pasture = []
        for (int y = 0; y < height; y++) {
            pasture[y] = []
            for (int x = 0; x < width; x++) {
                def isBorder = border && ((y == 0 || y == height -1) || (x == 0) || x == width - 1)
                pasture[y][x] = isBorder ? border : tiles.pick()
            }
        }
        return pasture as Pasture
    }

    // Factory methods below

    /**
    *   Sets the width of the pasture to generate
    */
    PastureGenerator width(int w) {
        width = w
        return this
    }
    
    /**
    *   Sets the height of the pasture to generate
    */
    PastureGenerator height(int h) {
        height = h
        return this
    }
    
    
    /**
    *   Sets the pasture of tile densities to use
    */
    PastureGenerator tiles(WeightedMap t) {
        tiles = t
        return this
    }

    /**
    *   Sets the border tile of the pasture
    */
    PastureGenerator border(Tile b) {
        border = b
        return this
    }
}
