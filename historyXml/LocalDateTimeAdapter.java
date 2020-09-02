package historyXml;

import utilityPack.Utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String v) {
        return LocalDateTime.parse(v, Utils.dateTimeFormatter);
    }

    @Override
    public String marshal(LocalDateTime v) {
        return v.format(Utils.dateTimeFormatter);
    }

}