package lapr.project.utils;

import java.lang.reflect.Array;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public class Edge<V, E> implements Comparable {

    private E element;             // Edge information
    private double weight;        // Edge weight
    private Vertex<V, E> vOrig;   // vertex origin
    private Vertex<V, E> vDest;  // vertex destination

    public Edge() {
        this.element = null;
        this.weight = 0.0;
        this.vOrig = null;
        this.vDest = null;
    }

    public Edge(E eInf, double ew, Vertex<V, E> vo, Vertex<V, E> vd) {
        this.element = eInf;
        this.weight = ew;
        this.vOrig = vo;
        this.vDest = vd;
    }

    public E getElement() {
        return this.element;
    }

    public void setElement(E eInf) {
        this.element = eInf;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double ew) {
        this.weight = ew;
    }

    public V getVOrig() {
        if (this.vOrig != null) {
            return this.vOrig.getElement();
        }
        return null;
    }

    public void setVOrig(Vertex<V, E> vo) {
        this.vOrig = vo;
    }

    public V getVDest() {
        if (this.vDest != null) {
            return this.vDest.getElement();
        }
        return null;
    }

    public void setVDest(Vertex<V, E> vd) {
        this.vDest = vd;
    }

    public V[] getEndpoints() {
        V oElem = null;
        V dElem = null;
        V typeElem = null;

        if (this.vOrig != null) {
            oElem = this.vOrig.getElement();
        }

        if (this.vDest != null) {
            dElem = this.vDest.getElement();
        }

        if (oElem == null && dElem == null) {
            return null;
        }

        if (oElem != null) // To get type
        {
            typeElem = oElem;
        }

        if (dElem != null) {
            typeElem = dElem;
        }

        @SuppressWarnings("unchecked")
        V[] endverts = (V[]) Array.newInstance(typeElem.getClass(), 2);

        endverts[0] = oElem;
        endverts[1] = dElem;

        return endverts;
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
        Edge<V, E> otherEdge = (Edge<V, E>) otherObj;

        // if endpoints vertices are not equal
        if ((this.vOrig == null && otherEdge.vOrig != null)
                || (this.vOrig != null && otherEdge.vOrig == null)) {
            return false;
        }

        if ((this.vDest == null && otherEdge.vDest != null)
                || (this.vDest != null && otherEdge.vDest == null)) {
            return false;
        }

        if (this.vOrig != null && otherEdge.vOrig != null
                && !this.vOrig.equals(otherEdge.vOrig)) {
            return false;
        }

        if (this.vDest != null && otherEdge.vDest != null
                && !this.vDest.equals(otherEdge.vDest)) {
            return false;
        }

        if (this.weight != otherEdge.weight) {
            return false;
        }

        if (this.element != null && otherEdge.element != null) {
            return this.element.equals(otherEdge.element);
        }

        return true;
    }

    @Override
    public int compareTo(Object otherObject) {

        @SuppressWarnings("unchecked")
        Edge<V, E> other = (Edge<V, E>) otherObject;
        if (this.weight < other.weight) {
            return -1;
        }
        if (this.weight == other.weight) {
            return 0;
        }
        return 1;
    }

    @Override
    public Edge<V, E> clone() {

        Edge<V, E> newEdge = new Edge<>();

        newEdge.element = this.element;
        newEdge.weight = this.weight;
        newEdge.vOrig = this.vOrig;
        newEdge.vDest = this.vDest;

        return newEdge;
    }

    @Override
    public String toString() {
        String st = "";
        if (this.element != null) {
            st = "      (" + this.element + ") - ";
        } else {
            st = "\t ";
        }

        if (this.weight != 0) {
            st += this.weight + " - " + this.vDest.getElement() + "\n";
        } else {
            st += this.vDest.getElement() + "\n";
        }

        return st;
    }

}
