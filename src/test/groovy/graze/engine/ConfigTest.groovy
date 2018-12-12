package graze.engine

import org.junit.Test

class ConfigTest {
    @Test
    void parses_flat_settings() {
        def configFile = File.createTempFile('parses_flat_settings', 'json')
        configFile.text = """\
        {
            "pasture.width": 500,
            "pasture.height": 200,
            "canvas.enabled": false
        }
        """.stripIndent()
        configFile.deleteOnExit()

        def config = new Config(configFile)
        assert config["pasture.width"] == 500
        assert config["pasture.height"] == 200
        assert config["canvas.enabled"] == false
    }

    @Test
    void parses_nested_settings() {
        def configFile = File.createTempFile('parses_nested_settings', 'json')
        configFile.text = """\
        {
            "actors": {
                "graze.actor.imp.RCow": 20
            }
        }
        """.stripIndent()
        configFile.deleteOnExit()

        def config = new Config(configFile)
        assert config["actors"]["graze.actor.imp.RCow"] == 20
    }
}
