package helpers;

import java.util.Optional;

public class TextHelper {

    public static final String removeLastCharOptional(String s, int quantity) {
        return Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - quantity))
                .orElse(s);
    }

    public static final String removeDotsOptional(String s) {
        return s.replace(".", "");
    }

    public static final int convertToInt(String s) {
        return Integer.parseInt(s);
    }

}

