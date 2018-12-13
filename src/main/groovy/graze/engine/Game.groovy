package graze.engine

import graze.actor.*
import graze.actor.imp.*
import graze.pasture.*

class Game {
    final Config config
    final Pasture pasture
    final ArrayList<Actor> actors

    Game(Config config) {
        this.config = config

        Setup setup = new Setup(config)
        pasture = setup.newPasture()
        actors = setup.newActors()
        setup.placeActors(actors, pasture)

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

            if (!config["canvas.enabled"]) {
                if (config["loop.king"]) {
                    println getActorClasses()[0] ?: 'stalemate'
                } else {
                    println dayCount
                }
            }
        }
    }

    List<Class> getActorClasses() {
        return actors.grep { it instanceof Grazer }.collect { it.class }.unique()
    }

    boolean endGame() {
        def survivingActors = getActorClasses()
        if (config["loop.king"]) {
            switch (survivingActors.size()) {
                case 0:
                    Canvas.instance.message "Wow. They all died. Stalemate?"
                    return true
                case 1:
                    Canvas.instance.message "Winner winner chicken dinner!"
                    Canvas.instance.message "<<< ${survivingActors[0]} >>>"
                    return true
                default:
                    return false
            }
        } else {
            return survivingActors.size() == 0
        }
    }

    void doTurn(int turnCount) {
        List<Actor> removedActors = []
        actors.each { actor ->
            Pasture surroundings = pasture.surroundingsOf(actor)
            if (!surroundings) {
                removedActors.add(actor)
                return
            }

            actor.move(pasture, actor.move(surroundings))
        }
        actors.removeAll(removedActors)
        removedActors.clear()

        actors.each { actor ->
            Pasture surroundings = pasture.surroundingsOf(actor)
            if (!surroundings) {
                removedActors.add(actor)
                return
            }

            actor.act(pasture, actor.act(surroundings))
        }
        actors.removeAll(removedActors)
        removedActors.clear()

        if (turnCount % 3 == 0) {
            actors.each { actor ->
                Pasture surroundings = pasture.surroundingsOf(actor)
                if (!surroundings) {
                    removedActors.add(actor)
                    return
                }

                actor.getHungry(pasture)
            }
        }
        actors.removeAll(removedActors)
        removedActors.clear()
    }
}
