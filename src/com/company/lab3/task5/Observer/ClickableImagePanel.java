package com.company.lab3.task5.Observer;

import com.company.lab3.task5.ImageEventType;
import com.company.lab3.task5.ImagePanel;
import com.company.lab3.task5.Observable.Observable;
import com.company.lab3.task5.Switchable;

import java.awt.*;

public class ClickableImagePanel extends ImagePanel implements Switchable, Observer {
    private Image firstImage;
    private Image secondImage;
    private ImageEventType eventType;
    private boolean isFirstSelected;

    public ClickableImagePanel(Image firstImage, Image secondImage, ImageEventType eventType) {
        super(firstImage);
        setSize(firstImage.getWidth(null) + 1, firstImage.getHeight(null) + 1);
        this.isFirstSelected = true;
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.eventType = eventType;
    }

    public void onSwitch() {
        image = (isFirstSelected) ? secondImage : firstImage;
        getGraphics().drawImage(image, 0, 0, null);
        isFirstSelected = !isFirstSelected;
    }

    public void subscribeOn(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof ImageEventType && obj.equals(eventType)) {
            onSwitch();
        }
    }
}
