package historyXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "operationsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operations {
    @XmlElement(name = "operation")
    private List<Operation> operationList;

    public Operations(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public Operations() {
        this.operationList = new LinkedList<>();
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }
}