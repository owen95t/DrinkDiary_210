package exception;

import java.io.IOException;
import java.io.StreamCorruptedException;

public class LoadFailException extends IOException {
    public LoadFailException(String message) {
        super(message);
    }
}
