package com.example.inved.mynews.controller;


import java.util.Random;

public class Search {


    public int addition(int pA, int pB){
        return pA + 2* pB;
    }

    public String getOperand(){
        switch(new Random().nextInt(4)){
            case 0:
                return "+";
            case 1:
                return "-";
            default:
                return "*";
        }
    }

    public int doOperation(int a, int b){
        String operand = getOperand();
        switch(operand){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b ;

        }
        return 0;
    }


}
