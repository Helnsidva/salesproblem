package salesproblem.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DepthSearch {

    public static Integer depthSearch (Graph graph) {

        if (graph == null)
            return null;

        List<Integer> passedVertices = new ArrayList<>();

        recursiveDepthSearch(graph, 0, passedVertices);

        return passedVertices.size();

    }

    static private void recursiveDepthSearch(Graph graph, int vertex, List<Integer> passedVertices) {

        passedVertices.add(vertex);
        List<Integer> neighborsList = graph.getNeighborsList(vertex);

        for (int i: neighborsList) {

            if (!passedVertices.contains(i)) {

                recursiveDepthSearch(graph, i, passedVertices);

            }

        }

    }


}
