package buffer;

import historyXml.Operation;
import historyXml.Operations;
import utilityPack.Dictionary;
import utilityPack.Utils;

import javax.xml.bind.JAXB;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryBuffer extends Thread {
    private final List<Operation> calcOperationBuffer;
    private Operations operations;

    public HistoryBuffer() {
        this.calcOperationBuffer = new ArrayList<>();
        operations = Utils.loadXmlData(new File(Utils.xmlFileName));
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (calcOperationBuffer.size() > 0) {
                updateXmlDataFile(new File(Utils.xmlFileName), calcOperationBuffer);
                printBufferLogToFile(getOperationInfoString(true));
                synchronized (this) {
                    calcOperationBuffer.clear();
                }
            } else {
                printBufferLogToFile(getOperationInfoString(false));
            }
        }
    }

    private String getOperationInfoString(boolean b) {
        String currentLocalDateTime = LocalDateTime.now().toString();
        return b ? String.format("%s - Zapisano operacji : %s", currentLocalDateTime, calcOperationBuffer.size())
                : String.format("%s - Nie znaleziono operacji do zapisu", currentLocalDateTime);
    }

    private void printBufferLogToFile(String bufferOperationInfo) {
        List<String> list = new ArrayList<>();
        if (Files.exists(Paths.get(Utils.logFileName))) {
            list = readLogFileToList();
        }
        list.add(0, bufferOperationInfo);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Utils.logFileName))) {
            for (String s : list) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readLogFileToList() {
        List<String> strings = new ArrayList<>();
        try {
            strings = Files.readAllLines(Paths.get(Utils.logFileName));
        } catch (IOException e) {
            System.out.println(Dictionary.FILE_READ_ERROR.getAppMassage());
            e.printStackTrace();
        }
        return strings;
    }

    private void updateXmlDataFile(File dataFile, List<Operation> newOperationList) {
        if ((operations = Utils.loadXmlData(dataFile)) != null) {
            newOperationList.forEach(operations.getOperationList()::add);
            operations.setOperationList(sortDateTimeFromNewestToOldest(operations.getOperationList()));
        } else {
            operations = new Operations();
        }
        JAXB.marshal(operations, dataFile);
    }

    private static List<Operation> sortDateTimeFromNewestToOldest(List<Operation> list) {
        list.sort((o1, o2) -> {
            if (o1.getDateTimeOfOperation().isBefore(o2.getDateTimeOfOperation())) {
                return 1;
            } else {
                return -1;
            }
        });
        return list;
    }

    public List<Operation> getCalcOperationBuffer() {
        return calcOperationBuffer;
    }

}