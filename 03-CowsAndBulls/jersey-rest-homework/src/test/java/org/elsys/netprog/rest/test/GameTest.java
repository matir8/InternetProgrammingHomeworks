package org.elsys.netprog.rest.test;

import org.elsys.netprog.rest.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void guess() {
        Game game = new Game(1234);
        Assert.assertTrue(game.guess(1111).containsKey(1));
        Assert.assertTrue(game.guess(1111).containsValue(1));
    }
}
