package lapr.project.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import org.junit.After;
import org.junit.BeforeClass;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ISEP
 */
public class GraphAlgorithmsTest {

    Graph<String, String> completeMap = new Graph<>(false);
    Graph<String, String> incompleteMap = new Graph<>(false);

    public GraphAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto", "Aveiro", "A1", 75);
        completeMap.insertEdge("Porto", "Braga", "A3", 60);
        completeMap.insertEdge("Porto", "Vila Real", "A4", 100);
        completeMap.insertEdge("Viseu", "Guarda", "A25", 75);
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23", 100);
        completeMap.insertEdge("Aveiro", "Coimbra", "A1", 60);
        completeMap.insertEdge("Coimbra", "Lisboa", "A1", 200);
        completeMap.insertEdge("Coimbra", "Leiria", "A34", 80);
        completeMap.insertEdge("Aveiro", "Leiria", "A17", 120);
        completeMap.insertEdge("Leiria", "Lisboa", "A8", 150);

        completeMap.insertEdge("Aveiro", "Viseu", "A25", 85);
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        completeMap.insertEdge("Lisboa", "Faro", "A2", 280);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge("Aveiro", "Viseu");
        incompleteMap.removeEdge("Leiria", "Castelo Branco");
        incompleteMap.removeEdge("Lisboa", "Faro");
    }

    @After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of BreadthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        // "Should be null if vertex does not exist"
        assertTrue(GraphAlgorithms.BreadthFirstSearch(completeMap, "LX") == null);

        LinkedList<String> path = GraphAlgorithms.BreadthFirstSearch(incompleteMap, "Faro");

        // "Should be just one"
        assertTrue(path.size() == 1);

        Iterator<String> it = path.iterator();
        // "it should be Faro"
        assertTrue(it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.BreadthFirstSearch(incompleteMap, "Porto");
        // "Should give seven vertices "
        assertTrue(path.size() == 7);
        path = GraphAlgorithms.BreadthFirstSearch(incompleteMap, "Viseu");
        // "Should give 3 vertices"
        assertTrue(path.size() == 3);
    }

    /**
     * Test of DepthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testDepthFirstSearch() {
        System.out.println("Test of DepthFirstSearch");

        LinkedList<String> path;
        // "Should be null if vertex does not exist"
        assertTrue(GraphAlgorithms.DepthFirstSearch(completeMap, "LX") == null);

        path = GraphAlgorithms.DepthFirstSearch(incompleteMap, "Faro");
        // "Should be just one"
        assertTrue(path.size() == 1);

        Iterator<String> it = path.iterator();
        // it should be Faro"
        assertTrue(it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.DepthFirstSearch(incompleteMap, "Porto");
        // "Should give seven vertices "
        assertTrue(path.size() == 7);

        path = GraphAlgorithms.DepthFirstSearch(incompleteMap, "Viseu");
        // "Should give 3 vertices"
        assertTrue(path.size() == 3);

        it = path.iterator();
        // "First in visit should be Viseu"
        assertTrue(it.next().compareTo("Viseu") == 0);
        // "then Guarda"
        assertTrue(it.next().compareTo("Guarda") == 0);
        // "then Castelo Branco"
        assertTrue(it.next().compareTo("Castelo Branco") == 0);
    }

    /**
     * Test of allPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testAllPaths() {
        System.out.println("Test of all paths");

        ArrayList<LinkedList<String>> paths = new ArrayList<LinkedList<String>>();

        paths = GraphAlgorithms.allPaths(completeMap, "Porto", "LX");
        // "There should not be paths if vertex does not exist"
        assertTrue(paths.size() == 0);

        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Lisboa");
        // "There should be 4 paths"
        assertTrue(paths.size() == 4);

        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Faro");
        // "There should not be paths between Porto and Faro in the incomplete map"
        assertTrue(paths.size() == 0);
    }

    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<String>();
        double lenpath = 0;
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "LX", shortPath);
        // "Length path should be 0 if vertex does not exist"
        assertTrue(lenpath == 0);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Faro", shortPath);
        // "Length path should be 0 if there is no path"
        assertTrue(lenpath == 0);

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Porto", shortPath);
        // "Number of nodes should be 1 if source and vertex are the same"
        assertTrue(shortPath.size() == 1);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Lisboa", shortPath);
        // "Path between Porto and Lisboa should be 335 Km"
        assertTrue(lenpath == 335);

        Iterator<String> it = shortPath.iterator();

        // "First in path should be Porto", 
        assertTrue(it.next().compareTo("Porto") == 0);
        // "then Aveiro", 
        assertTrue(it.next().compareTo("Aveiro") == 0);
        // "then Coimbra", 
        assertTrue(it.next().compareTo("Coimbra") == 0);
        // "then Lisboa", 
        assertTrue(it.next().compareTo("Lisboa") == 0);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Braga", "Leiria", shortPath);
        // "Path between Braga and Leiria should be 255 Km"
        assertTrue(lenpath == 255);

        it = shortPath.iterator();

        // "First in path should be Braga"
        assertTrue(it.next().compareTo("Braga") == 0);
        // "then Porto"
        assertTrue(it.next().compareTo("Porto") == 0);
        // "then Aveiro"
        assertTrue(it.next().compareTo("Aveiro") == 0);
        // "then Leiria"
        assertTrue(it.next().compareTo("Leiria") == 0);

        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        // "Path between Porto and Castelo Branco should be 335 Km"
        assertTrue(lenpath == 335);
        // "N. cities between Porto and Castelo Branco should be 5 "
        assertTrue(shortPath.size() == 5);

        it = shortPath.iterator();

        // "First in path should be Porto"
        assertTrue(it.next().compareTo("Porto") == 0);
        // "then Aveiro"
        assertTrue(it.next().compareTo("Aveiro") == 0);
        // "then Viseu"
        assertTrue(it.next().compareTo("Viseu") == 0);
        // "then Guarda",
        assertTrue(it.next().compareTo("Guarda") == 0);
        // "then Castelo Branco"
        assertTrue(it.next().compareTo("Castelo Branco") == 0);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        // "Path between Porto and Castelo Branco should now be 365 Km"
        assertTrue(lenpath == 365);
        // "Path between Porto and Castelo Branco should be 4 cities"
        assertTrue(shortPath.size() == 4);

        it = shortPath.iterator();

        // "First in path should be Porto"
        assertTrue(it.next().compareTo("Porto") == 0);
        // "then Aveiro"
        assertTrue(it.next().compareTo("Aveiro") == 0);
        // "then Leiria"
        assertTrue(it.next().compareTo("Leiria") == 0);
        // "then Castelo Branco"
        assertTrue(it.next().compareTo("Castelo Branco") == 0);

    }

    /**
     * Test of shortestPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists);

        // "There should be as many paths as sizes"
        assertEquals(paths.size(), dists.size());
        // "There should be a path to every vertex"
        assertEquals(completeMap.numVertices(), paths.size());
        // "Number of nodes should be 1 if source and vertex are the same"
        assertEquals(1, paths.get(completeMap.getKey("Porto")).size());
        // "Path to Lisbon"
        assertEquals(Arrays.asList("Porto", "Aveiro", "Coimbra", "Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        // "Path to Castelo Branco"
        assertEquals(Arrays.asList("Porto", "Aveiro", "Viseu", "Guarda", "Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));
        // "Path between Porto and Castelo Branco should be 335 Km"
        assertEquals(335, dists.get(completeMap.getKey("Castelo Branco")), 0.01);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco        
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists);
        // "Path between Porto and Castelo Branco should now be 365 Km"
        assertEquals(365, dists.get(completeMap.getKey("Castelo Branco")), 0.01);
        // "Path to Castelo Branco"
        assertEquals(Arrays.asList("Porto", "Aveiro", "Leiria", "Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));

        GraphAlgorithms.shortestPaths(incompleteMap, "Porto", paths, dists);
        // "Length path should be Double.MAX_VALUE if there is no path"
        assertEquals(Double.MAX_VALUE, dists.get(completeMap.getKey("Faro")), 0.01);
        // "Path between Porto and Lisboa should be 335 Km"
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")), 0.01);
        // "Path to Lisboa"
        assertEquals(Arrays.asList("Porto", "Aveiro", "Coimbra", "Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        // "Path between Porto and Lisboa should be 335 Km"
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")), 0.01);

        GraphAlgorithms.shortestPaths(incompleteMap, "Braga", paths, dists);
        // "Path between Braga and Leiria should be 255 Km"
        assertEquals(255, dists.get(completeMap.getKey("Leiria")), 0.01);
        // "Path to Leiria"
        assertEquals(Arrays.asList("Braga", "Porto", "Aveiro", "Leiria"), paths.get(completeMap.getKey("Leiria")));

        boolean expectedResult = false;
        assertEquals(GraphAlgorithms.shortestPaths(completeMap, "Nardia", paths, dists), expectedResult);

        expectedResult = true;
        assertEquals(GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists), expectedResult);
    }

    /**
     * Test of ColouringAlgorithms method, of class GraphAlgorithms.
     */
    @Test
    public void testColouringAlgorithms() {
        System.out.println("Test of colouring algorithm");

        HashMap<String, Integer> portugal = GraphAlgorithms.ColouringAlgorithms(completeMap);

        int maxColourUsed = Integer.MIN_VALUE;
        for (String cidade : portugal.keySet()) {
            if (portugal.get(cidade) > maxColourUsed) {
                maxColourUsed = portugal.get(cidade);
            }
        }

        // "Max number of colours used must be 3"
        assertEquals(2, maxColourUsed);

        int numberOne = portugal.get("Porto");
        // "Colour of Porto must be number 1"
        assertEquals(1, numberOne);
    }

    /**
     * Test of ConstrainedShortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testConstrainedShortestPath() {
        System.out.println("Test of constrained shortest path");

        List<String> city = new ArrayList<>();

        city.add("Viseu");
        assertTrue(GraphAlgorithms.ConstrainedShortestPath(completeMap, "Porto", "Castelo Branco", city).size() == 5);
        assertNull(GraphAlgorithms.ConstrainedShortestPath(completeMap, "Porto", "Madrid", city));
    }

    /**
     * Test of getPosition method, of class GraphAlgorithms.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        int[] v = {1, 0};
        int position = 2;
        int expResult = -1;
        int result = GraphAlgorithms.getPosition(v, position);
        assertEquals(expResult, result);
    }

    /**
     * Test of findOptimiseWithCustomeStops method, of class GraphAlgorithms.
     */
    @Test
    public void testFindOptimiseWithCustomeStops() {
        System.out.println("findOptimiseWithCustomeStops");
        Graph<Address, String> mapaPorto = new Graph<>(false);

        Address Trindade = new Address(1L, "Portugal", "Porto", "Trindade", "4420-000", 41.15227, -8.60929);
        Address Clerigos = new Address(2L, "Portugal", "Porto", "Clerigos", "4420-000", 41.14582, -8.61398);
        Address Majestic = new Address(3L, "Portugal", "Porto", "Majestic", "4420-000", 41.14723, -8.60657);
        Address Bolhao = new Address(4L, "Portugal", "Porto", "Bolhao", "4420-000", 41.14871, -8.60746);
        Address Se = new Address(5L, "Portugal", "Porto", "Se", "4420-000", 41.14331, -8.60914);
        Address CaisDaRibeira = new Address(6L, "Portugal", "Porto", "Cais da Ribeira", "4420-000", 41.14063, -8.61118);

        mapaPorto.insertVertex(Trindade);
        mapaPorto.insertVertex(Clerigos);
        mapaPorto.insertVertex(Majestic);
        mapaPorto.insertVertex(Bolhao);
        mapaPorto.insertVertex(Se);
        mapaPorto.insertVertex(CaisDaRibeira);

        mapaPorto.insertEdge(Trindade, Clerigos, "Trindade - Clerigos", 818);
        mapaPorto.insertEdge(Clerigos, Bolhao, "Clerigos - Bolhao", 633);
        mapaPorto.insertEdge(Clerigos, Majestic, "Clerigos - Majestic", 640);
        mapaPorto.insertEdge(Bolhao, CaisDaRibeira, "Bolhao - Cais da Ribeira", 950);
        mapaPorto.insertEdge(Majestic, CaisDaRibeira, "Majestic - Cais da Ribeira", 829);

        ArrayList<Address> stops = new ArrayList<>();
        stops.add(CaisDaRibeira);
        stops.add(Bolhao);

        LinkedList<Address> finalPath = new LinkedList<>();
        double expResult = 3052.0;
        double result = GraphAlgorithms.findOptimiseWithCustomeStops(mapaPorto, Majestic, Majestic, stops, finalPath);
        assertEquals(expResult, result, 0.001);

        stops.remove(CaisDaRibeira);
        stops.remove(Bolhao);
        
        Address Nardia = new Address(666L, "Nardia", "Muito longe", "North Nardia", "4420", 42.0, 8.0);
        stops.add(Nardia);
        
        double expResult2 = 0.0;
        double result2 = GraphAlgorithms.findOptimiseWithCustomeStops(mapaPorto, Majestic, Majestic, stops, finalPath);
        assertEquals(expResult2, result2, 0.001);
    }

}
