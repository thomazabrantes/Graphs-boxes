import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Thomaz Abrantes
 */
public class App {
    public static void main(String[] args) throws Exception {
        String nomeArquivo = "";
        long tempoInicial = System.nanoTime();

        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);

        System.out.println("Olá usuário, seja bem-vindo!");
        linhaDivisoria();
        System.out.println("Por favor, escolha uma das opções para a análise: \n");
        System.out.println("(1)  : análise com 10    caixas");
        System.out.println("(2)  : análise com 20    caixas");
        System.out.println("(3)  : análise com 50    caixas");
        System.out.println("(4)  : análise com 100   caixas");
        System.out.println("(5)  : análise com 200   caixas");
        System.out.println("(6)  : análise com 300   caixas");
        System.out.println("(7)  : análise com 500   caixas");
        System.out.println("(8)  : análise com 1000  caixas");
        System.out.println("(9)  : análise com 2000  caixas");
        System.out.println("(10) : análise com 5000  caixas");
        System.out.println("(11) : análise com 10000 caixas");
        linhaDivisoria();
        System.out.print("Sua escolha: ");
        int escolha = in.nextInt();
        switch (escolha) {
            case 1:
                nomeArquivo = "teste10.txt";
                break;
            case 2:
                nomeArquivo = "teste20.txt";
                break;
            case 3:
                nomeArquivo = "teste50.txt";
                break;
            case 4:
                nomeArquivo = "teste100.txt";
                break;
            case 5:
                nomeArquivo = "teste200.txt";
                break;
            case 6:
                nomeArquivo = "teste300.txt";
                break;
            case 7:
                nomeArquivo = "teste500.txt";
                break;
            case 8:
                nomeArquivo = "teste1000.txt";
                break;
            case 9:
                nomeArquivo = "teste2000.txt";
                break;
            case 10:
                nomeArquivo = "teste5000.txt";
                break;
            case 11:
                nomeArquivo = "teste10000.txt";
                break;
            default:
                linhaDivisoria();
                System.out.println("Como não identificamos sua escolha, vamos testar com 10 caixas: ");
                nomeArquivo = "teste10.txt";
                break;
        }

        ArrayList<Caixa> caixas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split("\\s+");
                int[] dimensoes = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    dimensoes[i] = Integer.parseInt(parts[i]);
                }
                caixas.add(new Caixa(dimensoes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(caixas);

        Digraph digraph = new Digraph(caixas.size());
        
        for (int i = 0; i < caixas.size(); i++) {
            for (int j = 0; j < caixas.size(); j++) {
                if (i != j && caixas.get(i).cabeDentro(caixas.get(j))) {
                    digraph.addEdge(i, j);
                }
            }
        }

        DepthFirstSearch dfs = new DepthFirstSearch(digraph);
        ArrayList<Integer> maiorCaminho = dfs.encontrarMaiorCaminho(digraph);

        long tempoFinal = System.nanoTime();
        long duracao = (tempoFinal - tempoInicial) / 1_000_000;

        /**
         * Comandos para construção e visualização da estrutura detalhada do grafo
         * dirigido
         */
        // linhaDivisoria();
        //System.out.println(digraph.toDot());
        // System.out.println();

        // linhaDivisoria();
        // System.out.println(digraph.toString());

        linhaDivisoria();
        System.out.println("Estrutura simples do grafo dirigido: \n");
        int numeroVertices = digraph.getV();
        System.out.printf("Número de vértices é : %d \n", numeroVertices);

        int numeroArestas = digraph.getE();
        System.out.printf("Número de arestas é  : %d \n", numeroArestas);


        linhaDivisoria();
        System.out.println("A maior sequência de caixas que cabem uma na outra: " + maiorCaminho.size() + " \n");
        System.out.println("Dimensões caixas: \n");
        for (int v : maiorCaminho) {
            System.out.println(caixas.get(v));
        }

        System.out.println();
        linhaDivisoria();
        System.out.println("Tempo de execução: " + duracao + " milissegundos");
        System.out.println();
        linhaDivisoria();
    }

    public static void linhaDivisoria() {
        System.out.println("-----------------------------------------------------");
    }
}
