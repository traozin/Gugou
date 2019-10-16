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
package gugou.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 *
 * @author Antonio Neto
 * @author Victor César
 */
public class Palavra implements Comparable, Serializable {

    private String palavra;
    private int qtdPesquisa;
    private LinkedList<Pagina> paginas;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.paginas = new LinkedList();
        this.qtdPesquisa = 0;
    }

    /**
     * Esse método retorna o nome da palavra em que contenha a string da palavra
     * e a quantidade de vezes que foi pesquisada
     *
     * @return uma string que contém a string da palavra e a quantidade de
     * pesquisa
     *
     * @author Antonio Neto
     * @author Victor César
     */
    @Override
    public String toString() {
        return "A palavra: " + palavra + " foi pesquisada " + qtdPesquisa + " vezes!";
    }

    public String getPalavra() {
        return palavra;
    }

    public int getQtdPesquisa() {
        return qtdPesquisa;
    }

    public void setQtdPesquisa(int num) {
        this.qtdPesquisa = qtdPesquisa + num;
    }

    public LinkedList getPaginas() {
        return paginas;
    }

    /**
     * Metodo usado para adicionar uma página na lista de páginas da palavra
     *
     * @param pagina é a página que vai ser adicionada
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void addNovaPagina(Pagina pagina) {
        paginas.add(pagina);
    }

    /**
     * Método usado para chamar o método contains da lista de palavras, que
     * retorna se tem uma página na lista
     *
     * @param pagina que vai ser comparada na lista
     * @return true/false se a página estiver/não estiver na lista
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public boolean contemPagina(Pagina pagina) {
        return paginas.contains(pagina);
    }

    /**
     * Remove uma página específica da lista
     *
     * @param pagina é a página que vai ser removida
     * @return a página removida
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public Object removerPagina(Pagina pagina) {
        return paginas.remove(pagina);
    }

    /**
     * Recebe uma palavra como parâmetro e usa do compareToIgnoreCase da mesma
     * para retornar um inteiro que será usado para ordenar o array
     *
     * @param t é a palavra que será usada para comparar
     * @return um inteiro que será usado para ordenar uma estrutura de palavras
     *
     * @author Antonio Neto
     * @author Victor César
     */
    @Override
    public int compareTo(Object t) {
        Palavra outra = (Palavra) t;
        return palavra.compareToIgnoreCase(outra.getPalavra());
    }

}
