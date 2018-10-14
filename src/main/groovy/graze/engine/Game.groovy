package graze.engine

import graze.pasture.*
import graze.actors.*
import graze.actors.imp.*

class Game {

    def pastureGenerator = new PastureGenerator()
            .width(50)
            .height(50)
            .tiles([(Tile.DIRT): 50, (Tile.GRASS): 50] as WeightedMap)
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
        def tileCount = pasture.size() * pasture[0].size()
        if (actors.size() > tileCount) {
            throw new IllegalStateException("Map is too small for all actors to be placed")
        }

        // Place actors
        def random = new Random()
        actors.each { actor ->
            while (actor.x == 0  && actor.y == 0) {
                // Pick some coordinates
                def x = random.nextInt(pasture.size())
                def y = random.nextInt(pasture[0].size())

                // Put the actor there if there's no one else there
                if(!actors.any { it.x == x && it.y == y }) {
                    actor.x = x
                    actor.y = y
                }
            }
        }
    }
}
