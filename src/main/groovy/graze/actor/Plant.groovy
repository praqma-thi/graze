package graze.actor

import graze.actor.*
import graze.pasture.*


abstract class Plant extends Actor {
    void eat(Tile tile) {
        // Nothing
    }

    @Override
    void getHungry(Pasture pasture) {
        // Nothing
    }
}
