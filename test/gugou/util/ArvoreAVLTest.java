/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gugou.util;

import gugou.model.Palavra;
import gugou.util.ArvoreAVL.Node;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Neto
 */
public class ArvoreAVLTest {

    ArvoreAVL arvore;
    Palavra ob1, ob2, ob3;
    LinkedList<Object> lista;

    @Before
    public void setUp() {
        arvore = new ArvoreAVL();
        ob1 = new Palavra("palavra1");
        ob2 = new Palavra("palavra2");
        ob3 = new Palavra("palavra3");
        lista = new LinkedList<>();

    }

    @Test
    public void testInserir() {
        arvore.inserir(ob1);
        arvore.inserir(ob2);
        arvore.inserir(ob3);

        assertSame(ob1, arvore.buscarPalavra(ob1));
        assertSame(ob2, arvore.buscarPalavra(ob2));
        assertSame(ob3, arvore.buscarPalavra(ob3));

    }

    @Test
    public void testTolist() {
        arvore.inserir(ob1);
        arvore.inserir(ob2);
        arvore.inserir(ob3);

        lista = arvore.toList();
        int i = lista.size();

        assertFalse(lista.isEmpty());
        assertSame(3, i);

    }

    @Test
    public void testRemove() {
        arvore.inserir(ob1);
        arvore.inserir(ob2);
        arvore.inserir(ob3);

        arvore.remover(ob1);
        assertSame(null, arvore.buscarPalavra(ob1));

        arvore.remover(ob2);
        assertSame(null, arvore.buscarPalavra(ob2));

        arvore.remover(ob3);
        assertSame(null, arvore.buscarPalavra(ob3));

        arvore.remover(ob1);
        assertSame(null, arvore.buscarPalavra(ob1));

    }

    @Test
    public void testIterator() {

        Iterator<Comparable> it = arvore.iterator();
        assertFalse(it.hasNext());

        arvore.inserir(ob1);
        arvore.inserir(ob2);
        arvore.inserir(ob3);

        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());

    }

    @Test
    public void testCotem() {
        arvore.inserir(ob1);
        arvore.inserir(ob2);
        arvore.inserir(ob3);

        assertTrue(arvore.contem(ob2));

        arvore.remover(ob2);
        assertFalse(arvore.contem(ob2));

    }

    @Test
    public void testEstaVazia() {
        assertTrue(arvore.estaVazia());

        arvore.inserir(ob1);
        arvore.inserir(ob2);
        arvore.inserir(ob3);

        assertFalse(arvore.estaVazia());

        arvore.remover(ob1);

        assertFalse(arvore.estaVazia());

    }

}
