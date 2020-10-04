package com.company.lab4.task1;

import java.util.*;

public class Model extends Observable {

    private List<Double> abscesses;

    public Model() {
        abscesses = new ArrayList<>();
    }

    public Map<Double, Double> getPoints() {
        Map<Double, Double> points = new HashMap<>();
        abscesses.forEach(x -> points.put(x, function(x)));
        return points;
    }

    public void editPoint(int index, double newX) {
        Collections.sort(abscesses);
        abscesses.set(index, newX);
        setChanged();
        notifyObservers();
    }

    public void addPoint(double x) {
        abscesses.add(x);
        setChanged();
        notifyObservers();
    }

    public void removePoints(List<Double> listX) {
        abscesses.removeAll(listX);
        setChanged();
        notifyObservers();
    }

    public boolean existsPoint(double x) {
        return abscesses.contains(x);
    }

    public boolean existsPointIndex(int index) {
        return abscesses.size() > index;
    }

    private double function(double x) {
        return 3 * Math.pow(x, 2) + 4;
    }
}
