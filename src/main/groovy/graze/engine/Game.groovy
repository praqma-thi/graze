package graze.engine

import graze.actor.*
import graze.actor.imp.*
import graze.behaviour.imp.*
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
                    println pasture.allActorClasses()[0] ?: 'stalemate'
                } else {
                    println dayCount
                }
            }
        }
    }

    boolean endGame() {
        def survivingActors = actors.collect { it.class }.unique()
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

            if (actor instanceof Grass) {
                new GrassBehaviour().move(actor, pasture, actor.move(surroundings))
            } else if (actor instanceof Wolf) {
                new WolfBehaviour().move(actor, pasture, actor.move(surroundings))
            } else {
                new CowBehaviour().move(actor, pasture, actor.move(surroundings))
            }
        }
        actors.removeAll(removedActors)
        removedActors.clear()

        actors.each { actor ->
            Pasture surroundings = pasture.surroundingsOf(actor)
            if (!surroundings) {
                removedActors.add(actor)
                return
            }

            if (actor instanceof Grass) {
                new GrassBehaviour().act(actor, pasture, actor.act(surroundings)) 
            } else if (actor instanceof Wolf) {
                new WolfBehaviour().act(actor, pasture, actor.act(surroundings)) 
            } else {
                new CowBehaviour().act(actor, pasture, actor.act(surroundings)) 
            }
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

                if (actor instanceof Grass) {
                    new GrassBehaviour().getHungry(actor, pasture)
                } else if (actor instanceof Wolf) {
                    new WolfBehaviour().getHungry(actor, pasture)
                } else {
                    new CowBehaviour().getHungry(actor, pasture)
                }
            }
        }
        actors.removeAll(removedActors)
        removedActors.clear()
    }
}
