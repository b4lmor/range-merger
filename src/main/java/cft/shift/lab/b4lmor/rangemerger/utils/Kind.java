package cft.shift.lab.b4lmor.rangemerger.utils;

import cft.shift.lab.b4lmor.rangemerger.exception.WrongKindNameException;

public enum Kind {
    LETTERS,
    DIGITS;
    public static Kind getKindByString(String kind) {
        return switch (kind) {
            case "letters" -> LETTERS;
            case "digits" -> DIGITS;
            default -> throw new WrongKindNameException();
        };
    }
}
