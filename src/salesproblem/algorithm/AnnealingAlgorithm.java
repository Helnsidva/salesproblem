package salesproblem.algorithm;

import java.util.*;

import static java.lang.Math.*;

public class AnnealingAlgorithm {

    static private double transitionProbability(int evaluation, double temprature) {
        return exp(-evaluation / temprature);
    }

    static private int evaluatePath(List<Integer> path, Graph graph) {

        int result = 0;
        for (int i = 0; i < path.size() - 1; i++) {

            result += graph.getNotNullDistance(path.get(i), path.get(i + 1));

        }
        return result;

    }

    static private List<Integer> generateNewPath(List<Integer> path, Graph graph) {

        int graphSize = graph.getGraphSize();
        List<Integer> newPath = new ArrayList<>();

        Random random = new Random();
        int i, j;
        do {
            do {
                i = random.nextInt(graphSize - 1);
            } while (i == 0);
            do {
                j = random.nextInt(graphSize - 1);
            } while (j == 0);
        } while (graph.getDistance(i - 1, j - 1) == null || graph.getDistance(i, j) == null);

        int first = min(i, j);
        int second = max(i, j);

        newPath.addAll(path.subList(0, first));
        List<Integer> reverseBuffer = path.subList(first, second);
        Collections.reverse(reverseBuffer);
        newPath.addAll(reverseBuffer);
        newPath.addAll(path.subList(second, graphSize + 1));

        return newPath;

    }

    public static Map <Integer, Integer> findPath(Graph graph) {

        if (graph == null)
            return null;
        int graphSize = graph.getGraphSize();
        if (graphSize == 0)
            return null;

        int startTemperature = graphSize * graphSize * 2;
        Random random = new Random();

        List<Integer> path = new ArrayList<>();
        for(int i = 0; i < graphSize; i++) {
            path.add(i);
        }
        path.add(0);

        for (int i = 1; i < graphSize * graphSize; i++) {

            List<Integer> newPath = generateNewPath(path, graph);
            int evaluation = evaluatePath(path, graph);
            int newEvaluation = evaluatePath(newPath, graph);

            if (newEvaluation < evaluation) {
                path = newPath;
            }

            else if (random.nextDouble() < transitionProbability(newEvaluation - evaluation,
                    (double) startTemperature / i)) {
                path = newPath;
            }

        }
        path.add(evaluatePath(path, graph));

        Map<Integer, Integer> pathMap = new HashMap<>();
        for (int i = 0; i < path.size() - 1; i++)
            pathMap.put(path.get(i), path.get(i + 1));


        pathMap.put(graphSize,evaluatePath(path, graph));

        return pathMap;

    }

}
