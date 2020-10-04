package com.company.lab3.task7;

import java.awt.*;

public class BouncingFigure implements Runnable {
    private Figure figure;

    public BouncingFigure(Figure figure) {
        this.figure = figure;
    }

    @Override
    public void run() {
        while(true)
        {
            figure.buildAndMove();

            try{
                Thread.sleep(50);//here we pause the movement for
            }
            catch(InterruptedException e){
                System.out.println("Error in executing thread: "+ e);
            }
        }
    }
}
