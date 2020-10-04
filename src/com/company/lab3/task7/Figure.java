package com.company.lab3.task7;

import java.awt.*;
import java.util.Random;

public abstract class Figure {
    protected int fw, fh;
    protected Random ran = new Random();
    protected int x, y, diameter = 25;
    protected int speed = 0;
    protected float red, green, blue;
    protected boolean up = false, right = false;

    public Figure(int frameWidth, int frameHeight) {
        fw = frameWidth;
        fh = frameHeight;
        x = ran.nextBoolean() ? fw - diameter + ran.nextInt(10) : fw - diameter - ran.nextInt(10);
        y = ran.nextBoolean() ? fh - diameter + ran.nextInt(10) : fh - diameter - ran.nextInt(10);
        speed = ran.nextInt(10);
        up = ran.nextBoolean();
        right = ran.nextBoolean();
    }

    public void buildAndMove(){
        changeColor();
        move();
    }

    abstract protected void changeColor();
    abstract protected void move();

    public void draw(Graphics g) {
        g.setColor(new Color(red, green, blue));
    }
}
