package graze.pasture

import graze.actor.*

class Pasture extends ArrayList<ArrayList<Tile>> {
    Pasture(int width, int height) {
        super(height)
        height.times { y ->
            this[y] = new ArrayList<Tile>(width)
            width.times { x ->
                this[y][x] = new Tile()
            }
        }
    }

    def allCows() {
        return this.flatten().collectMany { it.actors }.grep { it instanceof Cow }
    }

    def surroundingsOf(int x, int y) {
        def fromX = x - 1 < 0 ? 0  : x - 1
        def fromY = y - 1 < 0 ? 0  : y - 1
        def toX =   x + 1 >= width ? width : x + 2
        def toY =   y + 1 >= height ? height : y + 2
        this.subList(fromY, toY).collect{ it.subList(fromX, toX) }
    }

    def surroundingsOf(Actor actor) {
        def c = coordinatesOf(actor)
        return surroundingsOf(c.x, c.y)
    }

    Tile tileOf(Actor actor) {
        return this.flatten().find { it.actors.contains(actor) }
    }

    Coordinates coordinatesOf(Actor actor) {
        for (row in this) {
            for (tile in row) {
                if (actor in tile.actors) {
                    def x = row.indexOf(tile)
                    def y = this.indexOf(row)
                    return new Coordinates(x, y)
                }
            }
        }
        return new Coordinates(-1, -1)
    }

    Tile leftOf(Actor actor) {
        def c = coordinatesOf(actor)
        return isOutOfBounds(c.x - 1, c.y) ? null : this[c.y][c.x -1 ]
    }

    Tile rightOf(Actor actor) {
        def c = coordinatesOf(actor)
        return isOutOfBounds(c.x + 1, c.y) ? null : this[c.y][c.x + 1]
    }

    Tile above(Actor actor) {
        def c = coordinatesOf(actor)
        return isOutOfBounds(c.x, c.y - 1) ? null : this[c.y - 1][c.x]
    }

    Tile below(Actor actor) {
        def c = coordinatesOf(actor)
        return isOutOfBounds(c.x, c.y + 1) ? null : this[c.y + 1][c.x]
    }

    boolean isOutOfBounds(x, y) {
        return 0 > x || x >= width || 0 > y || y >= height
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
