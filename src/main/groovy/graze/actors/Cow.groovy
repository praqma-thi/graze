package graze.actors

@groovy.transform.EqualsAndHashCode
abstract class Cow implements Actor {
    String id = UUID.randomUUID().toString()

    abstract Action takeTurn(def surroundings)
}
