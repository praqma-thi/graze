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

    Pasture pasture = null
    Map<Actor, Attributes> actors = [:]

    /**
    * Creates a new pasture and places actors on it
    */
    void setup() {
        // Generate map
        pasture = pastureGenerator.generate()
        
        // Create actors
        actors.clear()
        10.times { actors[new RCow()] = new Attributes() }

        // Check if there's enough room for the actors
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
        actors.each { actor, attributes ->
            while (attributes.x == -1  || attributes.y == -1) {
                // Pick some coordinates
                def x = random.nextInt(pasture.width)
                def y = random.nextInt(pasture.height)

                // Try again if there's already someone there
                if (actors.attributes.any { it.x == x && it.y == y }) {
                    continue
                }

                // Try again if it's an obstacle
                if (pasture[x][y].isObstacle) {
                    continue
                }

                attributes.y = y
                attributes.x = x
            }
        }
    }
}
