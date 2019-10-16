/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gugou.tests;

import gugou.model.PaginaTest;
import gugou.model.PalavraTest;
import gugou.util.ArvoreAVLTest;
import gugou.util.PaginaComparadorTest;
import gugou.util.PalavraComparadorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	PaginaTest.class,
	PalavraTest.class,
	ArvoreAVLTest.class,
	PaginaComparadorTest.class,
        PalavraComparadorTest.class,
	
})
public class AllTests { }

