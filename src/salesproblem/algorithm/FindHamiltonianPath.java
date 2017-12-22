package salesproblem.algorithm;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.pow;

/*
  поиск кратчайшего цикла в графе, проходящего по каждой вершине
  один раз
*/
public class FindHamiltonianPath {

    private final static int infinity = 1000000;

    /*
      метод возвращает список вершин, входящих в путь,
      соответствующий маске
     */
    private static List<Integer> getIncomingVertices(int mask) {

        List<Integer> output = new ArrayList<>();
        int buffer;
        /*получение номеров разрядов, равных единице,
         у бинарного представления аргумента*/
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

        //проверка графа на связность
        if (DepthSearch.depthSearch(graph) != graphSize)
            return null;

        //проверка на наличие в графе циклов
        for (int i = 0; i < graphSize; i++) {
            if (graph.getVertexDegree(i) < 2)
                return null;
        }

        /* viewedWays - двумерный массив для хранения "масок", соответсвующих каждой вершине:
          размер маски в двоичном представлении соответстувет количеству вершин графа
          и каждый разряд тому, пройдена ли вершина с соответствующим номером
          в рассмотренном пути.
          маски, соответствующие вершине - пути из этой вершины в стартовую, содержающие
          вершины из маски. значения элементов массива - длина пути.
          например, viewedWays[0][15] = viewedWays[0][111 в двоичной системе счисления] -
          путь из вершины с номером 0, проходящий через вершины 1 и 2. */
        int[][] viewedWays = new int[graphSize][(int)pow(2, graphSize)];
        for(int i = 0; i < graphSize; i++) {
            /*для каждого рассматриваемого маршрута до целевой вершины считается кратчайший
              путь, поэтому изначально массив заполняется условными "бесконечностями"*/
            Arrays.fill(viewedWays[i], infinity);
        }

        //начинаем с нулевой вершины
        viewedWays[0][0] = 0;

        int wayLength = findCheapest(0, (int)pow(2, graphSize) - 1, viewedWays, graph);

        List<Integer> outputList = findWay(viewedWays, graph);
        Map<Integer, Integer> outputMap = new HashMap<>();

        for (int i = 0; i < outputList.size() - 1; i++)
            outputMap.put(outputList.get(i), outputList.get(i + 1));

        //в последний элемент Map записывается длина найденного пути
        outputMap.put(graphSize, wayLength);

        return outputMap;

    }

    /*
    метод возвращает длину кратчайшего искомого цикла
     */
    private static int findCheapest(int i, int mask, int[][] viewedWays, Graph graph) {

        if (viewedWays[i][mask] != infinity)
            return viewedWays[i][mask];

        //начинаем с вершины 0 и маски, состоящей только из единиц. это искомый путь
        List<Integer> maskContaining = getIncomingVertices(mask);
        for (int j: maskContaining) {

            if (j == i)
                continue;

            /*если между вершинами есть ребро, то рекурсивно вызываем метод
              для второй вершины. дойдя до viewedWays[0][0], начнут восстанавливаться
              минимальные длины путей, соответствующие своей вершине и маске.*/
            if (graph.getDistance(i, j) != null) {

            //операция XOR - "удаление" вершины из маски (маршрута)
                viewedWays[i][mask] = min(viewedWays[i][mask],
                        findCheapest(j, mask ^ (int)pow(2, j), viewedWays, graph) + graph.getDistance(i, j));

            }

        }
        /*возвращается длина пути от начальной вершины (0) с маской, состоящей
          из всех вершин (таким образом, рассматривается путь по всем вершиными).*/
        return viewedWays[i][mask];

    }

    // восстановление кратчайшего цикла
    private static List<Integer> findWay(int[][] viewedWays, Graph graph) {

        int graphSize = graph.getGraphSize();
        int i = 0;
        int mask = (int)pow(2, graphSize) - 1;

        List<Integer> theWay = new ArrayList<>();

        //рассматриваем путь так же от 0 вершины
        theWay.add(0);

        /*длина пути - размер графа + 1. вершина 0 уже записана,
          поэтому надо рассмотреть количество вершин graphSize*/
        for (int k = 0; k < graphSize; k++) {
            //получение вершин, соответствующих рассматриваемой маске
            List<Integer> maskWays = getIncomingVertices(mask);
            for (int j: maskWays) {

                //нахождение вершины с кратчайшим путем и ее добавление к решению
                if (graph.getDistance(i, j) != null &&
                        viewedWays[i][mask] == viewedWays[j][mask ^ (int)pow(2, j)] + graph.getDistance(i, j)) {

                    theWay.add(j);
                    i = j;
                    //удаление добавленной вершины из маски
                    mask = mask ^ (int)pow(2, j);

                }
            }
        }
        return theWay;

    }

}
