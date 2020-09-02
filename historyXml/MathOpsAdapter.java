package historyXml;

import calculator.MathOps;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MathOpsAdapter extends XmlAdapter<String, MathOps> {
    @Override
    public MathOps unmarshal(String v) throws Exception {
        for (MathOps element : MathOps.values()) {
            if (element.getOperationDescription().equals(v)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public String marshal(MathOps v) throws Exception {
        return v.getOperationDescription();
    }
}