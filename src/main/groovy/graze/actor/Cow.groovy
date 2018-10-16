package graze.actor

abstract class Cow extends Actor {
    int food = 0
    int poop = 0

    abstract Move move(def surroundings)
    abstract Action act(def surroundings)
}
