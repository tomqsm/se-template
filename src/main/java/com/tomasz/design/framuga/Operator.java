package com.tomasz.design.framuga;

public class Operator {

    public double devide(double a, double b) {
        if(b == 0){
            throw new AppException("Not allowed.");
        }
        return a / b;
    }
}
