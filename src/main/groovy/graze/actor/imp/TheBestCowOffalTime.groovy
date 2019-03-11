package graze.actor.imp

import graze.actor.*
import graze.pasture.Pasture

class TheBestCowOffalTime extends Grazer {
    Random brain = new Random()

    String getIcon() {
        return 'ThisIsTheLongestNameACowCanHaveAndYouAreFreeToProveMeWrong'
    }

    Move move(Pasture surroundings) {
        return Move.values()[brain.nextInt(Move.values().length)]
    }

    Action act(Pasture surroundings) {
        return Action.EAT
    }
}
