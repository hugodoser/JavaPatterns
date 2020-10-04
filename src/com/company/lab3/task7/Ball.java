package com.company.lab3.task7;

import java.awt.*;

public class Ball extends Figure {
    public Ball(int frameWidth, int frameHeight) {
        super(frameWidth, frameHeight);
    }

    @Override
    protected void changeColor() {
        red = ran.nextFloat();
        green = ran.nextFloat();
        blue = ran.nextFloat();
    }

    @Override
    protected void move() {
        if(right) x += speed;
        else x -= speed;

        if(up) y -= speed;
        else y += speed;

        if(x < 0) right = true;
        if(x > fw - diameter) right = false;

        if(y < 0) up = false;
        if(y > fh - diameter) up = true;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillOval(x, y, diameter, diameter);
    }
}
