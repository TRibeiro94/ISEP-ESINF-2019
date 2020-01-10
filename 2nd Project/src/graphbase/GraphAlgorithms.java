/*
 * A collection of graph algorithms.
 */
package graphbase;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the search (vInf)
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }
        LinkedList<V> vertex = new LinkedList<V>();
        LinkedList<V> qAux = new LinkedList<V>();
        boolean knownVerticex[] = new boolean[g.numVertices()];

        vertex.add(vert);
        qAux.add(vert);
        int vKey = g.getKey(vert);
        knownVerticex[vKey] = true;

        while (!qAux.isEmpty()) {
            vert = qAux.remove();
            for (Edge<V, E> edge : g.outgoingEdges(vert)) {
                V vAdj = g.opposite(vert, edge);
                vKey = g.getKey(vAdj);
                if (!knownVerticex[vKey]) {
                    vertex.add(vAdj);
                    qAux.add(vAdj);
                    knownVerticex[vKey] = true;
                }
            }
        }

        return vertex;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param g Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.getKey(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (!visited[g.getKey(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
    }

    /**
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the search (vInf)
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }
        LinkedList<V> resultList = new LinkedList<V>();
        boolean[] visited = new boolean[g.numVertices()];
        DepthFirstSearch(g, vert, visited, resultList);
        return resultList;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
            LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        visited[g.getKey(vOrig)] = true;
        path.push(vOrig);
        for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
            V vert = edge.getVDest() != vOrig ? edge.getVDest() : edge.getVOrig();
            if (vert.equals(vDest)) {
                path.push(vDest);
                paths.add(revPath(path));
                path.pop();
            } else {
                if (!visited[g.getKey(vert)]) {
                    allPaths(g, vert, vDest, visited, path, paths);
                }
            }
        }
        path.pop();
        visited[g.getKey(vOrig)] = false;
    }

    /**
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination (voInf)
     * @return paths ArrayList with all paths from voInf to vdInf (vdInf)
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        if (!g.validVertex(vDest) || !g.validVertex(vOrig)) {
            return new ArrayList<>();
        }

        ArrayList<LinkedList<V>> paths = new ArrayList<LinkedList<V>>();
        LinkedList<V> path = new LinkedList<V>();
        boolean visited[] = new boolean[g.numVertices()];
        allPaths(g, vOrig, vDest, visited, path, paths);
        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable vertices of a graph g with nonnegative edge weights This implementation uses Dijkstra's algorithm
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vertices
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices, boolean[] visited, int[] pathKeys, double[] dist) {

        int oKey = g.getKey(vOrig);
        dist[oKey] = 0;

        while (oKey != -1) {
            visited[g.getKey(vOrig)] = true;

            for (Edge<V, E> e : g.outgoingEdges(vOrig)) {
                V vert = e.getVDest() != vOrig ? e.getVDest() : e.getVOrig();
                if (!visited[g.getKey(vert)] && dist[g.getKey(vert)] > dist[g.getKey(vOrig)] + e.getWeight()) {
                    dist[g.getKey(vert)] = dist[g.getKey(vOrig)] + e.getWeight();
                    pathKeys[g.getKey(vert)] = g.getKey(vOrig);
                }
            }

            Double min = Double.MAX_VALUE;
            oKey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    oKey = i;
                }
            }
            for (V v : g.vertices()) {
                if (g.getKey(v) == oKey) {
                    vOrig = v;
                    break;
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf 
     * The path is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @param verts
     * @param pathKeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        int index = g.getKey(vDest);
        path.add(vDest);
        while (index != g.getKey(vOrig)) {
            if (pathKeys[index] == -1) {
                path.clear();
                break;
            }
            path.addFirst(verts[pathKeys[index]]);
            index = pathKeys[index];
        }
    }

    /**
     * shortest-path between vOrig and vDest
     *
     * @param <V>
     * @param <E>
     * @param g
     * @param vOrig
     * @param vDest
     * @param shortPath
     * @return
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        V[] vertices = (V[]) new Object[g.numVertices()];
        int[] pathKeys = new int[g.numVertices()];
        double[] dist = new double[g.numVertices()];
        boolean[] visited = new boolean[g.numVertices()];

        for (int i = 0; i < g.numVertices(); i++) {
            visited[i] = false;
            vertices[i] = null;
            dist[i] = Double.MAX_VALUE;
        }

        for (V v : g.vertices()) {
            vertices[g.getKey(v)] = v;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        shortPath.clear();

        if (!visited[g.getKey(vDest)]) {
            return 0;
        }

        getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
        return dist[g.getKey(vDest)];
    }

    /**
     * shortest-path between vOrig and all other
     *
     * @param <V>
     * @param <E>
     * @param g
     * @param vOrig
     * @param paths
     * @param dists
     * @return
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != Double.MAX_VALUE) {
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            }
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }
}
