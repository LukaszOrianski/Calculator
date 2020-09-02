package historyXml;

import java.util.Comparator;

public class OperationComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        if (o1== null && o2 == null){
            return 0;
        }
        if (o1==null){
            return -1;
        }if(o2==null){
            return 1;
        }
        if (o1.getDateTimeOfOperation().isBefore(o2.getDateTimeOfOperation())){
            return 1;
        }
        if (o1.getDateTimeOfOperation().isAfter(o2.getDateTimeOfOperation())){
            return -1;
        }
        return 0;
    }
}
