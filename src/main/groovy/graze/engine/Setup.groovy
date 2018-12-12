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

    ArrayList<Actor> newActors() {
        config["actors"].collectMany { className, count ->
            def actors = []
            count.times { 
                def actor = Class.forName(className).newInstance()
                actors.add(actor)
            }
            return actors
        }
    }

    void placeActors(ArrayList actors, Pasture pasture) {
        def random = new Random()
        actors.each { actor ->
            def x = random.nextInt(pasture.width)
            def y = random.nextInt(pasture.height)
            pasture.getTile(x, y).actors.add(actor)
        }
    }
}
