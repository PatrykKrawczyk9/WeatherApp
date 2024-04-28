package pl.kurs.weatherapp.io;

import org.springframework.stereotype.Service;
import pl.kurs.weatherapp.data.Option;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DataReader implements InputService {

    private final Scanner scanner;

    public DataReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Option chooseOption() {
        int numberOption;
        Optional<Option> option;

        do {
            Option.printOption();
            numberOption = scanner.nextInt();
            scanner.nextLine();
            option = Option.fromInt(numberOption);
        } while (option.isEmpty());

        return option.get();
    }

    @Override
    public LocalDate getDateFromUser() {
        System.out.println("Podaj datę w formacie yyyy-mm-dd");
        String dateFromUser = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateFromUser, formatter);
    }

    @Override
    public String getCityFromUser() {
        System.out.println("Podaj nazwę miejscowości");
        return scanner.nextLine();
    }
}
