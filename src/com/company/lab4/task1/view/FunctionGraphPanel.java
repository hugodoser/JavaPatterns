package com.company.lab4.task1.view;

import com.company.lab4.task1.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Map;

public class FunctionGraphPanel extends JPanel {

    private Map<Double, Double> points;

    public FunctionGraphPanel(Map<Double, Double> points) {
        this.points = points;
    }

    @Override
    public void paint(Graphics graph) {
        super.paint(graph);
        repaintGraph((Graphics2D) graph);
    }

    public void repaintGraph(Map<Double, Double> points) {
        this.points = points;
        repaintGraph((Graphics2D) getGraphics());
    }

    private void repaintGraph(Graphics2D graph) {
        graph.clearRect(0, 0, getWidth(), getHeight());
        if (points == null || points.size() < 2)
            return;

        Double[] abscesses = new Double[points.size()];
        abscesses = points.keySet().toArray(abscesses);
        Arrays.sort(abscesses);
        double minX = abscesses[0];
        double maxX = abscesses[abscesses.length - 1];

        Double[] ordinates = new Double[points.size()];
        ordinates = points.values().toArray(ordinates);
        double minY = ArrayUtils.getMinValue(ordinates);
        double maxY = ArrayUtils.getMaxValue(ordinates);

        for (int i = 1; i < abscesses.length; i++) {
            double prevX = abscesses[i - 1];
            double nextX = abscesses[i];
            double prevY = points.get(prevX);
            double nextY = points.get(nextX);

            prevX = transformAbscess(prevX, minX, maxX);
            nextX = transformAbscess(nextX, minX, maxX);
            prevY = transformOrdinate(prevY, minY, maxY);
            nextY = transformOrdinate(nextY, minY, maxY);

            graph.draw(new Line2D.Double(prevX, prevY, nextX, nextY));
        }
    }

    private Double transformAbscess(double value, double minValue, double maxValue) {
        double tensionCoefficient = (double) getWidth() / (maxValue - minValue);
        return (value - minValue) * tensionCoefficient;
    }

    private Double transformOrdinate(double value, double minValue, double maxValue) {
        double tensionCoefficient = (double) getHeight() / (maxValue - minValue);
        return getHeight() - (value - minValue) * tensionCoefficient;
    }
}
