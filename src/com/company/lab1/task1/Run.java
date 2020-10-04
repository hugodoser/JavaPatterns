package com.company.lab1.task1;

public class Run {
    public static void main(String[] args) {
        PropertyLoader firstPropertyLoader = PropertyLoader.getLoader();
        PropertyLoader secondPropertyLoader = PropertyLoader.getLoader();
        if (firstPropertyLoader == secondPropertyLoader) {
            System.out.println("Это один и тот же объект.");
            System.out.println(firstPropertyLoader.getProperties().getProperty("text"));
        } else {
            System.out.println("Это разные объекты.");
        }
    }
}