package graze.pasture

class Pasture extends ArrayList<ArrayList<Tile>> {
    Pasture(int width, int height) {
        super(height)
        height.times { y ->
            this[y] = new ArrayList<Tile>(width)
        }
    }

    def surroundingsOf(x, y) {
        def fromX = x - 1 < 0 ? 0  : x - 1
        def fromY = y - 1 < 0 ? 0  : y - 1
        def toX =   x + 1 >= width ? width : x + 2
        def toY =   y + 1 >= height ? height : y + 2
        this.subList(fromY, toY).collect{ it.subList(fromX, toX) }
    }

    Tile getTile(int x, int y) {
        return this[y][x]
    }

    void setTile(int x, int y, Tile tile) {
        this[y][x] = tile
    }

    int getHeight() {
        return this.size()
    }

    int getWidth() {
        return this[0].size()
    }
}
