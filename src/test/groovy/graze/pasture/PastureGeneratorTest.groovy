package graze.pasture

import org.junit.Test

class PastureGeneratorTest {
    @Test
    void generate_dimensions_match() {
        def pasture = new PastureGenerator().width(15).height(30).generate()
        assert pasture.height == 30
        assert pasture.width == 15  
    }
}
