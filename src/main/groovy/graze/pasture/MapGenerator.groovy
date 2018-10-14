package graze.pasture

class PastureGenerator {

    int width = 50
    int height = 50
    WeightedMap tiles = [(Tile.DIRT): 1]
    Tile border = null

    /**
    *   Returns a 2D array of tiles
    */
    def generate() {
        def pasture = []
        for (int w = 0; w < width; w++) {
            pasture[w] = []
            for (int h = 0; h < height; h++) {
                def isBorder = border && ((w == 0 || w == width -1) || (h == 0) || h == height - 1)
                pasture[w][h] = isBorder ? border : tiles.pick()
            }
        }
        return pasture
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
