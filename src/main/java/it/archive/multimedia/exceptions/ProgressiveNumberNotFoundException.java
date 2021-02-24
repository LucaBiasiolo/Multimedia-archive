package it.archive.multimedia.exceptions;

public class ProgressiveNumberNotFoundException extends Throwable {
    public ProgressiveNumberNotFoundException(int day, int month, int year) {
        super(String.format("ProgressiveNumberNotFoundException: Un numero progressivo per la data %d-%d-%s " +
                "non è stato trovato", day, month, year));
    }
}