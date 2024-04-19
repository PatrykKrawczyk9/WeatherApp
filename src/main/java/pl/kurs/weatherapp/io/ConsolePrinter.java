package pl.kurs.weatherapp.io;

public class ConsolePrinter implements OutputService{

    @Override
    public void printLine(String text) {
        System.out.println(text);
    }

    @Override
    public void printOption () {
        System.out.println("0 - Wyjscie z programu.");
        System.out.println("1 - Sprawdź aktualną temperature w mieście");
        System.out.println("2 - Sprawdź prognozę temperatury w mieście");
    }

    @Override
    public void exit() {
        System.out.println("Koniec programu, papa");
    }
}
