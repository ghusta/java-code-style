package fr.husta.tool.ide.formatting;

public class ExporterException extends RuntimeException {

    public ExporterException() {
    }

    public ExporterException(String message) {
        super(message);
    }

    public ExporterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExporterException(Throwable cause) {
        super(cause);
    }

}
