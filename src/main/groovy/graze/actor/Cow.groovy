package graze.actor

@groovy.transform.EqualsAndHashCode
abstract class Cow {
    String id = UUID.randomUUID().toString()

    abstract Action takeTurn(def surroundings)
}
