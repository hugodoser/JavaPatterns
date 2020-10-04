package com.company.lab3.task7;

import java.awt.*;

public class Square extends Figure {
    public Square(int frameWidth, int frameHeight) {
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
        if(right) x -= speed;
        else x += speed;

        if(up) y += speed;
        else y -= speed;

        if(x < 0) right = false;
        if(x > fw - diameter) right = true;

        if(y < 0) up = true;
        if(y > fh - diameter) up = false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(x, y, diameter, diameter);
    }
}
