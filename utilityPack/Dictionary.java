package utilityPack;

public enum Dictionary {
    NUMBER_FORMAT_MISMATCH_ERROR("wpisano błędne dane, prosze jeszcze raz."),
    DATE_FORMAT_MISMATCH_ERROR("błędnie podana data, proszę jeszcze raz."),
    WRONG_CHOICE("Godny, lecz błędny wybór..."),
    BACK_TO_PREV_MENU("Wracam do poprzedniego menu..."),
    APP_EXIT("Do policzenia!"),
    FILE_READ_ERROR("Błąd odczytu pliku/plik uszkodzony."),
    ENTER_DATE_LOW("Proszę podać dolny zakres wyszukiwania (format \"dd-mm-yyyy gg:mm:ss\") : "),
    ENTER_DATE_HIGH("Proszę podać górny zakres wyszukiwania (format \"dd-mm-yyyy gg:mm:ss\") : "),
    ENTER_NO_OF_ARGS("Proszę podać ilość argumentów działania."),
    ENTER_ARGUMENT("Proszę wprowadzić argument."),
    TOO_FEW_ARGUMENTS("Zbyt mała liczba argumentów, proszę jeszcze raz."),
    DIVIDE_BY_ZERO("Dzielenie przez \"0\" niemożliwe, proszę wprowadzić ponownie."),
    HISTORY_RANGE_NOT_FOUND("Nie odnaleziono rekordów w podanym zakresie czasu.");
    private final String appMassage;

    Dictionary(String appMassage) {
        this.appMassage = appMassage;
    }

    public String getAppMassage() {
        return appMassage;
    }
}
