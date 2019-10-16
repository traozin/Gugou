/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gugou.util;

import gugou.model.Pagina;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author VíctorCésar
 */
public class PaginaComparadorTest {

    Pagina pagina, pagina2;
    File file, file2;
    PaginaComparador comparador;
    
    @Before
    public void setUp() {
        file = new File("Twittre 2323.txt");
        file2 = new File("Twitter 2589.txt");
        pagina = new Pagina(file);
        pagina2 = new Pagina(file2);
        comparador = new PaginaComparador();

    }

    @Test
    public void testCompare() {
        pagina.setAcessos(1);
        pagina2.setAcessos(2);

        

        assertSame(-1, comparador.compare(pagina, pagina2));

        assertSame(1, comparador.compare(pagina2, pagina));

        pagina.setAcessos(1);

        assertSame(0, comparador.compare(pagina, pagina2));

    }
}
