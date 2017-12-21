package salesproblem.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private class VertexList {

        int vertexNumber;
        List<Integer> vertexNeighbors = new ArrayList<>();
        List<Integer> neighborsDistance = new ArrayList<>();
        int neighborsNumber;

        VertexList(int argNumber) {
            vertexNumber = argNumber;
            neighborsNumber = 0;
        }

        void addNeighbor (int argNumber, int argDistance) {

            vertexNeighbors.add(argNumber);
            neighborsDistance.add(argDistance);
            neighborsNumber++;

        }

        void removeNeighbor (int argNumber) {

            if (!vertexNeighbors.contains(argNumber))
                return;
            int index = vertexNeighbors.indexOf(argNumber);
            vertexNeighbors.remove(index);
            neighborsDistance.remove(index);
            neighborsNumber--;

        }

        Integer getDistance (int argVetex) {

            if (!vertexNeighbors.contains(argVetex))
                return null;
            int index = vertexNeighbors.indexOf(argVetex);
            return neighborsDistance.get(index);

        }

        List<Integer> getNeighborsList () {

            return vertexNeighbors;

        }

        void removingShift(int deletingVertex) {

            if (deletingVertex < vertexNumber)
                vertexNumber--;
            for (int i = 0; i < vertexNeighbors.size(); i++) {
                int oldValue = vertexNeighbors.get(i);
                if (oldValue == deletingVertex) {
                    vertexNeighbors.remove(i);
                    neighborsDistance.remove(i);
                    neighborsNumber--;
                }
                else if (oldValue > deletingVertex) {
                    oldValue--;
                    vertexNeighbors.set(i, oldValue);
                }

            }

        }

        int getNeighborsNumber() {
            return neighborsNumber;
        }

    }

    private List<VertexList> adjacencyList = new ArrayList<>();
    private int graphSize = 0;

    public int addVertex() {

        int addingVertexNumber = adjacencyList.size();
        VertexList addingVertex = new VertexList(addingVertexNumber);
        adjacencyList.add(addingVertex);
        graphSize++;
        return addingVertexNumber;

    }

    public void removeVertex(int vertexNumber) {

        if (vertexNumber >= graphSize)
            return;
        for (int i = 0; i < adjacencyList.size(); i++) {

            adjacencyList.get(i).removingShift(vertexNumber);

        }
        adjacencyList.remove(vertexNumber);
        graphSize--;

    }

    public void removeEdge(int source, int target) {

        if (source >= graphSize || target >= graphSize)
            return;
        adjacencyList.get(source).removeNeighbor(target);
        adjacencyList.get(target).removeNeighbor(source);

    }

    public void addNeighbor(int vertexNumber, int neighborNumber, int distance) {

        if (vertexNumber >= adjacencyList.size() || neighborNumber >= adjacencyList.size())
            return;
        adjacencyList.get(vertexNumber).addNeighbor(neighborNumber, distance);
        adjacencyList.get(neighborNumber).addNeighbor(vertexNumber, distance);

    }

    public void addNeighbor(int vertexNumber, int neighborNumber) {

        if (vertexNumber >= adjacencyList.size() || neighborNumber >= adjacencyList.size())
            return;
        adjacencyList.get(vertexNumber).addNeighbor(neighborNumber, 1);
        adjacencyList.get(neighborNumber).addNeighbor(vertexNumber, 1);

    }

    void printGraphData() {

        for (VertexList i: adjacencyList) {

            System.out.println("vertex number: " + i.vertexNumber + " neighbors: " + i.vertexNeighbors +
                    " distances: " + i.neighborsDistance);

        }

    }

    Integer getDistance(int firstVertex, int secondVertex) {

        if (firstVertex >= graphSize || secondVertex >= graphSize)
            return null;
        return adjacencyList.get(firstVertex).getDistance(secondVertex);

    }

    Integer getNotNullDistance(int firstVertex, int secondVertex) {

        int infinity = 10000;
        if (firstVertex >= graphSize || secondVertex >= graphSize)
            return null;
        Integer distance = adjacencyList.get(firstVertex).getDistance(secondVertex);
        return (distance != null) ? distance : infinity;

    }

    public int getGraphSize() {

        return graphSize;

    }

    List<Integer> getNeighborsList (int vertexNumber) {

        if (vertexNumber >= graphSize)
            return null;
        return adjacencyList.get(vertexNumber).getNeighborsList();

    }

    public List<List<Map<Integer, Integer>>> getAdjencityList () {

        List<List<Map<Integer, Integer>>> outputAdjencityList = new ArrayList<>();
        for (VertexList vertex : adjacencyList) {
            List<Map<Integer, Integer>> vertexList = new ArrayList<>();
            for (int i = 0; i < vertex.getNeighborsNumber(); i++) {
                Map<Integer, Integer> neighborAndDistance = new HashMap<>();
                neighborAndDistance.put(vertex.vertexNeighbors.get(i), vertex.neighborsDistance.get(i));
                vertexList.add(neighborAndDistance);
            }
            outputAdjencityList.add(vertexList);
        }
        return outputAdjencityList;

    }

    public String getAdjacencyListForPrint () {

        String outputAdjacencyListString = new String();
        outputAdjacencyListString += "Adjacency list:\n";
        outputAdjacencyListString += "[ vertex number ] [ vertex neighbors ] [ distance ]\n";
        for (Integer i = 0; i < adjacencyList.size(); i++) {
            VertexList vertex = adjacencyList.get(i);
            outputAdjacencyListString += "[ " + i.toString() + " ] [ ";
            if (vertex.vertexNeighbors.isEmpty()) {
                outputAdjacencyListString += "] [ ]\n";
                continue;
            }
            for (int j = 0; j < vertex.getNeighborsNumber(); j++) {
                outputAdjacencyListString += vertex.vertexNeighbors.get(j).toString() + ", ";
            }
            outputAdjacencyListString = outputAdjacencyListString.substring(0, outputAdjacencyListString.length() - 2);
            outputAdjacencyListString += "] [ ";
            for (int j = 0; j < vertex.getNeighborsNumber(); j++) {
                outputAdjacencyListString += vertex.neighborsDistance.get(j).toString() + ", ";
            }
            outputAdjacencyListString = outputAdjacencyListString.substring(0, outputAdjacencyListString.length() - 2);
            outputAdjacencyListString += "]\n";
        }
        return outputAdjacencyListString;

    }

    public boolean checkIfNeighbors (int source, int target) {

        return adjacencyList.get(source).vertexNeighbors.contains(target);

    }

    public int getVertexDegree (int vertex) {

        return adjacencyList.get(vertex).vertexNeighbors.size();

    }

}
