package calculator;

public enum MathOps {
    ADD("dodawanie", "+"),
    SUBTRACT("odejmowanie", "-"),
    MULTIPLY("mnozenie", "*"),
    DIVIDE("dzielenie", ":");
    private final String operationDescription;
    private final String delimeter;

    MathOps(String operationDescription, String delimeter) {
        this.operationDescription = operationDescription;
        this.delimeter = delimeter;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public String getDelimeter() {
        return delimeter;
    }
}
