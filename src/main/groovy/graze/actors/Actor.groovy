package graze.actors

interface Actor {
    String id = UUID.randomUUID().toString()

    Action takeTurn(def surroundings)
}
