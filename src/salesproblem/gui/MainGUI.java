package salesproblem.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import salesproblem.algorithm.*;

import javax.swing.*;

public class MainGUI extends javax.swing.JFrame {

    Graph graph;
    int graphInputMode = 0;

    public MainGUI() {
        initComponents();
        graph = new Graph();
    }

    private void initComponents() {

        graphField = new DrawPanel();
        addVertexButton = new javax.swing.JButton();
        addEdgeButton = new javax.swing.JButton();
        removeVertexButton = new javax.swing.JButton();
        removeEdgeButton = new javax.swing.JButton();
        buildTheWayButton = new javax.swing.JButton();
        caption = new javax.swing.JScrollPane();
        captionArea = new javax.swing.JTextArea();
        solutionWindow = new javax.swing.JScrollPane();
        solutionWindowArea = new javax.swing.JTextArea();
        adjListWindow = new javax.swing.JScrollPane();
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
        addVertexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVertexButtonActionPerformed(evt);
            }
        });

        addEdgeButton.setText("Add edge");
        addEdgeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEdgeButtonActionPerformed(evt);
            }
        });

        removeVertexButton.setText("Remove vertex");
        removeVertexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVertexButtonActionPerformed(evt);
            }
        });

        removeEdgeButton.setText("Remove edge");
        removeEdgeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeEdgeButtonActionPerformed(evt);
            }
        });

        buildTheWayButton.setText("Build the way");
        buildTheWayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildTheWayButtonActionPerformed(evt);
            }
        });
        captionArea.setColumns(20);
        captionArea.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    public class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private ArrayList<Point> vertices;
        private ArrayList<ArrayList<Integer>> edges;
        private Map<Integer, Integer> salesmanPath;
        Integer deletingBuffer = null;

        private Integer getVertexIndex(int x, int y) {

            for (int i = 0; i < vertices.size(); i++) {
                if (((x - vertices.get(i).x) < 10) && ((x - vertices.get(i).x) > 0) &&
                    ((y - vertices.get(i).y) < 10) && ((y - vertices.get(i).y) > 0))
                    return i;
            }
            return null;

        }

        public DrawPanel() {
            vertices = new ArrayList<>();
            edges = new ArrayList<>();
            salesmanPath = new HashMap<>();
            graph = new Graph();

            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (graphInputMode == 1) {
                        vertices.add(new Point(e.getX(), e.getY()));
                        graph.addVertex();
                        adjListWindowArea.setText(null);
                        adjListWindowArea.append(graph.getAdjacencyListForPrint());
                        repaint();
                        graphInputMode = 0;
                    }
                    else if (graphInputMode == 2) {
                        int x = e.getX();
                        int y = e.getY();
                        Integer vertexIndex = getVertexIndex(x, y);
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
                    else if (graphInputMode == 3) {
                        int x = e.getX();
                        int y = e.getY();
                        Integer vertexIndex = getVertexIndex(x, y);
                        if (vertexIndex != null) {
                            edges.get(edges.size() - 1).add(vertexIndex);
                            String inputWeight = JOptionPane.showInputDialog("Enter the weight of the edge");
                            edges.get(edges.size() - 1).add(Integer.parseInt(inputWeight));
                            graph.addNeighbor(edges.get(edges.size() - 1).get(0), vertexIndex, Integer.parseInt(inputWeight));
                            adjListWindowArea.setText(null);
                            adjListWindowArea.append(graph.getAdjacencyListForPrint());
                            repaint();
                        }
                        else
                            edges.remove(edges.size() - 1);
                        graphInputMode = 0;
                    }
                    else if (graphInputMode == 4) {
                        int x = e.getX();
                        int y = e.getY();
                        Integer vertexIndex = getVertexIndex(x, y);
                        if (vertexIndex != null) {
                            for (int i = 0; i < edges.size(); i++) {
                                ArrayList<Integer> edge = edges.get(i);
                                if (edge.get(0) == vertexIndex || edge.get(1) == vertexIndex) {
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
                            adjListWindowArea.setText(null);
                            adjListWindowArea.append(graph.getAdjacencyListForPrint());
                            repaint();
                        }
                        else
                            edges.remove(edges.size() - 1);
                        graphInputMode = 0;
                    }
                    else if (graphInputMode == 5) {
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
                    else if (graphInputMode == 6) {
                        int x = e.getX();
                        int y = e.getY();
                        Integer vertexIndex = getVertexIndex(x, y);
                        if (vertexIndex != null) {
                            for (int i = 0; i < edges.size(); i++) {
                                ArrayList<Integer> edge = edges.get(i);
                                if ((edge.get(0) == deletingBuffer && edge.get(1) == vertexIndex) ||
                                        (edge.get(0) == vertexIndex && edge.get(1) == deletingBuffer)) {
                                    edges.remove(i);
                                    graph.removeEdge(deletingBuffer, vertexIndex);
                                    break;
                                }
                            }
                            adjListWindowArea.setText(null);
                            adjListWindowArea.append(graph.getAdjacencyListForPrint());
                            repaint();
                        }
                        graphInputMode = 0;
                    }
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.black);
            if (graphInputMode == 7) {
                if (graph == null) {
                    solutionWindowArea.setText(null);
                    solutionWindowArea.append("These is no solution: there is no graph!\n");
                    return;
                }
                int graphSize = graph.getGraphSize();
                if (graphSize == 0) {
                    solutionWindowArea.setText(null);
                    solutionWindowArea.append("These is no solution: there is no graph!\n");
                    return;
                }
                if (graphSize > 2) {
                    for (int i = 0; i < graph.getGraphSize(); i++) {

                        for (int j = 0; j < graph.getGraphSize(); j++) {

                            if (i == j || graph.checkIfNeighbors(i, j))
                                continue;
                            if (graph.getVertexDegree(i) + graph.getVertexDegree(j) < graphSize) {

                                solutionWindowArea.setText(null);
                                solutionWindowArea.append("There is no solution for this graph\n");
                                return;

                            }

                        }

                    }
                }
                if (DepthSearch.depthSearch(graph) != graphSize) {
                    solutionWindowArea.setText(null);
                    solutionWindowArea.append("There is no solution: the graph is disconnected\n");
                    return;
                }
                if (graph.getGraphSize() < 20)
                    salesmanPath = FindEulerianPath.findCycle(graph);
                else
                    salesmanPath = AnnealingAlgorithm.findPath(graph);

            }

            for (Integer i = 0; i < vertices.size(); i++) {
                Point vertex = vertices.get(i);
                g2.fillOval(vertex.x, vertex.y, 10, 10);
                g2.setFont(new Font("Times new roman", Font.BOLD, 20));
                g2.setColor(Color.BLUE);
                g2.drawString(i.toString(), vertex.x - 1, vertex.y);
                g2.setFont(null);
                g2.setColor(Color.black);
            }
            for (int i = 0; i < edges.size(); i++) {
                ArrayList<Integer> edge = edges.get(i);
                if (edge.size() == 3) {
                    int sourceX = vertices.get(edge.get(0)).x + 5;
                    int sourceY = vertices.get(edge.get(0)).y + 5;
                    int targetX = vertices.get(edge.get(1)).x + 5;
                    int targetY = vertices.get(edge.get(1)).y + 5;
                    Integer weight = edge.get(2);
                    if (salesmanPath != null) {
                        int source = edge.get(0);
                        int target = edge.get(1);
                        if (salesmanPath.get(source) == target || salesmanPath.get(target) == source) {
                            g2.setColor(Color.BLUE);
                        }
                    }
                    g2.drawLine(sourceX, sourceY, targetX, targetY);
                    g2.setColor(Color.red);
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
            salesmanPath = null;
        }
    }

    private javax.swing.JButton addEdgeButton;
    private javax.swing.JButton addVertexButton;
    private javax.swing.JScrollPane adjListWindow;
    private javax.swing.JTextArea adjListWindowArea;
    private javax.swing.JButton buildTheWayButton;
    private javax.swing.JScrollPane caption;
    private javax.swing.JTextArea captionArea;
    private DrawPanel graphField;
    private javax.swing.JButton removeEdgeButton;
    private javax.swing.JButton removeVertexButton;
    private javax.swing.JScrollPane solutionWindow;
    private javax.swing.JTextArea solutionWindowArea;

}
