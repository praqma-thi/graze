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
        Canvas.instance.pasture = pasture
    }

    void run() {
        def dayCount = 0
        def gameOver = false
        while (!gameOver) {        
            dayCount++
            Canvas.instance.title "Dawn of day $dayCount"
            doTurn(dayCount)

            gameOver = endGame()

            Canvas.instance.paint()
        }
    }

    boolean endGame() {
        def survivingCows = pasture.allCowClasses()
        switch (survivingCows.size()) {
            case 0:
                Canvas.instance.message "Wow. They all died. Stalemate?"
                return true
            case 1:
                Canvas.instance.message "Winner winner chicken dinner!"
                Canvas.instance.message "<<< ${survivingCows.first()} >>>"
                return true
            default:
                return false
        }
    }

    void doTurn(int turnCount) {
        def allCows = pasture.allCows()
        allCows.each { cow -> CowBehaviour.move(cow, pasture, cow.move(pasture.surroundingsOf(cow))) }
        allCows.each { cow -> CowBehaviour.act(cow, pasture, cow.act(pasture.surroundingsOf(cow))) }
        if (turnCount % 3 == 0) { allCows.each { cow -> CowBehaviour.getHungry(cow, pasture) } }
    }
}
