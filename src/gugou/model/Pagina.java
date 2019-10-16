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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Neto
 */
public class Pagina implements Serializable, Comparable {

    private File arquivo;
    private Long dataDeModificação;
    private int ocorrenciasPalavra;
    private int acessos;

    public Pagina(File arquivo) {
        this.arquivo = arquivo;
        this.ocorrenciasPalavra = 0;
        this.dataDeModificação = arquivo.lastModified();
        this.acessos = 0;
    }

    /**
     * Retorna o nome da página mostrando as ocorrências de uma palavra na
     * página
     *
     * @return uma string que contem o nome dá página tratado, sem ser o
     * diretório e com as ocorrências da palavra
     *
     * @author Antonio Neto
     * @author Victor César
     */
    @Override
    public String toString() {
        String nome = arquivo.getPath();
        nome = nome.replaceAll("[^A-Za-z0-9 ]", "");
        nome = nome.replaceAll("repositorio", "");
        nome = nome.replaceAll("txt", "");

        return "Na página " + nome + " teve " + ocorrenciasPalavra + " ocorrencias dessa palavra!";
    }

    /**
     * Retorna o nome da página mostrando os acessos que a mesma teve em tempo
     * de execução
     *
     * @return uma string com o nome da página e o num de acessos da mesma
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public String toString2() {
        String nome = arquivo.getPath();
        nome = nome.replaceAll("[^A-Za-z0-9 ]", "");
        nome = nome.replaceAll("repositorio", "");
        nome = nome.replaceAll("txt", "");

        return "A página " + nome + "teve" + acessos + "acessos!";
    }

    public int getAcessos() {
        return acessos;
    }

    public void setAcessos(int num) {
        this.acessos = acessos + num;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public Long getDataDeModificação() {
        return dataDeModificação;
    }

    public void setDataDeModificação(Long dataDeModificação) {
        this.dataDeModificação = dataDeModificação;
    }

    public int getOcorrenciasPalavra() {
        return ocorrenciasPalavra;
    }

    public void setOcorrenciasPalavra(int num) {
        this.ocorrenciasPalavra = this.ocorrenciasPalavra + num;
    }

    /**
     * Método que testa se o arquivo foi modificado comparando o atributo
     * dataDeModificação e o lastModified() do arquivo
     *
     * @return true/false se o arquivo foi/não foi modificado
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public boolean foiModificado() {
        if (dataDeModificação != arquivo.lastModified()) {
            dataDeModificação = arquivo.lastModified();
            return true;
        }
        return false;
    }

    /**
     * Lê um arquivo em busca de uma palavra passada como parâmetro, atualizando
     * suas ocorrências no arquivo e a data de modificação
     *
     * @param palavra palavra passada como parâmetro para o teste no arquivo
     * @return true/false caso consiga, ou não, ler o arquivo
     * @throws FileNotFoundException excessão para tratar se o arquivo existe ou
     * não
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public boolean lerArquivo(String palavra) throws FileNotFoundException {
        int cont = 0;

        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = "";
            while (linha != null) {
                linha = lerArq.readLine();
                if (linha != null) {
                    linha = linha.replaceAll("[^A-Za-z0-9 ]", "");
                    String[] aux = linha.split(" ");

                    for (int i = 0; i < aux.length; i++) {
                        if (aux[i].equals(palavra)) {
                            cont++;
                        }
                    }
                }

            }
            arq.close();
            lerArq.close();
            dataDeModificação = arquivo.lastModified();
            ocorrenciasPalavra = cont;
            return true;

        } catch (FileNotFoundException ex) {
            return false;

        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Recebe obrigatoriamente uma pagina como parâmetro e compara a mesma com a
     * página usada para chamar esse método, comparando o caminhos dos arquivos,
     * testando se são iguais ou não
     *
     * @param obj página a ser comparada
     * @return true/false se a página for igual/diferente
     *
     * @author Antonio Neto
     * @author Victor César
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pagina) {
            Pagina aux = (Pagina) obj;
            if (aux.getArquivo().getPath().equals(arquivo.getPath())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a subtração das ocorrências da palavra no arquivo e das
     * ocorrências da palavra no arquivo passado como parametro, usado como
     * parâmetro para ordenar um array de paginas
     *
     * @param o página a ser comparada
     * @return um inteiro que será usado para ordenar uma estrutura de páginas
     *
     * @author Antonio Neto
     * @author Victor César
     */
    @Override
    public int compareTo(Object o) {
        Pagina outraPagina = (Pagina) o;
        return ocorrenciasPalavra - outraPagina.getOcorrenciasPalavra();
    }

}
