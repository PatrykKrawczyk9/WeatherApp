package pl.kurs.weatherapp.data;

import java.util.Optional;

public enum Option {
    EXIT(0, "Wyjście z programu"),
    CURRENT_WEATHER(1, "Aktualna srednia temperatura"),
    AVG_FORECAST_WEATHER(2, "Prognoza sredniej temperatury");

    private int number;
    private String description;

    Option(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public static void printOption() {
        for (Option value : values()) {
            System.out.println(value.number + " - " + value.description);
        }
    }

    public static Optional<Option> fromInt(int number) {
        Option[] values = values();
        if (number >= 0 && number < values.length) {
            return Optional.of(values[number]);
        } else
            return Optional.empty();
    }
}
