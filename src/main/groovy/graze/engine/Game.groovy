package graze.engine

import graze.pasture.*
import graze.actors.*
import graze.actors.imp.*

class Game {

    def pastureGenerator = new PastureGenerator()
            .width(50)
            .height(50)
            .tiles(WeightedMap.from([(Tile.DIRT): 50, (Tile.GRASS): 50]))
            .border(Tile.FENCE)

    def pasture = [[]]
    def actors = []

    void setup() {
        // Generate map
        pasture = pastureGenerator.generate()
        
        // Create actors
        actors.clear()
        10.times { actors += new RCow() }

        // Generation sanity checks
        def tileCount = pasture.flatten().grep { !it.isObstacle }.size()
        if (actors.size() > tileCount) {
            throw new IllegalStateException("""\
                Map is too small for all actors to be placed.
                Try again with less actors, a bigger map, or
                less obstacles.
                """.stripIndent())
        }

        // Place actors
        def random = new Random()
        actors.each { actor ->
            while (actor.x == -1  || actor.y == -1) {
                // Pick some coordinates
                def x = random.nextInt(pasture.size())
                def y = random.nextInt(pasture[0].size())

                // Try again if there's already someone there
                if (actors.any { it.x == x && it.y == y }) {
                    continue
                }

                // Try again if it's an obstacle
                if (pasture[x][y].isObstacle) {
                    continue
                }

                actor.y = y
                actor.x = x
            }
        }
    }
}
