package lapr.project.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public class Vertex<V, E> {

    private int key;                     //Vertex key number
    private V element;                 //Vertex information
    private final Map<V, Edge<V, E>> outVerts; //adjacent vertices

    public Vertex() {
        this.key = -1;
        this.element = null;
        this.outVerts = new LinkedHashMap<>();
    }

    public Vertex(int k, V vInf) {
        this.key = k;
        this.element = vInf;
        this.outVerts = new LinkedHashMap<>();
    }

    public Vertex(Vertex<V, E> v) {
        this.key = v.getKey();
        this.element = v.getElement();
        this.outVerts = new LinkedHashMap<>();
        for (V vert : v.outVerts.keySet()) {
            Edge<V, E> edge = v.outVerts.get(vert);
            this.outVerts.put(vert, edge);
        }
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int k) {
        this.key = k;
    }

    public V getElement() {
        return this.element;
    }

    public void setElement(V vInf) {
        this.element = vInf;
    }

    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        this.outVerts.put(vAdj, edge);
    }

    public V getAdjVert(Edge<V, E> edge) {

        for (V vert : this.outVerts.keySet()) {
            if (edge.equals(this.outVerts.get(vert))) {
                return vert;
            }
        }

        return null;
    }

    public void remAdjVert(V vAdj) {
        this.outVerts.remove(vAdj);
    }

    public Edge<V, E> getEdge(V vAdj) {
        return this.outVerts.get(vAdj);
    }

    public int numAdjVerts() {
        return this.outVerts.size();
    }

    public Iterable<V> getAllAdjVerts() {
        return this.outVerts.keySet();
    }

    public Iterable<Edge<V, E>> getAllOutEdges() {
        return this.outVerts.values();
    }

    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Vertex<V, E> otherVertex = (Vertex<V, E>) otherObj;

        if (this.key != otherVertex.key) {
            return false;
        }

        if (this.element != null && otherVertex.element != null
                && !this.element.equals(otherVertex.element)) {
            return false;
        }

        //adjacency vertices should be equal
        if (this.numAdjVerts() != otherVertex.numAdjVerts()) {
            return false;
        }

        //and edges also
        Iterator<Edge<V, E>> it1 = this.getAllOutEdges().iterator();
        while (it1.hasNext()) {
            Iterator<Edge<V, E>> it2 = otherVertex.getAllOutEdges().iterator();
            boolean exists = false;
            while (it2.hasNext()) {
                if (it1.next().equals(it2.next())) {
                    exists = true;
                }
            }
            if (!exists) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Vertex<V, E> clone() {

        Vertex<V, E> newVertex = new Vertex<>();

        newVertex.setKey(this.key);
        newVertex.setElement(this.element);

        for (V vert : this.outVerts.keySet()) {
            newVertex.addAdjVert(vert, this.getEdge(vert));
        }

        return newVertex;
    }

    @Override
    public String toString() {
        String st = "";
        if (this.element != null) {
            st = this.element + " (" + this.key + "): \n";
        }
        if (!this.outVerts.isEmpty()) {
            for (V vert : this.outVerts.keySet()) {
                st += this.outVerts.get(vert);
            }
        }

        return st;
    }

}
