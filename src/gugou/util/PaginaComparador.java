/** *****************************************************************************
 * Autores: Antonio Carlos Mendes Neto e Victor César da Rocha Bastos
 * Componente Curricular: Algoritmos II
 * Concluido em: 02/07/2018
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ***************************************************************************************** */
package gugou.util;

import gugou.model.Pagina;
import java.util.Comparator;

/**
 *
 * @author Antonio Neto
 * @author Victor César
 */
public class PaginaComparador implements Comparator {

    /**
     * Recebe duas páginas como parâmetro e as compara utilizando-se da
     * quantidade de acessos das mesmas
     *
     * @param t pagina 1
     * @param t1 pagina 2
     * @return a subtração dos acessos das duas páginas(funciona como compareTo)
     *
     * @author Antonio Neto
     * @author Victor César
     */
    @Override
    public int compare(Object t, Object t1) {
        Pagina p = (Pagina) t;
        Pagina p1 = (Pagina) t1;
        return p.getAcessos() - p1.getAcessos();
    }
}
