/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gugou.util;

import gugou.model.Palavra;
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
public class PalavraComparadorTest {
    
   
    
   Palavra palavra, palavra2;
   PalavraComparador comparador;

    @Before
    public void setUp() {
        palavra = new Palavra("palavraTest");
        palavra2 = new Palavra("palavraTest");
        comparador= new PalavraComparador();
    }
    
    @Test
    public void testCompare(){
        palavra.setQtdPesquisa(1);
        palavra2.setQtdPesquisa(2);
        
        assertSame(-1, comparador.compare(palavra, palavra2));
        
        assertSame(1, comparador.compare(palavra2, palavra));
        
        palavra.setQtdPesquisa(1);
        
        assertSame(0, comparador.compare(palavra, palavra2));
    }
    
}
