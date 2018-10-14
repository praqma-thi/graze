package graze.actors

@groovy.transform.EqualsAndHashCode
abstract class Cow implements Actor {
    String id = UUID.randomUUID().toString()

    int food = 0
    int poop = 0
    int x = -1
    int y = -1

    abstract Action takeTurn(def surroundings)
}
