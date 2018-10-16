package graze.pasture

import graze.actor.Actor

@groovy.transform.EqualsAndHashCode
class Tile {
    final String id = UUID.randomUUID().toString()
    final ArrayList<Actor> actors = []

    @Override
    String toString() {
        return "($id): $actors"
    }
}
