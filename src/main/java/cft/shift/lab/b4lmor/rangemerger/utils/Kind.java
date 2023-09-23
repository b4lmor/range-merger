package cft.shift.lab.b4lmor.rangemerger.utils;

public enum Kind {
    LETTERS,
    DIGITS;
    public static Kind getKindByString(String kind) {
        return switch (kind) {
            case "letters" -> LETTERS;
            case "digits" -> DIGITS;
            default -> DIGITS; // TODO : throw an exception
        };
    }
}
