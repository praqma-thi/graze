package graze

import graze.engine.*

class Main {
    static void main(String[] args) {
        def setup = new Setup()
        def game = new Game(setup)
        game.run()
    }
}
