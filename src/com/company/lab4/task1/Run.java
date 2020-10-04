package com.company.lab4.task1;

import com.company.lab4.task1.view.GraphicView;
import com.company.lab4.task1.view.TableView;

public class Run {

    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        startTableView(controller, model);
        startGraphicView(model);
    }

    private static void startTableView(Controller controller, Model model) {
        TableView tableView = new TableView(controller, model);
        tableView.start();
    }

    private static void startGraphicView(Model model) {
        GraphicView graphicView = new GraphicView(model);
        graphicView.start();
    }
}
