package graze.engine

import graze.actor.*
import graze.pasture.*


class Game {
    final Pasture pasture
    final ArrayList<Cow> cows

    Game(Setup setup) {
        pasture = setup.newPasture()
        cows = setup.newCows()
        setup.placeCows(cows, pasture)
    }
}
