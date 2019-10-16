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
package gugou.view;

import gugou.controller.ControllerPesquisa;
import gugou.model.Pagina;
import gugou.model.Palavra;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Neto
 */
public class Gugou {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {

        Scanner input = new Scanner(System.in);
        boolean controle = true;
        ControllerPesquisa pesquisa = new ControllerPesquisa();

        if (!pesquisa.lerArvoreAVL() || !pesquisa.lerListaPaginas() || !pesquisa.verificaAqruivos()) {
            pesquisa.cosntruirArvorePalavras(true);
        }

        while (controle) {
            if (!pesquisa.verificaAqruivos()) {
                pesquisa.cosntruirArvorePalavras(false);
            }
            pesquisa.salvarArvoreAVL();
            pesquisa.salvarListaPaginas();

            System.out.println("Gugou!\n");

            System.out.println("1 - Pesquisar");
            System.out.println("2 - Top 10");
            System.out.println("3 - Sair");

            System.out.print("\nOpção: ");

            switch (input.nextInt()) {
                case 1:
                    System.out.print("\nDigite a palavra: ");
                    Palavra palavra = new Palavra(input.next());

                    Palavra resultado = (Palavra) pesquisa.pesquisa(palavra);

                    if (resultado != null) {
                        Pagina[] paginas = (Pagina[]) resultado.getPaginas().toArray(new Pagina[resultado.getPaginas().size()]);

                        pesquisa.ordenaPaginas(paginas);

                        System.out.print("\nComo deseja visualizar a lista de páginas?\n\n1 - Maior Relevância\n2 - Menor Relevância\n\nOpção: ");
                        switch (input.nextInt()) {
                            case 1:
                                for (int i = 0; i < paginas.length; i++) {
                                    System.out.println(paginas[i].toString());
                                }
                                break;
                            case 2:
                                for (int i = paginas.length - 1; i >= 0; i--) {
                                    System.out.println(paginas[i].toString());
                                }
                                break;
                            default:
                                System.out.println("\nOpção inválida!\n");
                                break;
                        }
                        System.out.print("\n1 - Nova pesquisa\n2 - Entrar na página\n\nOpção: ");
                        switch (input.nextInt()) {
                            case 1:
                                break;
                            case 2:
                                Scanner input2 = new Scanner(System.in);
                                System.out.print("\nDigite o nome da página: ");
                                String entrada = "repositorio\\" + input2.nextLine() + ".txt";
                                pesquisa.contagemAcesso(entrada);
                                pesquisa.printarArquivo(entrada);
                                break;
                            default:
                                System.out.println("\nOpção inválida!\n");
                                break;
                        }
                    } else {
                        System.out.println("Palavra não encontrada!\n");
                    }
                    break;
                case 2:
                    System.out.print("Deseja ver top de páginas ou de palavras?\n\n1 - Páginas\n2 - Palavras\n\nOpção: ");
                    switch (input.nextInt()) {
                        case 1:
                            Pagina[] topPaginas = pesquisa.topKPaginas();
                            int tamanho = topPaginas.length;

                            System.out.print("Como deseja visualizar o Top 10 páginas?\n\n1 - Maior quantidade de acessos\n2 - Menor quantidade de acessos\n\nOpção: ");
                            switch (input.nextInt()) {
                                case 1:
                                    if (tamanho <= 10) {
                                        for (int i = 0; i < topPaginas.length; i++) {
                                            System.out.println(topPaginas[i].toString2());
                                        }
                                    } else {
                                        for (int i = 0; i < 10; i++) {
                                            System.out.println(topPaginas[i].toString2());
                                        }
                                    }
                                    break;
                                case 2:
                                    if (tamanho <= 10) {
                                        for (int i = topPaginas.length - 1; i >= 0; i--) {
                                            System.out.println(topPaginas[i].toString2());
                                        }
                                    } else {
                                        for (int i = topPaginas.length - 1; i > topPaginas.length - 10; i--) {
                                            System.out.println(topPaginas[i].toString2());
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("\nOpção inválida!\n");
                                    break;
                            }
                            break;
                        case 2:
                            Palavra[] topPalavras = pesquisa.topKPalavra();
                            int num = topPalavras.length;

                            System.out.print("Como deseja visualizar o Top 10 palavras?\n\n1 - Maior quantidade de acessos\n2 - Menor quantidade de acessos\n\nOpção: ");
                            switch (input.nextInt()) {
                                case 1:
                                    if (num <= 10) {
                                        for (int i = 0; i < topPalavras.length; i++) {
                                            System.out.println(topPalavras[i].toString());
                                        }
                                    } else {
                                        for (int i = 0; i < 10; i++) {
                                            System.out.println(topPalavras[i].toString());
                                        }
                                    }
                                    break;
                                case 2:
                                    if (num <= 10) {
                                        for (int i = topPalavras.length - 1; i >= 0; i--) {
                                            System.out.println(topPalavras[i].toString());
                                        }
                                    } else {
                                        for (int i = topPalavras.length - 1; i > topPalavras.length - 10; i--) {
                                            System.out.println(topPalavras[i].toString());
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("\nOpção inválida!\n");
                                    break;

                            }
                            break;
                        default:
                            System.out.println("\nOpção inválida!\n");
                            break;
                    }
                    break;
                case 3:
                    controle = false;
                    break;
                default:
                    System.out.println("\nOpção inválida!\n");
                    break;
            }
        }
    }
}
