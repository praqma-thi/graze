package graze.actor

import graze.pasture.Pasture

@groovy.transform.EqualsAndHashCode
abstract class Actor {
    final String id = UUID.randomUUID().toString()
    final String tag = "${this.class.simpleName}:${this.id.substring(0, 5)}"

    int food = 3
    int poop = 0

    abstract String getIcon()
    abstract Action act(Pasture surroundings)
    abstract Move move(Pasture surroundings)
}
