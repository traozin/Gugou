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
package gugou.controller;

import gugou.model.Pagina;
import gugou.model.Palavra;
import gugou.util.ArvoreAVL;
import gugou.util.PaginaComparador;
import gugou.util.PalavraComparador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import static gugou.util.QuickSort.quickSort;

/**
 *
 * @author Antonio Neto
 * @author Victor César
 */
public class ControllerPesquisa {

    private ArvoreAVL arvoreDePalavras;
    private LinkedList<Pagina> listaDePaginas;

    public ControllerPesquisa() {
        this.arvoreDePalavras = null;
        this.listaDePaginas = new LinkedList<>();
    }

    /**
     * Esse metodo verifica se no repositorio houve mudança, se algum arquivo
     * foi deletado ou adicionado.
     *
     * @return true/false se houve mudança no respositorio.
     *
     * @author Antonio Neto
     * @author Víctor Cèsar
     */
    public boolean verificaAqruivos() {

        LinkedList<Pagina> listaDePaginasAux = new LinkedList<>();

        File diretorio = new File("repositorio");
        File[] arquivos = diretorio.listFiles();

        for (int i = 0; i < arquivos.length; i++) {
            Pagina pagina = new Pagina(arquivos[i]);
            if (!listaDePaginasAux.contains(pagina)) {
                listaDePaginasAux.add(pagina);
            }

        }
        Iterator it = listaDePaginasAux.iterator();

        while (it.hasNext()) {
            if (!listaDePaginas.contains(it.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Serializa a lista geral de páginas
     *
     * @throws FileNotFoundException para tratar a auxência do arquivo lido
     * @throws IOException para tratar erros de entrada e saída de dados,
     * escrita de objetos
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void salvarListaPaginas() throws FileNotFoundException, IOException {
        FileOutputStream arquivoGrav = new FileOutputStream("data\\ListaPaginas.dat");
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

        objGravar.writeObject(listaDePaginas);

        objGravar.flush();

        objGravar.close();

        arquivoGrav.flush();

        arquivoGrav.close();
    }

    /**
     * Lê o arquivo serializado e retorna se o processo foi completo
     *
     * @return true/false se foi possível fazer a leitura
     * @throws IOException para tratar erros de entrada e saída de dados,
     * geração de objetos
     * @throws ClassNotFoundException para tratar a existência da classe do
     * objeto serializado
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public boolean lerListaPaginas() throws IOException, ClassNotFoundException {
        try {
            FileInputStream arquivoLeitura = new FileInputStream("data\\ListaPaginas.dat");
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

            listaDePaginas = (LinkedList<Pagina>) objLeitura.readObject();

            objLeitura.close();

            arquivoLeitura.close();

            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    /**
     * Serializa a árvore de palavras
     *
     * @throws FileNotFoundException para tratar a auxência do arquivo lido
     * @throws IOException para tratar erros de entrada e saída de dados,
     * escrita de objetos
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void salvarArvoreAVL() throws FileNotFoundException, IOException {
        FileOutputStream arquivoGrav = new FileOutputStream("data\\AVL.dat");
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

        objGravar.writeObject(arvoreDePalavras);

        objGravar.flush();

        objGravar.close();

        arquivoGrav.flush();

        arquivoGrav.close();
    }

    /**
     * Lê o objeto serializado e retorna se o processo foi completo
     *
     * @return true/false se foi possível fazer a leitura
     * @throws IOException para tratar erros de entrada e saída de dados,
     * geração de objetos
     * @throws ClassNotFoundException para tratar a existência da classe do
     * objeto serializado
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public boolean lerArvoreAVL() throws IOException, ClassNotFoundException {
        try {
            FileInputStream arquivoLeitura = new FileInputStream("data\\AVL.dat");
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

            arvoreDePalavras = (ArvoreAVL) objLeitura.readObject();

            objLeitura.close();

            arquivoLeitura.close();

            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    /**
     * Lê o diretório e constrói uma árvore de palavras, se houver alteração no
     * diretorio enquanto o programa estiver rodando não sera ciriada uma nova
     * arvore, apenas sera atualizada a arvore que esta na memoria.
     *
     * @param criar boolean responsavel por notificar que a uma necessidade de
     * criar um nova arvore.
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void cosntruirArvorePalavras(boolean criar) {
        if (criar == true) {
            arvoreDePalavras = new ArvoreAVL();
        }

        try {
            File diretorio = new File("repositorio");
            File[] arquivos = diretorio.listFiles();

//            for (File fileTmp : arquivos) {
//                System.out.println(fileTmp.getName());
//            }
            for (int i = 0; i < arquivos.length; i++) {
                Pagina pagina = new Pagina(arquivos[i]);
                if (!listaDePaginas.contains(pagina)) {
                    listaDePaginas.add(pagina);
                }

                FileReader arq = new FileReader(arquivos[i]);
                BufferedReader lerArq = new BufferedReader(arq);

                String linha = "";

                while (linha != null) {
                    linha = lerArq.readLine();

                    if (linha != null) {
                        linha = linha.replaceAll("[^A-Za-z0-9 ]", "");
                        String[] palavras = linha.split(" ");
                        // System.out.println(linha);

                        boolean controle = false;

                        for (String aux : palavras) {
                            Palavra palavra = new Palavra(aux);
                            pagina = new Pagina(arquivos[i]);

                            if (arvoreDePalavras.contem(palavra)) {
                                palavra = (Palavra) arvoreDePalavras.buscarPalavra(palavra);

                                Iterator it = palavra.getPaginas().iterator();
                                while (it.hasNext()) {
                                    Pagina percorreLista = (Pagina) it.next();
                                    if (percorreLista.equals(pagina)) {
                                        controle = true;
                                        percorreLista.setOcorrenciasPalavra(1);
                                    }
                                }
                                if (!controle) {
                                    pagina.setOcorrenciasPalavra(1);
                                    palavra.addNovaPagina(pagina);
                                }

                            } else {
                                pagina.setOcorrenciasPalavra(1);
                                palavra.addNovaPagina(pagina);
                                arvoreDePalavras.inserir(palavra);
                            }
                        }

                    } else {
                        //System.out.println("Proximo Arquivo!");
                    }
                }
                lerArq.close();
                arq.close();

            }

        } catch (IOException ex) {
            System.out.println("Erro!");

        }
    }

    /**
     * Escreve o arquivo por completo na interface
     *
     * @param arquivo arquivo que vai ser escrito
     * @throws FileNotFoundException para tratar caso o arquivo não exista
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void printarArquivo(String arquivo) throws FileNotFoundException {
        System.out.println(arquivo);
        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";

            while (linha != null) {
                linha = lerArq.readLine();

                if (linha != null) {
                    linha = linha.replaceAll("[^A-Za-z0-9 ]", "");
                    System.out.println(linha);
                }
            }
            arq.close();

        } catch (IOException ex) {
            System.out.println("Arquivo não encontrado!");
        }

    }

    /**
     * Pesquisa a palavra na árvore e atualiza os indices da palavra antes de
     * mostrar para o usuário
     *
     * @param palavra palavra pesquisada
     * @return a referência da palavra pesquisada, só que na árvore
     * @throws FileNotFoundException para tratar caso o arquivo que precise ser
     * atualizado não exista
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public Object pesquisa(Comparable palavra) throws FileNotFoundException {

        Palavra auxPalavra = (Palavra) arvoreDePalavras.buscarPalavra(palavra);

        if (auxPalavra != null) {
            auxPalavra.setQtdPesquisa(1);
            Iterator it = auxPalavra.getPaginas().iterator();

            while (it.hasNext()) {

                Pagina pagina = (Pagina) it.next();

                if (pagina.getDataDeModificação() != pagina.getArquivo().lastModified()) {
                    if (pagina.lerArquivo(auxPalavra.getPalavra())) {
                        if (pagina.getOcorrenciasPalavra() == 0) {
                            auxPalavra.getPaginas().remove(pagina);
                        }

                    } else {
                        auxPalavra.getPaginas().remove(pagina);
                    }

                }
            }
            return auxPalavra;
        } else {
            return null;

        }
    }

    /**
     * Incrementa o arquivo que foi acessado
     *
     * @param caminho arquivo acessado
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void contagemAcesso(String caminho) {
        File arquivo = new File(caminho);
        Pagina pagina = new Pagina(arquivo);

        if (listaDePaginas.contains(pagina)) {
            Iterator it = listaDePaginas.iterator();
            while (it.hasNext()) {
                Pagina aux = (Pagina) it.next();
                if (aux.equals(pagina)) {
                    aux.setAcessos(1);
                }
            }
        }
    }

    /**
     * Método para ordenar array de páginas que é passado como parâmetro
     *
     * @param paginas array de páginas
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public void ordenaPaginas(Pagina paginas[]) {
        quickSort(paginas, 0, paginas.length - 1);
    }

    public ArvoreAVL getArvoreDePalavras() {
        return arvoreDePalavras;
    }

    public LinkedList<Pagina> getListaDePaginas() {
        return listaDePaginas;
    }

    /**
     * Gera um array de páginas e ordena de acordo com o método compare do
     * comparador
     *
     * @return array de páginas ordenado por pesquisa da mesma
     *
     * @author Antonio Neto
     * @author Victor César
     *
     */
    public Pagina[] topKPaginas() {
        PaginaComparador comparador = new PaginaComparador();
        Pagina[] paginas = listaDePaginas.toArray(new Pagina[listaDePaginas.size()]);

        quickSort(paginas, 0, paginas.length - 1, comparador);
        return paginas;
    }

    /**
     * Gera um array de palavras e ordena de acordo com o método compare do
     * comparador
     *
     * @return array de palavras ordenado por pesquisa da mesma
     *
     * @author Antonio Neto
     * @author Victor César
     */
    public Palavra[] topKPalavra() {
        PalavraComparador comparador = new PalavraComparador();

        LinkedList palavra = arvoreDePalavras.toList();
        Palavra[] palavras = (Palavra[]) palavra.toArray(new Palavra[palavra.size()]);

        quickSort(palavras, 0, palavras.length - 1, comparador);
        return palavras;
    }

}
