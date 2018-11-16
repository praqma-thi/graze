package graze.engine

import graze.actor.*
import graze.pasture.*
import graze.actor.*

class Game {
    final Pasture pasture
    final ArrayList<Cow> cows

    Game(Setup setup) {
        pasture = setup.newPasture()
        cows = setup.newCows()
        setup.placeCows(cows, pasture)
    }

    void run() {
        def i = 0
        while (i < 5) {
            println "Dawn of day $i"
            i++ // just run five turns for now
            playTurn()

            def survivingCows = pasture.allCows().collect { it.class }.unique()
            switch (survivingCows.size()) {
                case 0:
                    println "Wow. They all died. Stalemate?"
                    System.exit(0)
                    break
                case 1:
                    println "Winner winner chicken dinner!"
                    println "<<< ${survivingCows.first().name} >>>"
                    System.exit(1)
                    break
            }
            System.sleep(2)
        }
    }

    void playTurn() {
        def allCows = pasture.allCows()
        allCows.each { cow ->
            CowBehaviour.move(cow, pasture, cow.move(pasture.surroundingsOf(cow)))
        }
        allCows.each { cow ->
            CowBehaviour.act(cow, pasture, cow.act(pasture.surroundingsOf(cow)))
        }
    }
}
