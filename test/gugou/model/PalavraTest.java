/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gugou.model;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author VíctorCésar
 */
public class PalavraTest {

    Palavra palavra, palavra2;

    @Before
    public void setUp() {
        palavra = new Palavra("palavraTest");
        palavra2 = new Palavra("palavraTest");
    }

    @Test
    public void testCompareTo() {
        assertSame(0, palavra.compareTo((Object) palavra2));
    }

}
