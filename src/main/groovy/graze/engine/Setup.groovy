package graze.engine

import graze.pasture.*
import graze.actor.*
import graze.actor.imp.*

class Setup {

    Config config
    
    Setup(Config config) {
        this.config = config
    }

    Pasture newPasture() {
        new PastureGenerator()
            .width(config["pasture.width"])
            .height(config["pasture.height"])
            .generate()
    }

    ArrayList<Cow> newCows() {
        config["cows"].collectMany { className, count ->
            def cows = []
            count.times { 
                def cow = Class.forName(className).newInstance()
                cows.add(cow)
            }
            return cows
        }
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
