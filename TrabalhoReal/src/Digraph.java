
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Marcelo Cohen
 * @author Thomaz Abrantes
 */

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V; // número de vértices do grafo
    private int E; // número de arestas do grafo
    private List<Integer>[] adj; // adj[v] = adjacency list for vertex v
    private int[] indegree; // indegree[v] = indegree of vertex v

    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Número de vértices em um grafo não pode ser negativo");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0)
                throw new IllegalArgumentException("Número de vértices em um grafo não pode ser negativo");
            indegree = new int[V];
            adj = (List<Integer>[]) new List[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new LinkedList<>();
            }
            int E = in.readInt();
            if (E < 0)
                throw new IllegalArgumentException("Número de arestas em um grafo não pode ser negativo");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Formato de entrada inválido no construtor Digraph", e);
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("O vértice" + v + " não está entre 0 e " + (V - 1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    public String toString() {
        System.out.println("Estrutura do Grafo Dirigido: \n");
        StringBuilder s = new StringBuilder();
        s.append(V + " vértices, " + E + " arestas " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public String toDot() {
        System.out.println("Construção do Grafo Dirigido: \n");
        StringBuilder s = new StringBuilder();
        s.append("digraph {" + NEWLINE);
        s.append("rankdir = LR;" + NEWLINE);
        s.append("node [shape = circle];" + NEWLINE);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                s.append(v + " -> " + w + ";" + NEWLINE);
            }
        }
        s.append("}");
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
        StdOut.println();
        StdOut.println(G.toDot());
    }
}
