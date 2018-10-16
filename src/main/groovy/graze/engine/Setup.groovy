package graze.engine

import graze.pasture.*
import graze.actors.*
import graze.actors.imp.*

class Setup {

    PastureGenerator pastureGenerator = new PastureGenerator()
            .width(50)
            .height(50)
            .tiles(WeightedMap.from([(Tile.DIRT): 50, (Tile.GRASS): 50]))
            .border(Tile.FENCE)

    /**
    * Creates a new pasture and places actors on it
    */
    Pasture newPasture() {
        // Generate map
        return pastureGenerator.generate()
    }

    Map<Actor, Attributes> newActors(Pasture pasture) {
        Map<Actor, Attributes> actors = [:]

        // Add actors
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
                if (pasture.getTile(x, y).isObstacle) {
                    continue
                }

                attributes.x = x
                attributes.y = y
            }
        }

        return actors
    }
}
