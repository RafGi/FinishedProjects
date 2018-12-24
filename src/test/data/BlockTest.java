package data;

import data.Block;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class BlockTest {

    //todo: deze waarden moeten uit de database blocks komen
    int Vierkant = 51;
    int Lijn = 15;
    int L = 785;
    int Omgekeerde_L = 802;
    int Bliksemfiguur = 561;
    int omgekeerde_bliksemfiguur = 306;
    int T = 39;


    @Test
    void doesBlockAtCoordsExist() {
        Block b = new Block(Omgekeerde_L);
        assertFalse(b.doesBlockAtCoordsExist(0,0));
        assertFalse(b.doesBlockAtCoordsExist(0,1));
        assertTrue(b.doesBlockAtCoordsExist(0, 2));
        assertFalse(b.doesBlockAtCoordsExist(0, 3));
        assertTrue(b.doesBlockAtCoordsExist(1, 0));
        assertTrue(b.doesBlockAtCoordsExist(1, 1));
        assertTrue(b.doesBlockAtCoordsExist(1, 2));
        assertFalse(b.doesBlockAtCoordsExist(1,3));
        for (int i = 2; i < 4; i++){
            for (int e = 0; e < 4; e++){
                assertFalse(b.doesBlockAtCoordsExist(i,e));
            }
        }


        Block lijn = new Block(Lijn);
        for (int e = 0; e < 4; e++){
            assertTrue(lijn.doesBlockAtCoordsExist(e,0));
        }
        for (int i = 1; i < 4; i++){
            for (int e = 0; e < 4; e++){
                assertFalse(lijn.doesBlockAtCoordsExist(e,i));
            }
        }
    }
}