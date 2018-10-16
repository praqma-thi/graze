package graze.pasture

@groovy.transform.EqualsAndHashCode
class Coordinates {
    final int x
    final int y

    Coordinates(int x, int y) {
        this.x = x
        this.y = y
    }

    @Override
    String toString() {
        return "x:$x, y:$y"
    }
}