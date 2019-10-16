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

import java.util.Comparator;

/**
 *
 * @author Antonio Neto
 * @author Victor César
 */
public class QuickSort {

    /**
     * Função recursiva que ordena totalmente um intervalo de um vetor usando o
     * compareTo
     *
     * @param vetor é o vetor passado como parâmetro
     * @param inicio é o inicio do intervalo onde será ordenado
     * @param fim é o final do intervalo onde será ordenado
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public static void quickSort(Comparable[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pe = inicio;
            int pivo = fim;
            int pd = fim - 1;
            while (pe <= pd) {
                while (pe <= pd && vetor[pe].compareTo(vetor[pivo]) > 0) {
                    pe++;
                }
                while (pe <= pd && vetor[pd].compareTo(vetor[pivo]) < 0) {
                    pd--;
                }
                if (pe <= pd) {
                    swap(vetor, pe, pd);
                    pe++;
                    pd--;
                }
            }
            swap(vetor, pe, pivo);
            quickSort(vetor, inicio, pe - 1);
            quickSort(vetor, pe + 1, fim);
        }
    }

    /**
     * Função recursiva que ordena totalmente um intervalo de um vetor usando
     * uma variável do tipo comparator compatível com o vetor
     *
     * @param vetor é o vetor que será ordenado
     * @param inicio inicio do intervalo
     * @param fim final do intervalo
     * @param comparador variável compatível com o vetor
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public static void quickSort(Comparable[] vetor, int inicio, int fim, Comparator comparador) {
        if (inicio < fim) {
            int pe = inicio;
            int pivo = fim;
            int pd = fim - 1;
            while (pe <= pd) {
                while (pe <= pd && comparador.compare(vetor[pe], vetor[pivo]) > 0) {
                    pe++;
                }
                while (pe <= pd && comparador.compare(vetor[pd], vetor[pivo]) < 0) {
                    pd--;
                }
                if (pe <= pd) {
                    swap(vetor, pe, pd);
                    pe++;
                    pd--;
                }
            }
            swap(vetor, pe, pivo);
            quickSort(vetor, inicio, pe - 1, comparador);
            quickSort(vetor, pe + 1, fim, comparador);
        }
    }

    /**
     * Troca duas posições do vetor que são passadas como parâmetro
     *
     * @param vetor vetor onde irá acontecer a troca
     * @param p1 primeira posição
     * @param p2 segunda posição
     *
     * @author Antonio Neto
     * @author Victor César
     */
    private static void swap(Object[] vetor, int p1, int p2) {
        Object carta = vetor[p1];
        vetor[p1] = vetor[p2];
        vetor[p2] = carta;
    }
}
