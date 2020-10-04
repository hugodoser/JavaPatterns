package com.company.lab3.task5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ImagePanel extends JPanel {
    protected Image image;

    public ImagePanel(Image image) {
        this.image = image;
        Dimension size = new Dimension(500, 500);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
