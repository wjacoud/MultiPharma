package lapr.project.utils;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public class Graph<V, E> implements GraphInterface<V, E> {

    private int numVert;
    private int numEdge;
    private boolean isDirected;
    private Map<V, Vertex<V, E>> vertices;  //all Vertices of the graph 

    // Constructs an empty graph (either undirected or directed)
    public Graph(boolean directed) {
        this.numVert = 0;
        this.numEdge = 0;
        this.isDirected = directed;
        this.vertices = new LinkedHashMap<>();
    }

    public int numVertices() {
        return this.numVert;
    }

    public Iterable<V> vertices() {
        return this.vertices.keySet();
    }

    public boolean validVertex(V vert) {
        if (vertices.get(vert) == null) {
            return false;
        }

        return true;
    }

    public int getKey(V vert) {
        return this.vertices.get(vert).getKey();
    }

    public V[] allkeyVerts() {
        //V[] keyverts = (V[]) new Object[numVert];
        V vertElem = null;
        for (Vertex<V, E> vert : vertices.values()) {
            vertElem = vert.getElement();            // To get type
        }

        @SuppressWarnings("unchecked")
        V[] keyverts = (V[]) Array.newInstance(vertElem.getClass(), numVert);

        for (Vertex<V, E> vert : vertices.values()) {
            keyverts[vert.getKey()] = vert.getElement();
        }

        return keyverts;
    }

    public Iterable<V> adjVertices(V vert) {
        if (!validVertex(vert)) {
            return null;
        }

        Vertex<V, E> vertex = this.vertices.get(vert);

        return vertex.getAllAdjVerts();
    }

    public int numEdges() {
        return this.numEdge;
    }

    public Iterable<Edge<V, E>> edges() {
        
        List<Edge<V,E>> edges_list = new ArrayList<>();
        
        for (V vert : this.vertices.keySet()){
            Iterator<Edge<V,E>> it = this.vertices.get(vert).getAllOutEdges().iterator();
            
            while (it.hasNext()){
                
                Edge<V,E> next_edge = it.next();
                
                if (!edges_list.contains(next_edge))
                    edges_list.add(next_edge);
            }
        }
        return edges_list;
    }

    public Edge<V, E> getEdge(V vOrig, V vDest) {
        if (!validVertex(vOrig) || !validVertex(vDest)) {
            return null;
        }

        Vertex<V, E> vorig = this.vertices.get(vOrig);

        return vorig.getEdge(vDest);
    }

    public V[] endVertices(Edge<V, E> edge) {
        if (edge == null) {
            return null;
        }

        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest())) {
            return null;
        }

        Vertex<V, E> vorig = this.vertices.get(edge.getVOrig());

        if (!edge.equals(vorig.getEdge(edge.getVDest()))) {
            return null;
        }

        return edge.getEndpoints();
    }

    public V opposite(V vert, Edge<V, E> edge) {
        if (!validVertex(vert)) {
            return null;
        }

        Vertex<V, E> vertex = this.vertices.get(vert);

        return vertex.getAdjVert(edge);
    }

    public int outDegree(V vert) {
        if (!validVertex(vert)) {
            return -1;
        }

        Vertex<V, E> vertex = this.vertices.get(vert);

        return vertex.numAdjVerts();
    }

    public int inDegree(V vert) {
        if (!validVertex(vert)) {
            return -1;
        }

        int degree = 0;
        for (V otherVert : this.vertices.keySet()) {
            if (getEdge(otherVert, vert) != null) {
                degree++;
            }
        }

        return degree;
    }

    public Iterable<Edge<V, E>> outgoingEdges(V vert) {
        if (!validVertex(vert)) {
            return null;
        }

        Vertex<V, E> vertex = this.vertices.get(vert);

        return vertex.getAllOutEdges();
    }

    public Iterable<Edge<V, E>> incomingEdges(V vert) {
        List<Edge<V, E>> edges_list = new ArrayList<>();

        if (!validVertex(vert)) 
            return null;
        
        for (V otherVert : this.vertices() ){
            Edge<V,E> newEdge = getEdge(otherVert, vert);
            if (newEdge != null && !edges_list.contains(newEdge)){
                edges_list.add(newEdge);
            }
        }
        
        return edges_list;
    }

    public boolean insertVertex(V vert) {
        if (validVertex(vert)) {
            return false;
        }

        Vertex<V, E> vertex = new Vertex<>(this.numVert, vert);
        this.vertices.put(vert, vertex);
        this.numVert++;

        return true;
    }

    public boolean insertEdge(V vOrig, V vDest, E eInf, double eWeight) {
        if (getEdge(vOrig, vDest) != null) {
            return false;
        }

        if (!validVertex(vOrig)) {
            insertVertex(vOrig);
        }

        if (!validVertex(vDest)) {
            insertVertex(vDest);
        }

        Vertex<V, E> vorig = this.vertices.get(vOrig);
        Vertex<V, E> vdest = this.vertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(eInf, eWeight, vorig, vdest);
        vorig.addAdjVert(vDest, newEdge);
        this.numEdge++;

        //if graph is not direct insert other edge in the opposite direction 
        if (!this.isDirected) // if vDest different vOrig
            if (getEdge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>(eInf, eWeight, vdest, vorig);
                vdest.addAdjVert(vOrig, otherEdge);
                this.numEdge++;
            }

        return true;
    }

    public boolean removeVertex(V vert) {
        if (!validVertex(vert)) {
            return false;
        }

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            V vadj = edge.getVOrig();
            removeEdge(vadj, vert);
        }

        Vertex<V, E> vertex = this.vertices.get(vert);

        //update the keys of subsequent vertices in the map
        for (Vertex<V, E> v : this.vertices.values()) {
            int keyVert = v.getKey();
            if (keyVert > vertex.getKey()) {
                keyVert = keyVert - 1;
                v.setKey(keyVert);
            }
        }

        //The edges that live from vert are removed with the vertex    
        this.vertices.remove(vert);

        this.numVert--;

        return true;
    }

    public boolean removeEdge(V vOrig, V vDest) {
        if (!validVertex(vOrig) || !validVertex(vDest)) {
            return false;
        }

        Edge<V, E> edge = getEdge(vOrig, vDest);

        if (edge == null) {
            return false;
        }

        Vertex<V, E> vorig = this.vertices.get(vOrig);

        vorig.remAdjVert(vDest);
        this.numEdge--;

        //if graph is not direct 
        if (!this.isDirected) {
            edge = getEdge(vDest, vOrig);
            if (edge != null) {
                Vertex<V, E> vdest = this.vertices.get(vDest);
                vdest.remAdjVert(vOrig);
                this.numEdge--;
            }
        }
        return true;
    }

    //Returns a clone of the graph 
    public Graph<V, E> clone() {
        Graph<V, E> newObject = new Graph<>(this.isDirected);

        //insert all vertices
        for (V vert : this.vertices.keySet()) {
            newObject.insertVertex(vert);
        }

        //insert all edges
        for (V vert1 : this.vertices.keySet()) {
            for (Edge<V, E> e : this.outgoingEdges(vert1)) {
                if (e != null) {
                    V vert2 = this.opposite(vert1, e);
                    newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }
            }
        }

        return newObject;
    }

    /* equals implementation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Graph<V, E> otherGraph = (Graph<V, E>) otherObj;

        if (this.numVert != otherGraph.numVertices() || this.numEdge != otherGraph.numEdges()) {
            return false;
        }

        //graph must have same vertices
        boolean eqvertex;
        for (V v1 : this.vertices()) {
            eqvertex = false;
            for (V v2 : otherGraph.vertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }
        return true;
    }

    //string representation
    @Override
    public String toString() {
        String s = "";
        if (this.numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + this.numVert + " vertices, " + this.numEdge + " edges\n";
            for (Vertex<V, E> vert : this.vertices.values()) {
                s += vert + "\n";
            }
        }
        return s;
    }

}
