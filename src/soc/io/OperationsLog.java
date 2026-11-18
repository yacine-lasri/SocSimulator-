package soc.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class OperationsLog {
    private final Path path;

    public OperationsLog(Path path) {
        this.path = path;
    }
    public synchronized void log(String message){
        try {
            Files.writeString(path,LocalDateTime.now()+message+System.lineSeparator(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot write to this file",e);
        }
    }
}
