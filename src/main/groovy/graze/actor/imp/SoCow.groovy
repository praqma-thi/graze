package graze.actor.imp

import graze.actor.*
import graze.pasture.Pasture

/**
* author: Beatrice Pendleton
*/

class SoCow extends Cow {

    String getIcon() {
        return 'B'
    }

    Move move(Pasture surroundings) {
        return Move.values()[new Random(13).nextInt(Move.values().length)]
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }
}
