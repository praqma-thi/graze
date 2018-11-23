package graze.engine

import org.junit.Test

class ConfigTest {
    @Test
    void parses_pasture_size() {
        def configFile = File.createTempFile('parses_pasture_size', 'json')
        configFile.text = """\
        {
            "pasture.width": 500,
            "pasture.height": 200,
        }
        """.stripIndent()
        configFile.deleteOnExit()

        def config = new Config(configFile)
        assert config["pasture.width"] == 500
        assert config["pasture.height"] == 200
    }
}
