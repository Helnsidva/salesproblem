package salesproblem.algorithm;

import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class FindHamiltonianPathTest {
    @org.junit.Test
    public void findCycle() throws Exception {

        //1: пустой граф
        Graph graph = new Graph();

        assertTrue(FindHamiltonianPath.findCycle(graph) == null);

        //2: несвязный граф
        for (int i = 0; i < 5; i++)
            graph.addVertex();
        assertTrue(FindHamiltonianPath.findCycle(graph) == null);
        graph.addNeighbor(0, 1);
        graph.addNeighbor(0, 2);
        graph.addNeighbor(4, 3);
        assertTrue(FindHamiltonianPath.findCycle(graph) == null);

        //3: граф без цикла
        graph.addNeighbor(2, 3);
        assertTrue(FindHamiltonianPath.findCycle(graph) == null);

        //4: сравнение работы двух алгоритмов: у эвристического
        // длина найденного путь должна быть >=
        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            Graph randomGraph = new Graph();
            int randomGraphSize;
            do {
                randomGraphSize = random.nextInt(100);
            } while (randomGraphSize == 0);
            for (int j = 0; j < 30; j++) {
                randomGraph.addNeighbor(random.nextInt(randomGraphSize), random.nextInt(randomGraphSize));
            }
            Map<Integer, Integer> dynamicPath = FindHamiltonianPath.findCycle(randomGraph);
            Map<Integer, Integer> heuristicPath = AnnealingAlgorithm.findPath(randomGraph);
            if (dynamicPath == null)
                continue;

            int dynamicLength = dynamicPath.get(randomGraphSize);
            int heuristicLength = heuristicPath.get(randomGraphSize);
            assertTrue(dynamicLength < heuristicLength);

        }

    }

}
