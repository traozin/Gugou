/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gugou.model;

import java.io.File;
import java.io.FileNotFoundException;
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
public class PaginaTest {

    Pagina pagina, pagina2;
    File file, file2;

    @Before
    public void setUp() {
        file = new File("Twittre 2323.txt");
        file2 = new File("Twitter 2589.txt");
        pagina = new Pagina(file);
        pagina2 = new Pagina(file2);
    }

    @Test
    public void testFoiModificado() {
        assertFalse(pagina.foiModificado());

        pagina.setDataDeModificação(Long.MAX_VALUE);
        assertTrue(pagina.foiModificado());

    }

    @Test
    public void testCompareTo() {
        pagina.setOcorrenciasPalavra(1);
        pagina2.setOcorrenciasPalavra(2);

        assertSame(-1, pagina.compareTo(pagina2));

        assertSame(1, pagina2.compareTo(pagina));

        pagina.setOcorrenciasPalavra(1);

        assertSame(0, pagina.compareTo(pagina2));

    }

}
