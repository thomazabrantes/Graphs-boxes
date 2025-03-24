import java.util.ArrayList;

import java.util.Collections;
/**
 * @author Eduarda Monteiro
 * @author Thomaz Abrantes
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private ArrayList<Integer>[] longestPaths;

    @SuppressWarnings("unchecked")
    public DepthFirstSearch(Digraph g) {
        marked = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        distTo = new int[g.getV()];
        longestPaths = new ArrayList[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            distTo[v] = -1;
            longestPaths[v] = new ArrayList<>();
        }
    }

    private int dfs(Digraph g, int v) {
        if (distTo[v] != -1) return distTo[v]; 

        marked[v] = true;
        int maxLength = 0;
        for (int w : g.adj(v)) {
            int length = dfs(g, w) + 1;
            if (length > maxLength) {
                maxLength = length;
                edgeTo[v] = w;
            }
        }
        distTo[v] = maxLength; 
        return maxLength;
    }

    public ArrayList<Integer> encontrarMaiorCaminho(Digraph g) {
        int maxLength = 0;
        int endVertex = 0;

        for (int v = 0; v < g.getV(); v++) {
            int length = dfs(g, v);
            if (length > maxLength) {
                maxLength = length;
                endVertex = v;
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        for (int x = endVertex; distTo[x] != 0; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(0);
        Collections.reverse(path);

        return path;
    }
}
