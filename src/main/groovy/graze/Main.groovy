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

        Config config = configFile ? new Config(configFile) : new Config()
        Game game = new Game(config)
        game.run()
    }
}
