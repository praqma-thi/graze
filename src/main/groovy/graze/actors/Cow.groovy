package graze.actors

@groovy.transform.EqualsAndHashCode
abstract class Cow {
    String id = UUID.randomUUID().toString()

    int food = 0
    int poop = 0
    int x = 0
    int y = 0

    abstract Action takeTurn(def surroundings)
}
