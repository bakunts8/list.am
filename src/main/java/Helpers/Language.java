package Helpers;

public enum Language {

    ENGLISH("en"),
    РУССКИЙ("ru"),
    ՀԱՅԵՐԵՆ("am");


    private final String value;

    Language (String value) {
        this.value = value;
    }

    public static String getValue(Language op) {
        return op.value;
    }
}
