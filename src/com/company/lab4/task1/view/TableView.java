package com.company.lab4.task1.view;

import com.company.lab4.task1.Controller;
import com.company.lab4.task1.Model;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class TableView implements Observer {
    public final static int SIZE = 500;

    private Controller controller;
    private Model model;

    private JFrame frame;
    private JTable pointTable;
    private boolean isManualMode = true;

    public TableView(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
        model.addObserver(this);
    }

    public void start() {

        frame = new JFrame("Точки функции");

        initPointTable();

        JScrollPane scrollPaneGenres = new JScrollPane(pointTable);
        frame.setSize(SIZE, SIZE);

        JPanel viewPanel = new JPanel(new GridLayout(1, 1));
        viewPanel.add(scrollPaneGenres);
        frame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initPointTable() {
        final int ROW = 10;
        final int COLUMN = 2;
        pointTable = new JTable(new Object[ROW][COLUMN],
                new String[]{"X", "Y"});
        pointTable.getModel().addTableModelListener(e -> tableHandler(e, pointTable));
        pointTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    remove(pointTable);
            }
        });
        updateTable();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTable();
    }

    private void updateTable() {
        boolean prevState = isManualMode;
        isManualMode = false;
        displayOnTable(model.getPoints());
        isManualMode = prevState;
    }

    private void displayOnTable(Map<Double, Double> points) {
        Double[] abscesses = new Double[points.size()];
        abscesses = points.keySet().toArray(abscesses);
        Arrays.sort(abscesses);

        clear(pointTable);
        for (int i = 0; i < points.size(); i++) {
            int column = 0;
            double x = abscesses[i];
            double y = points.get(x);
            pointTable.setValueAt(String.valueOf(x), i, column);
            pointTable.setValueAt(String.valueOf(y), i, ++column);
        }
    }

    private void clear(JTable table) {
        for (int i = 0, rows = table.getRowCount(); i < rows; i++) {
            for (int j = 0, columns = table.getColumnCount(); j < columns; j++) {
                table.setValueAt(null, i, j);
            }
        }
    }

    private void tableHandler(TableModelEvent e, JTable table) {
        if (!isManualMode)
            return;

        final int columnX = 0;
        final int columnY = 1;
        final int row = e.getFirstRow();
        final int column = e.getColumn();
        try {
            if (column == columnX && table.getValueAt(row, columnY) != null) {
                controller.editPoint(row, Double.parseDouble((String) table.getValueAt(row, columnX)));
            } else if (column == columnX && table.getValueAt(row, columnY) == null) {
                controller.addPoint(Double.parseDouble((String) table.getValueAt(row, columnX)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            updateTable();
        }
    }

    private void remove(JTable table) {
        final int columnX = 0;
        int[] selectedRows = table.getSelectedRows();
        List<Double> removedX = new ArrayList<>();

        for (int selectedRow : selectedRows) {
            if (table.getValueAt(selectedRow, columnX) == null)
                continue;
            try {
                removedX.add(Double.parseDouble((String) table.getValueAt(selectedRow, columnX)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        controller.removePoint(removedX);
        table.clearSelection();
    }
}
