package graze.actor

import graze.actor.imp.RCow
import org.junit.Test

class CowTest {
    @Test
    void equals_same_cow() {
        def cow = new RCow()
        assert cow == cow
    }

    @Test
    void equals_different_cow() {
        def cow1 = new RCow()
        def cow2 = new RCow()
        assert cow1 != cow2
    }

    @Test
    void equals_no_cow() {
        def cow = new RCow()
        assert cow != null
    }
}
