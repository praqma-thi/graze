package graze

import graze.engine.*

class Main {
    static void main(String[] args) {
        File configFile = null

        if (args.size() > 0) {
            configFile = new File(args[0])
            if (!configFile.exists()) {
                println "Invalid parameter ${configFile} does not exist."
                System.exit(1)
            }
        }

        System.sleep(1000)
        def config = configFile ? new Config() : new Config(configFile)
        def setup = new Setup()
        def game = new Game(setup)
        game.run()
    }
}
