package graze.pasture

enum Tile {
    FENCE(true),
    DIRT(false),
    GRASS(false)

    final boolean isObstacle

    Tile(boolean isObstacle) {
        this.isObstacle = isObstacle
    }
}
