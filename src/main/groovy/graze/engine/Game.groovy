package graze.engine

import graze.actor.*
import graze.pasture.*


class Game {
    final Pasture pasture
    final Map<Cow, Attributes> cows

    Game(Setup setup) {
        pasture = setup.newPasture()
        cows = setup.newCows()
    }


}