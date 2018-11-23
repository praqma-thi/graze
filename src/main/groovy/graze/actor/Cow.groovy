package graze.actor

abstract class Cow extends Actor {
    int food = 3
    int poop = 0
    String tag = "${this.class.simpleName}:${this.id.substring(0, 5)}"
}
