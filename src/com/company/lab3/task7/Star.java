package com.company.lab3.task7;

import java.awt.*;
import java.awt.geom.Path2D;

public class Star extends Figure {
    private int[] xPnts;
    private int[] yPnts;

    public Star(int frameWidth, int frameHeight, int[] xPnts, int[] yPnts) {
        super(frameWidth, frameHeight);
        this.xPnts = xPnts;
        this.yPnts = yPnts;
    }

    @Override
    protected void changeColor() {
        red = ran.nextFloat();
        green = ran.nextFloat();
        blue = ran.nextFloat();
    }

    @Override
    protected void move() {
        if(right)
            for (int i = 0; i< xPnts.length; i++)
                xPnts[i] -= speed;
        else
            for (int i = 0; i< xPnts.length; i++)
                xPnts[i] += speed;

        if(up)
            for (int i = 0; i< yPnts.length; i++)
                yPnts[i] += speed;
        else
            for (int i = 0; i< yPnts.length; i++)
                yPnts[i] -= speed;

        for (int x: xPnts)
            if(x < 0){
                right = false;
                break;
            }

        for (int x: xPnts)
            if(x > fw - diameter){
                right = true;
                break;
            }

        for (int y: yPnts)
            if(y < 0){
                up = true;
                break;
            }

        for (int y: yPnts)
            if(y > fh - diameter){
                up = false;
                break;
            }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawPolygon(xPnts, yPnts, xPnts.length);
    }


}
