package graze.engine

import graze.pasture.Pasture

@Singleton
class Canvas {
    ArrayList<String> titles = []
    ArrayList<String> messages = []
    ArrayList<String> log = []
    int lineCount = -1
    Pasture pasture

    void title(String text) {
        titles.add(text)
    }

    void message(String text) {
        messages.add(text)
    }

    void log(String text) {
        log.add(text)
    }

    void pasture(Pasture pasture) {
        this.pasture = pasture
    }

    String drawPasture() {
        String output = "*-${"--" * pasture[0].size()}*\n"
        pasture.each { row ->
            output += '|'
            row.each { tile ->
                output += ' '
                if (tile.actors.empty) {
                    output += ' '
                    return
                } else {
                    output += tile.actors.last().getIcon()
                }
            }
            output += ' |\n'
        }
        output += ("*-${"--" * pasture[0].size()}*\n")
        return output
    }

    void paint() {
        String output = "${'=' * 20}\n"
        titles.each { output += "${it}\n" }
        output += "${'=' * 20}\n"
        output += drawPasture()
        if (messages) {
            output += "${'=' * 20}\n"
            messages.each { output += "${it}\n" }
        }
        if (false) {
            output += "${'=' * 20}\n"
            log.each { output += "${it}\n" }
        }

        // Clear the console and paint
        System.out.print("\033[H\033[2J");
        print output

        titles.clear()
        messages.clear()
        log.clear()
    }
}
