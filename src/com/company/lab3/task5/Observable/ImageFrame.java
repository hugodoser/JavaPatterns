package com.company.lab3.task5.Observable;

import com.company.lab3.task5.ImageEventType;
import com.company.lab3.task5.ImagePanel;
import com.company.lab3.task5.Observer.ClickableImagePanel;
import com.company.lab3.task5.Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ImageFrame extends JFrame implements Observable {
    public static String MAIN_IMAGE = "resources/main.jpg";
    public static String SMILE_IMAGE = "resources/smile.jpg";
    public static String BIG_SMILE_IMAGE = "resources/big_smile.jpg";
    public static String NOISE_IMAGE = "resources/noise.jpg";
    public static String RED_NOISE_IMAGE = "resources/red_noise.jpg";
    public static String LEFT_EYE_IMAGE = "resources/left_eye_open.jpg";
    public static String LEFT_EYE_CLOSED_IMAGE = "resources/left_eye_close.jpg";
    public static String RIGHT_EYE_IMAGE = "resources/right_eye_open.jpg";
    public static String RIGHT_EYE_CLOSED_IMAGE = "resources/right_eye_close.jpg";

    private List<Observer> observers;

    public ImageFrame() {
        super("The Shining!");
        observers = new ArrayList<>();
        setSize(948, 711);
        initialize();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initialize() {
        addClickableImage(SMILE_IMAGE, BIG_SMILE_IMAGE, ImageEventType.SMILE, 254, 529);
        addClickableImage(NOISE_IMAGE, RED_NOISE_IMAGE, ImageEventType.NOISE, 363, 336);
        addClickableImage(RIGHT_EYE_IMAGE, RIGHT_EYE_CLOSED_IMAGE, ImageEventType.RIGHT_EYE, 544, 224);
        addClickableImage(LEFT_EYE_IMAGE, LEFT_EYE_CLOSED_IMAGE, ImageEventType.LEFT_EYE, 199, 203);
        addImage(MAIN_IMAGE, 0, 0);
    }

    private void addImage(String imagePath, int x, int y) {
        Image image = new ImageIcon(imagePath).getImage();
        ImagePanel panel = new ImagePanel(image);
        panel.setLocation(x, y);
        add(panel);
    }

    private void addClickableImage(String offClickImagePath, String onClickImagePath, ImageEventType eventType, int x, int y) {
        Image offClickImage = new ImageIcon(offClickImagePath).getImage();
        Image onClickImage = new ImageIcon(onClickImagePath).getImage();
        ClickableImagePanel panel = new ClickableImagePanel(offClickImage, onClickImage, eventType);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                notifyObservers(eventType);
            }
        });
        panel.setLocation(x, y);
        panel.subscribeOn(this);
        add(panel);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object object) {
        observers.forEach(observer -> observer.update(object));
    }
}
