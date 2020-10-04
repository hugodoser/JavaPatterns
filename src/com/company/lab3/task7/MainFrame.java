package com.company.lab3.task7;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{
    private int frameWidth = 700, frameHeight = 500;
    private List<Figure> figures = new ArrayList<>();

    public MainFrame() {
        setBounds(100, 100, frameWidth, frameHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton squareButton = new JButton("Квадрат");
        squareButton.setVisible(true);
        panel.add(squareButton);

        JButton ballButton = new JButton("Круг");
        ballButton.setVisible(true);
        panel.add(ballButton);

        JButton starButton = new JButton("Звезда");
        starButton.setVisible(true);
        panel.add(starButton);

        JButton closeButton = new JButton("Закрыть");
        closeButton.setVisible(true);
        panel.add(closeButton, BorderLayout.SOUTH);

        ballButton.addActionListener(e -> {
            Figure ball = new Ball(frameWidth,frameHeight);
            figures.add(ball);
            new Thread(new BouncingFigure(ball)).start();
        });

        starButton.addActionListener(e -> {
            int xPnts[] = {642, 652, 672, 652, 660, 640, 615, 628, 609, 632, 642};
            int yPnts[] = {338, 362, 368, 380, 405, 385, 402, 375, 358, 360, 338};
            Figure star = new Star(frameWidth, frameHeight, xPnts, yPnts);
            figures.add(star);
            new Thread(new BouncingFigure(star)).start();
        });

        squareButton.addActionListener(e -> {
            Figure square = new Square(frameWidth,frameHeight);
            figures.add(square);
            new Thread(new BouncingFigure(square)).start();
        });

        closeButton.addActionListener(e -> {
            System.exit(0);
        });


        while(true){
            repaint();
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        //g.fillRect(100, 100, frameWidth, frameHeight);
        for (Figure figure: figures) {
            figure.draw(g);
        }
    }

}
