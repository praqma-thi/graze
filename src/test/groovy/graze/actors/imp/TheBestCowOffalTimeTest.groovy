package graze.actor.imp

import org.junit.Test
import graze.actor.*
import graze.actor.imp.TtogttoghanCow
import graze.utils.TestUtils

class TheBestCowOffalTimeTest {

    @Test
    void always_devours() {
        def cow = new TheBestCowOffalTime()

        50.times {
            assert cow.act(null) == Action.EAT
        }
    }
}
