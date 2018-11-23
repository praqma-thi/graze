package graze.engine

class Color {
    static String green(String text) {
        return "\u001B[32m${text}\u001B[0m"
    }
}