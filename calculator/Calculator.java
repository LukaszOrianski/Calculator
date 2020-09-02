package calculator;

import historyXml.Operation;
import utilityPack.Utils;

import java.time.LocalDateTime;
import java.util.List;

public class Calculator {

    public static Operation calculateOperation(List<String> arguments, String operationType) {
        Double result = 0.0;
        switch (operationType) {
            case "+":
                result=adding(arguments);
                break;
            case "-":
                result=subtraction(arguments);
                break;
            case "*":
                result=multiply(arguments);
                break;
            case "/":
                result=divide(arguments);
                break;
        }
        return obtainOperationRecord(arguments,result,operationType);
    }

    //---------------------------------------------------------------

    private static Operation obtainOperationRecord(List<String> list, Double result, String operationType) {
        if(operationType.equals("/")){operationType=":";}
        System.out.println(Utils.getExcersiseOutput(list, result, operationType));
        return new Operation(LocalDateTime.now(), list, string2MO(operationType), result);
    }

    private static MathOps string2MO(String operationType){
        for (MathOps element : MathOps.values()) {
            if (element.getDelimeter().equals(operationType)) {
                return element;
            }
        }
        if(operationType.equals("/")){
            return MathOps.DIVIDE;
        }
        return null;
    }

    //---------------------------------------------------------------

    private static Double subtraction(List<String> list) {
        return list.stream()
                .map(Double::parseDouble)
                .mapToDouble(Double::doubleValue)
                .reduce(((Double.parseDouble(list.get(0))) * 2), (partialResult, nextElement) -> partialResult - nextElement);
    }

    private static Double divide(List<String> list) {
        if (Double.parseDouble(list.get(0)) == 0) {
            return 0.0;
        }
        return list.stream()
                .map(Double::parseDouble)
                .reduce(Math.pow(Double.parseDouble(list.get(0)), 2), (partialResult, nextElement) -> partialResult / nextElement);
    }

    private static Double adding(List<String> list) {
        return list.stream()
                .map(Double::parseDouble)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private static Double multiply(List<String> list) {
        return list.stream()
                .map(Double::parseDouble)
                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (partialResult, nextElement) -> partialResult * nextElement);
    }
}