package graze.engine

class Config extends HashMap<String, Object> {
    Config() {
        this."canvas.enabled" = true
        this."loop.king" = true
        this."loop.sleep" = 200
        this."pasture.height" = 30
        this."pasture.width" = 30
        this."actors" = [
            "graze.actor.imp.RCow": 5,
            "graze.actor.imp.SmartCow": 5,
        ]
    }

    Config(File configFile) {
        this()
        new groovy.json.JsonSlurper().parse(configFile).each { key, value ->
            this[key] = value
        }
    }
}
