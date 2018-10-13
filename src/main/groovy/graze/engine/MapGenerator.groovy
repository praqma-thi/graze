package graze.engine

class MapGenerator {

    int width = 50
    int height = 50
    WeightedMap tiles = [null: 1]
    Tile rim = null

    def generate() {
        def map = []
        for (int w = 0; w < width; w++) {
            map[w] = []
            for (int h = 0; h < height; h++) {
                def isRim = rim && ((w == 0 || w == width -1) || (h == 0) || h == height - 1)
                map[w][h] = isRim ? rim : tiles.pick()
            }
        }
        return map
    }

    // Factory methods below

    MapGenerator width(int w) {
        width = w
        return this
    }
    
    MapGenerator height(int h) {
        height = h
        return this
    }
    
    MapGenerator tiles(WeightedMap t) {
        tiles = t
        return this
    }

    MapGenerator rim(int r) {
        rim = r
        return this
    }
}
