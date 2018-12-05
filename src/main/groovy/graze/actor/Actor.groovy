package graze.actor

import graze.pasture.Pasture

@groovy.transform.EqualsAndHashCode
abstract class Actor {
    final String id = UUID.randomUUID().toString()
    abstract String getIcon()
    abstract Action act(Pasture surroundings)
    abstract Move move(Pasture surroundings)
}
