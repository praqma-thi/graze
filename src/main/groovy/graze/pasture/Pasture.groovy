package graze.pasture

class Pasture extends ArrayList<ArrayList<Tile>> {
    def surroundingsOf(x, y) {
        def fromX = x - 1 < 0 ? 0  : x - 1
        def fromY = y - 1 < 0 ? 0  : y - 1
        def toX =   x + 1 >= width ? width : x + 2
        def toY =   y + 1 >= height ? height : y + 2
        this.subList(fromY, toY).collect{ it.subList(fromX, toX) }
    }

    def getHeight() {
        return this.size()
    }

    def getWidth() {
        return this[0].size()
    }
}
