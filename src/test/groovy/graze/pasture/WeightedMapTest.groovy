package graze.pasture

import org.junit.Test
import graze.actor.Action

class WeightedMapTest {

    @Test
    void weightSum_sums_weights() {
        def map = WeightedMap.from([
            "foo": 80,
            "bar": 20,
        ])
        assert map.weightSum() == 100
    }

    @Test
    void pick_returns_weighted() {
        def map = WeightedMap.from([
            "foo": 95,
            "bar": 5,
        ])
        
        def picks = []
        50.times { picks += map.pick() }
        assert picks.count { it == "foo" } > picks.count { it == "bar" }
    }

    @Test
    void pick_returns_enums() {
        def map = WeightedMap.from([
            (Action.PASS): 1
        ])
        assert map.pick().class == Action.class
    }
}
