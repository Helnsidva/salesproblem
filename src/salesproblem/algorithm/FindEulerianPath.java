package salesproblem.algorithm;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.pow;

public class FindEulerianPath {

    private final static int infinity = 1000000;

    private static List<Integer> getIncomingVertices(int mask) {

        List<Integer> output = new ArrayList<>();
        int buffer;
        for (int i = 0; mask != 0; i++) {
            buffer = mask % 2;
            mask /= 2;
            if (buffer != 0) {
                output.add(i);
            }
        }
        return output;

    }

    public static Map <Integer, Integer> findCycle(Graph graph) {

        if (graph == null)
            return null;

        int graphSize = graph.getGraphSize();
        if (graphSize == 0)
            return null;

        if (DepthSearch.depthSearch(graph) != graphSize)
            return null;

        if (graphSize > 2) {
            for (int i = 0; i < graphSize; i++) {

                for (int j = 0; j < graphSize; j++) {

                    if (i == j || graph.checkIfNeighbors(i, j))
                        continue;

                    if (graph.getVertexDegree(i) + graph.getVertexDegree(j) < graphSize) {

                        return null;

                    }

                }

            }
        }

        int[][] viewedWays = new int[graphSize][(int)pow(2, graphSize)];
        for(int i = 0; i < graphSize; i++) {
            Arrays.fill(viewedWays[i], infinity);
        }

        viewedWays[0][0] = 0;

        int wayLength = findCheapest(0, (int)pow(2, graphSize) - 1, viewedWays, graph);

        List<Integer> outputList = findWay(viewedWays, graph);
        Map<Integer, Integer> outputMap = new HashMap<>();

        for (int i = 0; i < outputList.size() - 1; i++) {

            outputMap.put(outputList.get(i), outputList.get(i + 1));

        }

        outputMap.put(graphSize, wayLength);

        return outputMap;

    }

    private static int findCheapest(int i, int mask, int[][] viewedWays, Graph graph) {

        if (viewedWays[i][mask] != infinity)
            return viewedWays[i][mask];

        List<Integer> maskContaining = getIncomingVertices(mask);
        for (int j: maskContaining) {

            if (j == i)
                continue;

            if (graph.getDistance(i, j) != null) {

                viewedWays[i][mask] = min(viewedWays[i][mask],
                        findCheapest(j, mask ^ (int)pow(2, j), viewedWays, graph) + graph.getDistance(i, j));

            }

        }
        return viewedWays[i][mask];

    }

    private static List<Integer> findWay(int[][] viewedWays, Graph graph) {

        int graphSize = graph.getGraphSize();
        int i = 0;
        int mask = (int)pow(2, graphSize) - 1;

        List<Integer> theWay = new ArrayList<>();

        theWay.add(0);

        while (mask != 0) {
            List<Integer> maskWays = getIncomingVertices(mask);
            for (int j: maskWays) {

                if (graph.getDistance(i, j) != null &&
                        viewedWays[i][mask] == viewedWays[j][mask ^ (int)pow(2, j)] + graph.getDistance(i, j)) {

                    theWay.add(j);
                    i = j;
                    mask = mask ^ (int)pow(2, j);

                }
            }
        }
        return theWay;

    }

}
