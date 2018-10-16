package graze.engine

import graze.pasture.*
import graze.actor.*
import graze.actor.imp.*

class Setup {

    PastureGenerator pastureGenerator = new PastureGenerator()
            .width(50)
            .height(50)
            .tiles(WeightedMap.from([(Tile.DIRT): 50, (Tile.GRASS): 50]))
            .border(Tile.FENCE)

    /**
    * Creates a new pasture and places cows on it
    */
    Pasture newPasture() {
        // Generate map
        return pastureGenerator.generate()
    }

    Map<Cow, Attributes> newCows(Pasture pasture) {
        Map<Cow, Attributes> cows = [:]

        // Add cows
        10.times { cows[new RCow()] = new Attributes() }

        // Check if there's enough room for the cows
        def tileCount = pasture.flatten().grep { !it.isObstacle }.size()
        if (cows.size() > tileCount) {
            throw new IllegalStateException("""\
                Map is too small for all cows to be placed.
                Try again with less cows, a bigger map, or
                less obstacles.
                """.stripIndent())
        }

        // Place cows
        def random = new Random()
        cows.each { cow, attributes ->
            while (attributes.x == -1  || attributes.y == -1) {
                // Pick some coordinates
                def x = random.nextInt(pasture.width)
                def y = random.nextInt(pasture.height)

                // Try again if there's already someone there
                if (cows.attributes.any { it.x == x && it.y == y }) {
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

        return cows
    }
}
