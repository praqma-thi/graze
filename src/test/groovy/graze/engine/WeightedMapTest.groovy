package graze.engine

import org.junit.Test

class WeightedMapTest {

    @Test
    void weightSum_sums_weights() {
        def map = [
            "foo": 80,
            "bar": 20,
        ] as WeightedMap
        assert map.weightSum() == 100
    }

    @Test
    void pick_returns_weighted() {
        def map = [
            "foo": 95,
            "bar": 5,
        ] as WeightedMap
        
        def picks = []
        50.times { picks += map.pick() }
        assert picks.count { it == "foo" } > picks.count { it == "bar" }
    }
}