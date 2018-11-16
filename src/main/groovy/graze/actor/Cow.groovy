package graze.actor

abstract class Cow extends Actor {
    int food = 0
    int poop = 0
    String tag = "${this.class.simpleName}:${this.id.substring(0, 5)}"

    abstract Move move(def surroundings)
    abstract Action act(def surroundings)
}
