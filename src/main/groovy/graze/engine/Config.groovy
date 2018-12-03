package graze.engine

class Config extends HashMap<String, Object> {
    Config() {
        this."canvas.enabled" = true
        this."loop.sleep" = 0
        this."pasture.height" = 10
        this."pasture.width" = 10
    }

    Config(File configFile) {
        this()
        new groovy.json.JsonSlurper().parse(configFile).each { key, value ->
            this[key] = value
        }
    }
}
