package graze.actor

class Grass extends Actor {
    Move move(def surroundings) {
        return Move.STAND
    }

    Action act(def surroundings) {
        return Action.PASS
    }
}
