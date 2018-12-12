package graze.pasture

import graze.actor.imp.Grass

class PastureGenerator {

    int width = 27
    int height = 27
    WeightedMap actors = WeightedMap.from([
        (Grass.class): 1,
        (null): 1
    ])

    /**
    *   Returns a 2D array of tiles
    */
    Pasture generate() {
        def pasture = new Pasture(width, height)
        width.times { x ->
            height.times { y ->
                def tile = pasture.getTile(x, y)
                def pickedActor = actors.pick()
                if (pickedActor != null && pickedActor != "null") { // FIXME: why does null end up as a string?
                    tile.actors.add(pickedActor.newInstance())
                    return
                }
            }
        }
        return pasture
    }

    // Factory methods below

    /**
    *   Sets the width of the pasture to generate
    */
    PastureGenerator width(int w) {
        width = w
        return this
    }
    
    /**
    *   Sets the height of the pasture to generate
    */
    PastureGenerator height(int h) {
        height = h
        return this
    }
    
    
    /**
    *   Sets the actors to use
    */
    PastureGenerator actors(WeightedMap a) {
        actors = a
        return this
    }
}
