package utilityPack;

import historyXml.Operations;

import javax.xml.bind.JAXB;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String logFileName = "CalculatorBufferLog.txt";
    public static final String xmlFileName = "myXML.xml";

    private Utils() {
    }

    public static String getExcersiseOutput(List<String> list, double result, String delimeter) {
        return String.format("%s=%s", String.join(delimeter, list), result);
    }

    public static Operations loadXmlData(File dataFile) {
        if (!dataFile.exists()) {
            return null;
        }
        return JAXB.unmarshal(dataFile, Operations.class);
    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

}
