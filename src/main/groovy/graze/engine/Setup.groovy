package graze.engine

import graze.pasture.*
import graze.actor.*
import graze.actor.imp.*

class Setup {

    PastureGenerator pastureGenerator = new PastureGenerator()

    /**
    * Creates a new pasture and places cows on it
    */
    Pasture newPasture() {
        return pastureGenerator.generate()
    }

    ArrayList<Cow> newCows() {
        def cows = []
        5.times { cows += new RCow() }
        5.times { cows += new SmartCow() }
        return cows
    }
    
    void placeCows(ArrayList cows, Pasture pasture) {
        def random = new Random()
        cows.each { cow ->
            def x = random.nextInt(pasture.width)
            def y = random.nextInt(pasture.height)
            pasture.getTile(x, y).actors.add(cow)
        }
    }
}
