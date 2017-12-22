package salesproblem.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import salesproblem.algorithm.*;
import javax.swing.*;

public class MainGUI extends javax.swing.JFrame {

    private Graph graph;
    /*
    для обработки событий кнопок есть 7 "режимов":
    1 - добавление вершины
    2, 3 - добавление ребра
    4 - удаление вершины
    5, 6 - удаление ребра
    7 - построение и вывод решения
     */
    private int graphInputMode = 0;

    private MainGUI() {
        initComponents();
        graph = new Graph();
    }

    private void initComponents() {

        graphField = new DrawPanel();
        JButton addVertexButton = new JButton();
        JButton addEdgeButton = new JButton();
        JButton removeVertexButton = new JButton();
        JButton removeEdgeButton = new JButton();
        JButton buildTheWayButton = new JButton();
        JScrollPane caption = new JScrollPane();
        JTextArea captionArea = new JTextArea();
        JScrollPane solutionWindow = new JScrollPane();
        solutionWindowArea = new javax.swing.JTextArea();
        JScrollPane adjListWindow = new JScrollPane();
        adjListWindowArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        graphField.setBackground(new java.awt.Color(255, 255, 255));
        graphField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout graphFieldLayout = new javax.swing.GroupLayout(graphField);
        graphField.setLayout(graphFieldLayout);
        graphFieldLayout.setHorizontalGroup(
                graphFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        graphFieldLayout.setVerticalGroup(
                graphFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 184, Short.MAX_VALUE)
        );

        addVertexButton.setText("Add vertex");
        addVertexButton.addActionListener(this::addVertexButtonActionPerformed);

        addEdgeButton.setText("Add edge");
        addEdgeButton.addActionListener(this::addEdgeButtonActionPerformed);

        removeVertexButton.setText("Remove vertex");
        removeVertexButton.addActionListener(this::removeVertexButtonActionPerformed);

        removeEdgeButton.setText("Remove edge");
        removeEdgeButton.addActionListener(this::removeEdgeButtonActionPerformed);

        buildTheWayButton.setText("Build the way");
        buildTheWayButton.addActionListener(this::buildTheWayButtonActionPerformed);
        captionArea.setColumns(20);
        captionArea.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 12)); // NOI18N
        captionArea.setRows(5);
        captionArea.setText("             Here you can build a graph and find the solution of the salesman problem for it.");
        caption.setViewportView(captionArea);

        solutionWindowArea.setColumns(20);
        solutionWindowArea.setRows(5);
        solutionWindow.setViewportView(solutionWindowArea);

        adjListWindowArea.setColumns(20);
        adjListWindowArea.setRows(5);
        adjListWindow.setViewportView(adjListWindowArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(adjListWindow, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(solutionWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(removeVertexButton)
                                                        .addComponent(removeEdgeButton)
                                                        .addComponent(buildTheWayButton)
                                                        .addComponent(addEdgeButton)
                                                        .addComponent(addVertexButton))
                                                .addGap(18, 18, 18)
                                                .addComponent(graphField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addComponent(caption, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(caption, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addVertexButton)
                                                .addGap(17, 17, 17)
                                                .addComponent(addEdgeButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(removeVertexButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(removeEdgeButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(buildTheWayButton))
                                        .addComponent(graphField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(solutionWindow, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(adjListWindow))
                                .addContainerGap())
        );

        pack();
    }

    private void addVertexButtonActionPerformed(java.awt.event.ActionEvent evt) {
        graphInputMode = 1;
    }

    private void addEdgeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        graphInputMode = 2;
    }

    private void removeVertexButtonActionPerformed(java.awt.event.ActionEvent evt) {
        graphInputMode = 4;
    }

    private void removeEdgeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        graphInputMode = 5;
    }

    private void buildTheWayButtonActionPerformed(java.awt.event.ActionEvent evt) {

        graphInputMode = 7;
        graphField.repaint();

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    public class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        //список вершин и ребер хранится для перерисовки контейнера
        private ArrayList<Point> vertices;
        private ArrayList<ArrayList<Integer>> edges;
        private Map<Integer, Integer> salesmanPath;
        Integer deletingBuffer = null;

        //получение индекса вершины по координатам
        private Integer getVertexIndex(int x, int y) {

            for (int i = 0; i < vertices.size(); i++) {
                if (((x - vertices.get(i).x) < 10) && ((x - vertices.get(i).x) > 0) &&
                    ((y - vertices.get(i).y) < 10) && ((y - vertices.get(i).y) > 0))
                    return i;
            }
            return null;

        }

        //добавление вершины по координатам
        //добаление вершины к графу
        void addingVertex(MouseEvent e) {
            vertices.add(new Point(e.getX(), e.getY()));
            graph.addVertex();
            renewalAdjList();
            graphInputMode = 0;
        }

        //добавление первой вершины к списку ребер
        void addingSource(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Integer vertexIndex = getVertexIndex(x, y);
            //если вершины нет - ребро невозможно построить
            if (vertexIndex != null) {
                ArrayList<Integer> addingEdge = new ArrayList<>();
                addingEdge.add(vertexIndex);
                edges.add(addingEdge);
                graphInputMode = 3;
            }
            else {
                graphInputMode = 0;
            }
        }

        //добавление второй вершины и расстояния между ними к списку ребер
        //добавление ребра к графу
        void addingTarget(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Integer vertexIndex = getVertexIndex(x, y);
            //если вершины нет - ребро невозможно построить
            if (vertexIndex != null) {
                edges.get(edges.size() - 1).add(vertexIndex);
                String inputWeight = JOptionPane.showInputDialog("Enter the weight of the edge");
                edges.get(edges.size() - 1).add(Integer.parseInt(inputWeight));
                graph.addNeighbor(edges.get(edges.size() - 1).get(0), vertexIndex, Integer.parseInt(inputWeight));
                renewalAdjList ();
            }
            else
                edges.remove(edges.size() - 1);
            graphInputMode = 0;
        }

        //удаление вершины со сдвигом больших номеров вершин
        //удаление вершины из графа
        void removingVertex(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Integer vertexIndex = getVertexIndex(x, y);
            if (vertexIndex != null) {
                for (int i = 0; i < edges.size(); i++) {
                    ArrayList<Integer> edge = edges.get(i);
                    if (Objects.equals(edge.get(0), vertexIndex) ||
                            Objects.equals(edge.get(1), vertexIndex)) {
                        edges.remove(i);
                        i--;
                        continue;
                    }
                    if (edge.get(0) > vertexIndex) {
                        int oldValue = edge.get(0);
                        oldValue--;
                        edge.set(0, oldValue);
                    }
                    if (edge.get(1) > vertexIndex) {
                        int oldValue = edge.get(1);
                        oldValue--;
                        edge.set(1, oldValue);
                    }
                }
                vertices.remove((int)vertexIndex);
                graph.removeVertex(vertexIndex);
                renewalAdjList ();
            }
            else
                edges.remove(edges.size() - 1);
            graphInputMode = 0;
        }

        //сохранение первой вершины удаляемого ребра
        void removingSource(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Integer vertexIndex = getVertexIndex(x, y);
            if (vertexIndex != null) {
                deletingBuffer = vertexIndex;
                graphInputMode = 6;
            }
            else
                graphInputMode = 0;
        }

        //удаление ребра
        //удаление ребра из графа
        void removingTarget(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Integer vertexIndex = getVertexIndex(x, y);
            if (vertexIndex != null) {
                for (int i = 0; i < edges.size(); i++) {
                    ArrayList<Integer> edge = edges.get(i);
                    if ((Objects.equals(edge.get(0), deletingBuffer) && Objects.equals(edge.get(1), vertexIndex)) ||
                            (Objects.equals(edge.get(0), vertexIndex) && Objects.equals(edge.get(1), deletingBuffer))) {
                        edges.remove(i);
                        graph.removeEdge(deletingBuffer, vertexIndex);
                        break;
                    }
                }
                renewalAdjList ();
            }
            graphInputMode = 0;
        }

        //вывод списка смежности
        void renewalAdjList () {
            adjListWindowArea.setText(null);
            adjListWindowArea.append(graph.getAdjacencyListForPrint());
            repaint();
        }

        DrawPanel() {
            vertices = new ArrayList<>();
            edges = new ArrayList<>();
            salesmanPath = new HashMap<>();
            graph = new Graph();

            setBackground(Color.WHITE);
            //обработки событий мыши
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (graphInputMode == 1) {
                        addingVertex(e);
                    }
                    else if (graphInputMode == 2) {
                        addingSource(e);
                    }
                    else if (graphInputMode == 3) {
                        addingTarget(e);
                    }
                    else if (graphInputMode == 4) {
                        removingVertex(e);
                    }
                    else if (graphInputMode == 5) {
                        removingSource(e);
                    }
                    else if (graphInputMode == 6) {
                        removingTarget(e);
                    }
                }
            });
        }

        //вывод сообщений об ошибках
        void errorPrint() {
            solutionWindowArea.setText(null);
            solutionWindowArea.append("These is no solution!\n");
            graphInputMode = 0;
            repaint();
        }

        //проверка графа на то, что он:
        // не нулевой
        // не пустой
        // связный
        // степень каждой вершины не меньше 2
        // затем находим решение по алгоритму, соответствующему количеству вершин
        boolean checkGraph() {
            if (graph == null) {
                errorPrint();
                return false;
            }
            int graphSize = graph.getGraphSize();
            if (graphSize == 0) {
                errorPrint();
                return false;
            }
            if (DepthSearch.depthSearch(graph) != graphSize) {
                errorPrint();
                return false;
            }
            if (graph.getGraphSize() < 20)
                salesmanPath = FindHamiltonianPath.findCycle(graph);
            else
                salesmanPath = AnnealingAlgorithm.findPath(graph);
            if (salesmanPath == null) {
                errorPrint();
                return false;
            }
            return true;
        }

        //вывод найденного маршрута и его длины на текстовую панель
        boolean getSolution () {
            if (!checkGraph()) {
                errorPrint();
                return false;
            }
            solutionWindowArea.setText(null);
            solutionWindowArea.append("0 - ");
            int nextVertex = 0;
            for (int i = 0; i < salesmanPath.size() - 2; i++) {
                solutionWindowArea.append(salesmanPath.get(nextVertex).toString() + " - ");
                nextVertex = salesmanPath.get(nextVertex);
            }
            solutionWindowArea.append("0\n");
            solutionWindowArea.append("Path length = " + salesmanPath.get(graph.getGraphSize()));
            return true;
        }

        //вывод ребер графа
        void drawEdges (Graphics g2) {
            for (int i = 0; i < edges.size(); i++) {
                ArrayList<Integer> edge = edges.get(i);
                if (edge.size() == 3) {
                    //получение координат вершин из списка вершин
                    int sourceX = vertices.get(edge.get(0)).x + 5;
                    int sourceY = vertices.get(edge.get(0)).y + 5;
                    int targetX = vertices.get(edge.get(1)).x + 5;
                    int targetY = vertices.get(edge.get(1)).y + 5;
                    Integer weight = edge.get(2);
                    //если должны вывести решение задачи -
                    //находим ребра, принадлежащие найденному пути
                    //и рисуем их красным цветом
                    if (salesmanPath != null) {
                        int source = edge.get(0);
                        int target = edge.get(1);
                        if (salesmanPath.get(source) == target || salesmanPath.get(target) == source) {
                            g2.setColor(Color.RED);
                        }
                    }
                    g2.drawLine(sourceX, sourceY, targetX, targetY);
                    g2.setColor(Color.RED);
                    g2.setFont(new Font("Times new roman", Font.BOLD, 15));
                    g2.drawString(weight.toString(), (sourceX + targetX) / 2 - 10,
                            (sourceY + targetY) / 2 - 10);
                    g2.setFont(null);
                    g2.setColor(Color.black);
                }
                else {
                    edges.remove(i);
                    i--;
                }
            }
        }

        //вывод вершин
        void drawVertices (Graphics g2) {
            for (Integer i = 0; i < vertices.size(); i++) {
                Point vertex = vertices.get(i);
                g2.fillOval(vertex.x, vertex.y, 10, 10);
                g2.setFont(new Font("Times new roman", Font.BOLD, 20));
                g2.setColor(Color.BLUE);
                g2.drawString(i.toString(), vertex.x - 1, vertex.y);
                g2.setFont(null);
                g2.setColor(Color.black);
            }
        }

        //метод вызывается при каждой перерисовке контейнера
        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.black);
            //если надо вывести решение, но его нет - return
            if (graphInputMode == 7) {
                if (!getSolution())
                    return;
            }
            drawEdges(g2);
            drawVertices(g2);
            salesmanPath = null;
        }
    }

    private javax.swing.JTextArea adjListWindowArea;
    private DrawPanel graphField;
    private javax.swing.JTextArea solutionWindowArea;

}
