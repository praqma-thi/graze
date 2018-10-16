import graze.actor.*

class RCow extends Cow {
    def brain = new Random()

    Action takeTurn(def surroundings) {
        return Action.values(brain.nextInt(Action.values().length))
    }
}
