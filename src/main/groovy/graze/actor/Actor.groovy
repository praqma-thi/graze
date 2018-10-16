package graze.actor

@groovy.transform.EqualsAndHashCode
abstract class Actor {
    final String id = UUID.randomUUID().toString()
    abstract Action act(def surroundings)
    abstract Move move(def surroundings)
}
