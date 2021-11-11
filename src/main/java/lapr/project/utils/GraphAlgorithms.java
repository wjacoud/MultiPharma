/*
* A collection of graph algorithms.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Address;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vert
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> bfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();

        bfs.add(vert);
        qaux.add(vert);

        while (!qaux.isEmpty()) {
            vert = qaux.remove();
            for (V vAdj : g.adjVertices(vert)) {
                if (!bfs.contains(vAdj)) {
                    bfs.add(vAdj);
                    qaux.add(vAdj);
                }
            }
        }

        return bfs;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param g Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig,
            boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.getKey(vOrig)] = true;

        for (V adj : g.adjVertices(vOrig)) {
            if (!qdfs.contains(adj) && visited[g.getKey(adj)] == false) {
                DepthFirstSearch(g, adj, visited, qdfs);
            }
            visited[g.getKey(adj)] = false;
        }
    }

    /**
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vert
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qdfs = new LinkedList<V>();
        boolean[] visited = new boolean[g.numVertices()];

        DepthFirstSearch(g, vert, visited, qdfs);

        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
            LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        path.push(vOrig);
        visited[g.getKey(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.push(vDest);
                paths.add(path);
                path.pop();
            } else {
                if (!visited[g.getKey(vAdj)] == true) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }
            }

        }
        V vInt = path.pop();
        visited[g.getKey(vInt)] = false;

    }

    /**
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> paths = new ArrayList<>();

        if (!g.validVertex(vOrig) == true || !g.validVertex(vDest) == true) {
            return paths;
        }

        LinkedList<V> path = new LinkedList<>();

        boolean[] visited = new boolean[g.numVertices()];

        for (int i = 0; i < g.numVertices(); i++) {
            visited[i] = false;
        }

        allPaths(g, vOrig, vDest, visited, path, paths);

        return paths;
    }

    /**
     * Shortest Path's Length
     *
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights. This implementation
     * uses Dijkstra's algorithm.
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vertices
     * @param visited set of discovered vertices
     * @param pathKeys
     * @param dist minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig,
            V[] vertices, boolean[] visited, int[] pathKeys, double[] dist) {
// initializing variables
        for (V v : vertices) {
            dist[g.getKey(v)] = Double.MAX_VALUE;
            pathKeys[g.getKey(v)] = -1;
            visited[g.getKey(v)] = false;
        }

        // setting the distance origin - origin to zero
        dist[g.getKey(vOrig)] = 0;

        int index;
        while (vOrig != null) {
            index = g.getKey(vOrig);
            visited[index] = true;

            for (V adj : g.adjVertices(vOrig)) {
                Edge<V, E> edge = g.getEdge(vOrig, adj);

                if (visited[g.getKey(adj)] == false && dist[g.getKey(adj)]
                        > dist[g.getKey(vOrig)] + edge.getWeight()) {
                    dist[g.getKey(adj)] = dist[g.getKey(vOrig)] + edge.getWeight();
                    pathKeys[g.getKey(adj)] = index;
                }
            }

            vOrig = null;
            double minimunDistance = Double.MAX_VALUE;

            for (V v : vertices) {
                int vId = g.getKey(v);
                if (visited[vId] == false && dist[vId] < minimunDistance) {
                    vOrig = v;
                    minimunDistance = dist[vId];
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig
     * @param vDest
     * @param verts
     * @param pathKeys
     * @param path stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
            V[] verts, int[] pathKeys, LinkedList<V> path) {

        int vDestID = g.getKey(vDest);

        int prevID = pathKeys[vDestID];
        V prevV = null;

        for (V v : verts) {
            if (g.getKey(v) == prevID) {
                prevV = v;
            }
        }
        path.add(vDest);

        if (!vOrig.equals(vDest)) {
            getPath(g, vOrig, prevV, verts, pathKeys, path);
        }
    }

    //shortest-path between vOrig and vDest
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
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

        shortPath.clear();
        if (!visited[g.getKey(vDest)]) {
            return 0;
        }
        getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);

        LinkedList<V> pathInOrder = revPath(shortPath);
        shortPath.clear();
        while (!pathInOrder.isEmpty()) {
            shortPath.add(pathInOrder.removeFirst());
        }

        int vDestId = g.getKey(vDest);
        if (!visited[vDestId]) {
            return 0;
        }

        return dist[vDestId];
    }

    //shortest-path between voInf and all other
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
            paths.set(i, revPath(shortPath));
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * Reverses the path.
     *
     * @param path stack with path
     */
    public static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }

    public static <V, E> HashMap<V, Integer> ColouringAlgorithms(Graph<V, E> g) {

        HashMap<V, Integer> finalResut = new HashMap<>();

        V[] vertex = g.allkeyVerts();
        int[] degree = new int[g.numVertices()];
        int[] position = new int[g.numVertices()];

        // for vertex get the number of inc/outcoming edges and its position
        for (int i = 0; i < vertex.length; i++) {
            degree[i] = g.outDegree(vertex[i]) + g.inDegree(vertex[i]);
            position[i] = g.getKey(vertex[i]);
        }

        // sorting the vertices by edges degree (DESC)
        for (int i = 0; i < degree.length - 1; i++) {
            for (int j = i + 1; j < degree.length; j++) {
                if (degree[i] < degree[j]) {

                    // swap degree array
                    degree[i] += degree[j];
                    degree[j] = degree[i] - degree[j];
                    degree[i] = degree[i] - degree[j];

                    // swap position array
                    position[i] += position[j];
                    position[j] = position[i] - position[j];
                    position[i] = position[i] - position[j];

                    // swap vertex array
                    V aux = vertex[i];
                    vertex[i] = vertex[j];
                    vertex[j] = aux;
                }
            }
        }

        // support arrays for the colorization
        int[] rainbow = new int[g.numVertices()];
        boolean[] available = new boolean[g.numVertices()];

        // assigns the first colour to first country
        finalResut.put(vertex[0], 0);
        rainbow[0] = 0;
        available[0] = true;

        // initializing the support arrays
        for (int i = 1; i < g.numVertices(); i++) {
            rainbow[i] = -1;
            available[i] = true;
        }

        int colour = 0;
// Loop through each sorted vertex
        for (int i = 1; i < vertex.length; i++) {
            V actual = vertex[i];

            for (V adj : g.adjVertices(actual)) {
                int numb = getPosition(position, g.getKey(adj));
                if (rainbow[numb] != -1) {
                    available[rainbow[numb]] = false;
                }
            }

            // gets the first available colour
            colour = 0;
            while (!available[colour]) {
                colour++;
            }

            // assigns the available colour
            finalResut.put(actual, colour);
            rainbow[i] = colour;

            // Make all coloyur available again
            for (int ii = 0; ii < g.numVertices(); ii++) {
                available[ii] = true;
            }
        }

        return finalResut;
    }

    /**
     * Auxiliar to ColouringAlgorithms
     *
     * @param v
     * @param position
     * @return
     */
    public static int getPosition(int[] v, int position) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == position) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Constrained Shortest Path Problem (CSP)
     *
     * @param <V>
     * @param <E>
     * @param g
     * @param vOrig
     * @param stops
     * @param vDest
     * @return
     */
    public static <V, E> LinkedList<V> ConstrainedShortestPath(Graph<V, E> g, V vOrig, V vDest, List<V> stops) {
        LinkedList<V> finalPath = new LinkedList<>();

        // Check if Vertex orgim and Vertx destination exist
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
// origen and destiny doenst exist
            return null;
        }

        // Check if vertex that we need to stop exist
        for (V stop : stops) {
            if (!g.validVertex(stop)) {
// mid vertext doenst exist
                return null;
            }
        }

        ArrayList<LinkedList<V>> getAllAtualPath = new ArrayList<>();
        ArrayList<Double> doubleDistanceKms = new ArrayList<>();
// populate all paths from Origem to all vertex
// populate the weight of all paths from Origem to all vertex
        shortestPaths(g, vOrig, getAllAtualPath, doubleDistanceKms);

        V prox = null;
        while (!stops.isEmpty()) {
// Encontrar o proximo caminho mais curto
            int positionToNextVertex = -1;
            double shorterDistanceToNextVertex = Double.MAX_VALUE;

            for (V stop : stops) {
                int index = -1;

                for (LinkedList<V> atualPath : getAllAtualPath) {
                    index++;
// we are cheking the output of sortersPaths
// this output contains shorter path from vOrig to All vertex
// we will search for the shortest vertex that we need to stop
// atualPath can be empty because is possible to vOrig to not
// be able to reach a certain vertex
                    if (!atualPath.isEmpty()) {
                        if (atualPath.getLast().equals(stop)) {
                            if (doubleDistanceKms.get(index) < shorterDistanceToNextVertex) {
// in here we will find the closet stop vertex
// that is require to stop
                                prox = atualPath.getLast();
                                shorterDistanceToNextVertex = doubleDistanceKms.get(index);
                                positionToNextVertex = index;
                            }
                        }
                    }
                }
            }

            if (prox == null) {
                return null;
            }

            // atualPath
            LinkedList<V> atualPath = getAllAtualPath.get(positionToNextVertex);
// remove last path, to not repeat the stops
            atualPath.removeLast();
            finalPath.addAll(atualPath);

            // clear allPath List and distance List to be used again
            getAllAtualPath.clear();
            doubleDistanceKms.clear();

            // remove the stop that we already visited in this loop
            stops.remove(prox);

            // populate again the var
            shortestPaths(g, prox, getAllAtualPath, doubleDistanceKms);
        }

        LinkedList<V> aux = new LinkedList<>();
        double auxD = shortestPath(g, prox, vDest, aux);

        if (auxD == 0) {
            return null;
        }

        finalPath.addAll(aux);

        return finalPath;
    }

    private static <V, E> void findOptimiseWithCustomeStops(Graph<V, E> g, V vOrig, V vDest, ArrayList<V> stops, HashMap<Double, LinkedList<V>> finalPath, LinkedList<V> intermediatePath, double distance) {
        double nextStartingDistance;
        for (V stop : stops) {
            if (stops.size() > 1) {
                LinkedList<V> nextIntermediatePath = new LinkedList<>();
                nextStartingDistance = shortestPath(g, vOrig, stop, nextIntermediatePath);
                nextStartingDistance += distance;

                LinkedList<V> newIntermediatePath = new LinkedList<>(intermediatePath);
                newIntermediatePath.addAll(revPath(nextIntermediatePath));

                ArrayList<V> nextStops = new ArrayList<>(stops);
                nextStops.remove(stop);
                findOptimiseWithCustomeStops(g, stop, vDest, nextStops, finalPath, newIntermediatePath, nextStartingDistance);
            } else {
                LinkedList<V> finalP = new LinkedList<>(intermediatePath);
                LinkedList<V> nextIntermediatePath = new LinkedList<>();

                nextStartingDistance = shortestPath(g, vOrig, stop, nextIntermediatePath);
                distance += nextStartingDistance;
                finalP.addAll(revPath(nextIntermediatePath));

                nextStartingDistance = shortestPath(g, stop, vDest, nextIntermediatePath);
                nextStartingDistance += distance;
                finalP.addAll(revPath(nextIntermediatePath));

                finalPath.put(nextStartingDistance, finalP);
            }
        }
    }

    public static <V, E> double findOptimiseWithCustomeStops(Graph<V, E> graph, V vOrig, V vDest, ArrayList<V> stops, LinkedList<V> finalPath) {
        HashMap<Double, LinkedList<V>> hashMap = new HashMap<>();
        double nextStartingDistance;
        double startingDistance;
        
        stops.remove(vOrig);

        for (V stop : stops) {
            LinkedList<V> startingPath = new LinkedList<>();
            startingDistance = shortestPath(graph, vOrig, stop, startingPath);

            if (stops.size() == 1) {
                LinkedList<V> finalP = new LinkedList<>(revPath(startingPath));
                LinkedList<V> goingBackToDestination = new LinkedList<>();

                nextStartingDistance = shortestPath(graph, stop, vDest, goingBackToDestination);
                nextStartingDistance += startingDistance;
                finalP.addAll(revPath(goingBackToDestination));

                hashMap.put(nextStartingDistance, finalP);
            } else {
                ArrayList<V> nextStops = new ArrayList<>(stops);
                nextStops.remove(stop);
                findOptimiseWithCustomeStops(graph, stop, vDest, nextStops, hashMap, revPath(startingPath), startingDistance);
            }
        }

        double lowestKms = Double.MAX_VALUE;

        for (double key : hashMap.keySet()) {
            if (key < lowestKms) {
                lowestKms = key;
            }
        }

        if (lowestKms == Double.MAX_VALUE) {
            return -1.0;
        }

        finalPath.addAll(hashMap.get(lowestKms));

        return lowestKms;
    }
    
    
// -- DRUTE FORCE - FINAL
    @SuppressWarnings("unchecked")
    public static<V,E> double shortestPathPassingThrough(Graph<V,E> g, V vOrig, V vDest, LinkedList<V> addressList, LinkedList<V> path){

        if (!checkPathBFS(g, vOrig, vDest))
            return 0;

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];       // array with one path
        V[] vertices = g.allkeyVerts();

        ArrayList<V[]> combinations = new ArrayList<V[]>();

        Map<V, double[]> mapa = new HashMap<>();

        for (V v : addressList){
            visited = new boolean[nverts];
            pathKeys = new int[nverts];
            double[] dist = new double[nverts];
            shortestPathLength(g, v, vertices, visited, pathKeys, dist);
            mapa.put(v, dist);
        }
        
        permutaion((V[])addressList.toArray(), addressList.size(), combinations);

        visited = new boolean[nverts];
        pathKeys = new int[nverts];
        double[] dist_orig = new double[nverts];
        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist_orig);

        visited = new boolean[nverts];
        pathKeys = new int[nverts];
        double[] dist_dest = new double[nverts];
        shortestPathLength(g, vDest, vertices, visited, pathKeys, dist_dest);

        
        V[] tmp_vec = null;

        double min_dist;
        double tmp_dist_orig = 0;
        double tmp_dist_dest = 0;
        double min_dist_abs = Double.MAX_VALUE;
        int j;
        for (V[] vec : combinations){
            
            j = 0;
            min_dist = 0;

            for (int i=0; i<vec.length-1; i++){
                j = i + 1;

                V pais1 = vec[i];
                V pais2 = vec[j];

                min_dist = min_dist + mapa.get(pais1)[g.getKey(pais2)];

                if (min_dist > min_dist_abs)
                    break;

            }

            tmp_dist_orig = dist_orig[ g.getKey(vec[0]) ]; // distancia origem ao primeiro pais
            tmp_dist_dest = dist_dest[ g.getKey(vec[vec.length-1]) ]; // distancia destino ao ultimo pais
            min_dist = min_dist + tmp_dist_dest + tmp_dist_orig;

            if (min_dist_abs > min_dist){
                tmp_vec = (V[]) vec.clone();
                min_dist_abs = min_dist;
            }

        }
        /* Build Shortest Path */
        if (tmp_vec != null){
            path.add(vOrig);
            for (int i=0; i<tmp_vec.length; i++){
                path.add(tmp_vec[i]);
            }
            path.add(vDest);
        }

        return min_dist_abs;
    }
    
    
    /**
     * Verifica se existe caminho entre os dois vertices recebidos como parametro.
     * @param <V>
     * @param <E>
     * @param g
     * @param v_orig
     * @param v_dest
     * @return  true, caso exista caminho entre os dois.
     *          false, caso contrário.
     */
     public static<V,E> boolean checkPathBFS(Graph<V,E> g, V v_orig, V v_dest){
    
        if (!g.validVertex(v_orig) || !g.validVertex(v_dest))
            return false;
        
        boolean visited[] = new boolean[g.numVertices()];
        
        LinkedList<V> qaux = new LinkedList<>();
        
        qaux.add(v_orig);
        visited[g.getKey(v_orig)] = true;
        
        while (!qaux.isEmpty()){
            v_orig = qaux.removeLast();
            
            for (V vert : g.adjVertices(v_orig)){
                if (vert.equals(v_dest))
                    return true;
                
                if (visited[g.getKey(vert)] == false) {
                    
                    qaux.add(vert);
                    visited[g.getKey(vert)] = true;
                }
            }
        }
        return false;
    }

     
    private static<V> void permutaion(V[] a, int n, ArrayList<V[]> paths) {
        if (n == 1) {
            
//-- Print --     
//            for (int i=0; i<a.length; i++){
//                System.out.print(a[i] + ", ");
//            }System.out.println("");
//-- Print --
            paths.add(a.clone());
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            permutaion(a, n-1, paths);
            swap(a, i, n-1);
        }
    }  
    private static<V> void swap(V[] a, int i, int j) {
        V v = (V) a[i];
        a[i] = a[j];
        a[j] = v;
    }
}
