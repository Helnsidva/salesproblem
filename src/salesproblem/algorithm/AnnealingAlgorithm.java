package salesproblem.algorithm;

import java.util.*;

import static java.lang.Math.*;

//алгоритм имитации отжига
public class AnnealingAlgorithm {

    //возвращается коэффициент вероятность принятия решения
    static private double transitionProbability(int evaluation, double temprature) {
        return exp(-evaluation / temprature);
    }

    // возвращается длина пути path
    static private int evaluatePath(List<Integer> path, Graph graph) {

        int result = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            /*если ребра между вершинами не существует, вместо него
                    прибавляется условная "бесконечность"*/
            result += graph.getNotNullDistance(path.get(i), path.get(i + 1));
        }
        return result;

    }

    //случайное генерирование нового пути
    static private List<Integer> generateNewPath(List<Integer> path, Graph graph) {

        int graphSize = graph.getGraphSize();
        List<Integer> newPath = new ArrayList<>();

        Random random = new Random();
        int i, j;

        /*генерируем такие значения, чтобы при реверсии части пути,
         между переставленными вершинами и их соседями были ребра*/
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

        /*переворачиваем часть пути в интервале от первого сгенерированного
          числа до второго*/
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

        //"температурный" коэффициент
        int startTemperature = graphSize * graphSize * 2;
        Random random = new Random();

        //начальный путь - идущие от 0 по порядку вершины графа
        List<Integer> path = new ArrayList<>();
        for(int i = 0; i < graphSize; i++) {
            path.add(i);
        }
        path.add(0);

        for (int i = 1; i < graphSize * graphSize; i++) {

            List<Integer> newPath = generateNewPath(path, graph);
            int evaluation = evaluatePath(path, graph);
            int newEvaluation = evaluatePath(newPath, graph);

            //если новый путь однозначно лучше - принимаем его
            if (newEvaluation < evaluation) {
                path = newPath;
            }

            /*если нет - считаем вероятность приния данного решения
              если оно достигает нужного значения - принимаем*/
            else if (random.nextDouble() < transitionProbability(newEvaluation - evaluation,
                    (double) startTemperature / i)) {
                path = newPath;
            }

        }
        path.add(evaluatePath(path, graph));

        Map<Integer, Integer> pathMap = new HashMap<>();
        for (int i = 0; i < path.size() - 1; i++)
            pathMap.put(path.get(i), path.get(i + 1));

        //в последний элемент Map записываем длину найденного пути
        pathMap.put(graphSize,evaluatePath(path, graph));

        return pathMap;

    }

}
