package com.study.calculator.model;

import java.io.Serializable;

import java.io.Serializable;

public class ParamsCalculatorModel implements Serializable {
    private String variable1 = "";
    private String variable2 = "";
    private String operation;
    private Boolean isOneVariable = true;

    public ParamsCalculatorModel() {
        this.variable1 = "";
        this.variable2 = "";
        this.operation = "";
        this.isOneVariable = true;
    }

    public ParamsCalculatorModel(String variable1, String variable2, String operation, Boolean isOneVariable) {
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.operation = operation;
        this.isOneVariable = isOneVariable;
    }

    public String getVariable1() {
        return variable1;
    }

    public void setVariable1(String variable1) {
        this.variable1 = variable1;
    }

    public String getVariable2() {
        return variable2;
    }

    public void setVariable2(String variable2) {
        this.variable2 = variable2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Boolean getOneVariable() {
        return isOneVariable;
    }

    public void setOneVariable(Boolean oneVariable) {
        isOneVariable = oneVariable;
    }
   public void clear(){
        variable1 = "";
        variable2 = "";
        operation = "";
        isOneVariable = true;
    }
    public void clear1(){
        variable2 = "";
        operation = "";
        isOneVariable = true;
    }
}

