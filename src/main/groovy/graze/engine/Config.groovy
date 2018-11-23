package graze.engine

class Config extends HashMap<String, Object> {

    Config() {
        this."pasture.height" = 10
        this."pasture.width" = 10
    }

    Config(File configFile) {
        new groovy.json.JsonSlurper().parse(configFile).each { key, value ->
            this[key] = value
        }
    }
}
