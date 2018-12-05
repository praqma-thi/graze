package graze.engine

class Color {

    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String BLUE = "\u001B[34m";
    static final String GREEN = "\u001B[32m";
    static final String CYAN = "\u001B[36m";
    static final String YELLOW = "\u001B[33m";
    static final String PURPLE = "\u001B[35m";

    static String red(String text) {
        return "${RED}${text}${RESET}"
    }

    static String blue(String text) {
        return "${BLUE}${text}${RESET}"
    }

    static String green(String text) {
        return "${GREEN}${text}${RESET}"
    }

    static String cyan(String text) {
        return "${CYAN}${text}${RESET}"
    }

    static String yellow(String text) {
        return "${YELLOW}${text}${RESET}"
    }

    static String purple(String text) {
        return "${PURPLE}${text}${RESET}"
    }
}