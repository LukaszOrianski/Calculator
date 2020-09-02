package historyXml;

import utilityPack.Dictionary;
import utilityPack.Utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HistoryViewer {

    public static void history(String userChoice) {
        Operations historyOperations;
        if ((historyOperations = Utils.loadXmlData(new File(Utils.xmlFileName))) == null) {
            System.out.println(Dictionary.FILE_READ_ERROR.getAppMassage());
            return;
        }
        List<Operation> ops = historyOperations.getOperationList();
        switch (userChoice) {
            case "1":
                showWhole(ops);
                break;
            case "2":
                showFromDate(ops);
                break;
            case "3":
                showToDate(ops);
                break;
            case "4":
                showUserRange(ops);
                break;
            default:
                System.out.println(Dictionary.WRONG_CHOICE.getAppMassage());
        }
    }

    private static void showWhole(List<Operation> opList) {

        if (opList.isEmpty()) {
            System.out.println(Dictionary.HISTORY_RANGE_NOT_FOUND.getAppMassage());
        } else {
            opList.forEach(System.out::println);
            LocalDateTime from = opList.stream()
                    .max(Operation::compareTo).orElse(new Operation()).getDateTimeOfOperation();
            LocalDateTime to = opList.stream()
                    .min(Operation::compareTo).orElse(new Operation()).getDateTimeOfOperation();
            System.out.println(String.format("Przedział czasowy :: %s -- %s%sWpisów :: %s"
                    , Utils.formatDateTime(from), Utils.formatDateTime(to), System.lineSeparator(), opList.size()));
        }
    }


    private static void showFromDate(List<Operation> opList) {
        System.out.println(Dictionary.ENTER_DATE_LOW.getAppMassage());
        LocalDateTime from = dateTimeInput();
        opList = opList.stream()
                .filter((dateRange -> dateRange.getDateTimeOfOperation().isAfter(from)))
                .collect(Collectors.toList());
        int hits = opList.size();
        if (hits > 0) {
            opList.forEach(System.out::println);
            System.out.println(String.format("Przedział czasowy :: %s -- %s%sWpisów :: %s",
                    Utils.formatDateTime(from), Utils.formatDateTime(opList.stream()
                            .min(Operation::compareTo)
                            .get()
                            .getDateTimeOfOperation()), System.lineSeparator(), hits));
        } else {
            System.out.println(Dictionary.HISTORY_RANGE_NOT_FOUND.getAppMassage());
        }

    }

    private static void showToDate(List<Operation> opList) {
        System.out.println(Dictionary.ENTER_DATE_HIGH.getAppMassage());
        LocalDateTime to = dateTimeInput();
        opList = opList.stream()
                .filter((dateRange -> dateRange.getDateTimeOfOperation().isBefore(to)))
                .collect(Collectors.toList());
        int hits = opList.size();
        if (hits > 0) {
            opList.forEach(System.out::println);
            System.out.println(String.format("Przedział czasowy :: %s -- %s%sWpisów :: %s",
                    Utils.formatDateTime(opList.stream()
                            .filter(Objects::nonNull)
                            .max(new OperationComparator())
                            .get()
                            .getDateTimeOfOperation())
                    , Utils.formatDateTime(to), System.lineSeparator(), hits));
        } else {
            System.out.println(Dictionary.HISTORY_RANGE_NOT_FOUND.getAppMassage());
        }
    }

    private static void showUserRange(List<Operation> opList) {
        System.out.println(Dictionary.ENTER_DATE_LOW.getAppMassage());
        LocalDateTime from = dateTimeInput();
        System.out.println(Dictionary.ENTER_DATE_HIGH.getAppMassage());
        LocalDateTime to = dateTimeInput();
        opList = opList.stream()
                .filter((dateRange -> dateRange.getDateTimeOfOperation().isAfter(from)
                        && dateRange.getDateTimeOfOperation().isBefore(to)))
                .collect(Collectors.toList());
        int hits = opList.size();
        if (hits > 0) {
            opList.forEach(System.out::println);
            System.out.println(String.format("Przedział czasowy :: %s -- %s%sWpisów :: %s",
                    Utils.formatDateTime(from), Utils.formatDateTime(to), System.lineSeparator(), hits));
        } else {
            System.out.println(Dictionary.HISTORY_RANGE_NOT_FOUND.getAppMassage());
        }

    }

    private static LocalDateTime dateTimeInput() {
        LocalDateTime output = LocalDateTime.now();
        String input = null;
        Scanner scanner = new Scanner(System.in);
        while (input == null) {
            input = scanner.nextLine();
            try {
                output = LocalDateTime.parse(input, Utils.dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println(Dictionary.DATE_FORMAT_MISMATCH_ERROR.getAppMassage());
                input = null;
            }
        }
        return output;
    }
}