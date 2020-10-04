package com.company.lab4.task1.view;

import com.company.lab4.task1.Model;

import javax.swing.*;
import java.util.*;

public class GraphicView implements Observer {
    private Model model;

    private JFrame frame;
    private FunctionGraphPanel panel;

    public final static int SIZE = 500;

    public GraphicView(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public void start() {
        frame = new JFrame("График функции");
        initGraph();
        frame.setSize(SIZE, SIZE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.repaintGraph(model.getPoints());
    }

    @Override
    public void update(Observable o, Object arg) {
        panel.repaintGraph(model.getPoints());
    }

    private void initGraph() {
        panel = new FunctionGraphPanel(model.getPoints());
        frame.getContentPane().add(panel);
        frame.pack();
    }
}