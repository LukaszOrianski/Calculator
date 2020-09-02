package menu;

import calculator.Calculator;
import historyXml.HistoryViewer;
import historyXml.Operation;
import historyXml.Operations;
import utilityPack.Dictionary;
import utilityPack.Utils;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    List<Operation> operations;

    public Menu(List<Operation> operations) {
        this.operations = operations;
    }


    private static void printOperationOptionsMenu() {
        System.out.println(
                "1 ... Dwa argumenty (np. 2+2)" + System.lineSeparator() +
                        "2 ... Zadana liczba argumentów (np. 2+2+2+2 dla 4 argumentów)" + System.lineSeparator() +
                        "3 ... Niewiadoma liczba argumentów (użytkownik wpisuje argumenty do czasu wprowadzenia ustalonego" +
                        " znaku (x))" + System.lineSeparator() +
                        "0 ... Poprzednie Menu");
    }


    private static void printCalculatorMenu() {
        System.out.println(
                "+ ... Dodawanie" + System.lineSeparator() +
                        "- ... Odejmowanie" + System.lineSeparator() +
                        "* ... Mnożenie" + System.lineSeparator() +
                        "/ ... Dzielenie" + System.lineSeparator() +
                        "0 ... Poprzednie Menu" + System.lineSeparator() +
                        "? ... Twój wybór ?");
    }

    private void printAplicationMenu() {
        Operations historySize;
        if ((historySize = Utils.loadXmlData(new File(Utils.xmlFileName))) == null) {
            JAXB.marshal(new Operations(), new File(Utils.xmlFileName));
        }
        System.out.println(
                "1 ... Kalkulator");
        if (historySize != null && !historySize.getOperationList().isEmpty()) {
            System.out.printf("2 ... Przeglądanie historii (%s rekord/y/ów)%n", historySize.getOperationList().size());
        }
        System.out.println("0 ... wyjście z aplikacji" + System.lineSeparator() +
                "? ... Twój wybór ?");

    }

    private static void printHistoryMenu() {
        System.out.println(
                "1 ... Wyświetlenie całej historii" + System.lineSeparator() +
                        "2 ... Wyświetlenie historii od zadanej daty do chwili obecnej" + System.lineSeparator() +
                        "3 ... Wyświetlenie historii od początku do zadanej daty" + System.lineSeparator() +
                        "4 ... Wyświetlenie historii między zadanymi datami" + System.lineSeparator() +
                        "0 ... Poprzednie Menu" + System.lineSeparator() +
                        "? ... Twój wybór ?");
    }

    private void historyMenu() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("0")) {
            Menu.printHistoryMenu();
            input = scanner.next();
            switch (input) {
                case "1":
                case "2":
                case "3":
                case "4":
                    HistoryViewer.history(input);
                    break;
                case "0":
                    System.out.println(Dictionary.BACK_TO_PREV_MENU.getAppMassage());
                    break;
                default:
                    System.out.println(Dictionary.WRONG_CHOICE.getAppMassage());
            }
        }
    }

    private void operationOptionMenu(String opType) {
        Scanner scanner = new Scanner(System.in);
        String string = "";
        while (!string.equals("0")) {
            printOperationOptionsMenu();
            string = scanner.next();
            switch (string) {
                case "1":
                    switch (opType) {
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            operetionConditionalAdd(Calculator.calculateOperation(inputTwoArgs(opType), opType));
                            break;
                    }
                    break;
                case "2":
                    switch (opType) {
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            operetionConditionalAdd(Calculator.calculateOperation(inputChoosenNumberOfArgs(inputNoOfArgs(), opType), opType));
                            break;
                    }
                    break;
                case "3":
                    switch (opType) {
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            operetionConditionalAdd(Calculator.calculateOperation(inputUndisclosedNoOfArgs(opType), opType));
                            break;
                    }
                    break;
                case "0":
                    System.out.println(Dictionary.BACK_TO_PREV_MENU.getAppMassage());
                    break;
                default:
                    System.out.println(Dictionary.WRONG_CHOICE.getAppMassage());
            }
        }
    }

    private void calculatorMenu() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("0")) {
            Menu.printCalculatorMenu();
            input = scanner.next();
            switch (input) {
                case "+":
                case "*":
                case "-":
                case "/":
                    operationOptionMenu(input);
                    break;
                case "0":
                    System.out.println(Dictionary.BACK_TO_PREV_MENU.getAppMassage());
                    break;
                default:
                    System.out.println(Dictionary.WRONG_CHOICE.getAppMassage());
            }
        }
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("0")) {
            printAplicationMenu();
            input = scanner.next();
            switch (input) {
                case "1":
                    calculatorMenu();
                    break;
                case "2":
                    historyMenu();
                    break;
                case "0":
                    System.out.println(Dictionary.APP_EXIT.getAppMassage());
                    break;
                default:
                    System.out.println(Dictionary.WRONG_CHOICE.getAppMassage());
            }
        }
    }

    //---------------------------------------------------------------

    private List<String> inputChoosenNumberOfArgs(int argumentsCount, String operationType) {
        List<String> stringList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        int i = 1;
        do {
            System.out.printf("%s %s z %s :%n", Dictionary.ENTER_ARGUMENT.getAppMassage(), i, argumentsCount);
            input = scanner.nextLine();
            try {
                double doubleInput = Double.parseDouble(input);
                if (operationType.equals("/") && doubleInput == 0 && !stringList.isEmpty()) {
                    System.out.println(Dictionary.DIVIDE_BY_ZERO.getAppMassage());
                } else {
                    stringList.add(input);
                    i++;
                }
            } catch (NumberFormatException e) {
                System.out.println(Dictionary.NUMBER_FORMAT_MISMATCH_ERROR.getAppMassage());
            }
        } while (stringList.size() < argumentsCount);
        return stringList;
    }

    private List<String> inputTwoArgs(String operationType) {
        return inputChoosenNumberOfArgs(2, operationType);
    }

    private int inputNoOfArgs() {
        Scanner scanner = new Scanner(System.in);
        String string;
        int noOfArgs = 0;
        System.out.println(Dictionary.ENTER_NO_OF_ARGS.getAppMassage());
        do {
            try {
                string = scanner.nextLine();
                noOfArgs = Integer.parseInt(string);
                if (noOfArgs < 2) {
                    System.out.println(Dictionary.TOO_FEW_ARGUMENTS.getAppMassage());
                    string = null;
                }
            } catch (NumberFormatException e) {
                System.out.println(Dictionary.NUMBER_FORMAT_MISMATCH_ERROR.getAppMassage());
                string = null;
            }
        } while (string == null);
        return noOfArgs;
    }

    private List<String> inputUndisclosedNoOfArgs(String operationType) {
        Scanner scanner = new Scanner(System.in);
        String string = "";
        List<String> stringList = new ArrayList<>();
        while (!string.equals("x")) {
            System.out.printf("%s (\"x\" kończy wpisywanie)%n", Dictionary.ENTER_ARGUMENT.getAppMassage());
            string = scanner.nextLine();
            try {
                double input = Double.parseDouble(string);
                if (operationType.equals("/") && input == 0 && !stringList.isEmpty()) {
                    System.out.println(Dictionary.DIVIDE_BY_ZERO.getAppMassage());
                } else {
                    stringList.add(string);
                }
            } catch (NumberFormatException e) {
                if (!string.equals("x"))
                    System.out.println(Dictionary.NUMBER_FORMAT_MISMATCH_ERROR.getAppMassage());
            }
        }
        if (stringList.size() < 2) {
            System.out.println(Dictionary.TOO_FEW_ARGUMENTS.getAppMassage());
            return null;
        }
        return stringList;
    }

    //---------------------------------------------------------------

    private void operetionConditionalAdd(Operation operation) {
        if (operation != null) {
            operations.add(operation);
        } else {
            System.out.println(Dictionary.TOO_FEW_ARGUMENTS.getAppMassage());
        }
    }
}