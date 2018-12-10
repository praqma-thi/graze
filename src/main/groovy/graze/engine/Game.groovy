package graze.engine

import graze.actor.*
import graze.pasture.*
import graze.actor.*

class Game {
    final Config config
    final Pasture pasture
    final ArrayList<Cow> cows

    Game(Config config) {
        this.config = config

        Setup setup = new Setup(config)
        pasture = setup.newPasture()
        cows = setup.newCows()
        setup.placeCows(cows, pasture)

        Canvas.instance.pasture = pasture
    }

    void run() {
        int dayCount = 0
        boolean gameOver = false
        while (!gameOver) {
            dayCount++
            doTurn(dayCount)
            gameOver = endGame()

            if (config["canvas.enabled"]) {
                Canvas.instance.title "Dawn of day $dayCount"
                Canvas.instance.paint()
            }

            if (!gameOver) {
                System.sleep(config["loop.sleep"])
                continue
            }

            if (config["canvas.enabled"]) {
                if (config["loop.king"]) {
                    println pasture.allCowClasses()[0] ?: 'stalemate'
                } else {
                    println dayCount
                }
            }
        }
    }

    boolean endGame() {
        def survivingCows = pasture.allCowClasses()
        if (config["loop.king"]) {
            switch (survivingCows.size()) {
                case 0:
                    Canvas.instance.message "Wow. They all died. Stalemate?"
                    return true
                case 1:
                    Canvas.instance.message "Winner winner chicken dinner!"
                    Canvas.instance.message "<<< ${survivingCows[0]} >>>"
                    return true
                default:
                    return false
            }
        } else {
            return survivingCows.size() == 0
        }
    }

    void doTurn(int turnCount) {
        def allCows = pasture.allCows()
        allCows.each { cow -> CowBehaviour.move(cow, pasture, cow.move(pasture.surroundingsOf(cow))) }
        allCows.each { cow -> CowBehaviour.act(cow, pasture, cow.act(pasture.surroundingsOf(cow))) }
        if (turnCount % 3 == 0) { allCows.each { cow -> CowBehaviour.getHungry(cow, pasture) } }
    }
}
