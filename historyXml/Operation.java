package historyXml;

import calculator.MathOps;
import utilityPack.Utils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Operation implements Comparable<Operation> {
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlAttribute(name = "date")
    private LocalDateTime dateTimeOfOperation;
    @XmlElement(name = "argument")
    private List<String> argument;
    @XmlJavaTypeAdapter(MathOpsAdapter.class)
    @XmlElement(name = "operationType")
    private MathOps operationType;
    @XmlElement(name = "result")
    private double result;

    public Operation(LocalDateTime dateTimeOfOperation, List<String> argument, MathOps operationType, double result) {
        this.dateTimeOfOperation = dateTimeOfOperation;
        this.argument = argument;
        this.operationType = operationType;
        this.result = result;
    }

    public Operation() {
        this.argument = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Data i czas : %s | Typ działania : %s | Działanie : %s",
                Utils.formatDateTime(dateTimeOfOperation),
                operationType.getOperationDescription(),
                Utils.getExcersiseOutput(argument, result, operationType.getDelimeter()));
    }

    public LocalDateTime getDateTimeOfOperation() {
        return dateTimeOfOperation;
    }

    public void setDateTimeOfOperation(LocalDateTime dateTimeOfOperation) {
        this.dateTimeOfOperation = dateTimeOfOperation;
    }

    public List<String> getArgument() {
        return argument;
    }

    public void setArgument(List<String> argument) {
        this.argument = argument;
    }

    public MathOps getOperationType() {
        return operationType;
    }

    public void setOperationType(MathOps operationType) {
        this.operationType = operationType;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public int compareTo(Operation o) {
        if (o==null){
            return 1;
        }if (dateTimeOfOperation==null){
            return -1;
        }
        if (o.dateTimeOfOperation ==null){
            return 1;
        }
        if (dateTimeOfOperation.isAfter(o.dateTimeOfOperation)) {
            return -1;
        }
        if (dateTimeOfOperation.isBefore(o.dateTimeOfOperation)) {
            return 1;
        } else {
            return 0;
        }
    }
}