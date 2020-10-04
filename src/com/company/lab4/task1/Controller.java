package com.company.lab4.task1;

import com.company.lab4.task1.exception.DuplicateElementException;
import com.company.lab4.task1.exception.NoSuchElementException;

import java.util.List;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void editPoint(int index, double newX) {
        if (!model.existsPointIndex(index)) {
            throw new NoSuchElementException("Not such element: " + index);
        } else if (model.existsPoint(newX)) {
            throw new IllegalArgumentException("Such element exists yet: " + index);
        } else {
            model.editPoint(index, newX);
        }
    }

    public void addPoint(double x) {
        if (model.existsPoint(x)) {
            throw new DuplicateElementException("Such element exists yet: " + x);
        } else {
            model.addPoint(x);
        }
    }

    public void removePoint(List<Double> listX) {
            model.removePoints(listX);
    }
}
