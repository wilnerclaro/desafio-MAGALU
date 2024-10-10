package br.com.desafio_magaLu.comunicacao.exception;

public class CommunicationException extends RuntimeException {
    public CommunicationException(String menssagem) {
        super(menssagem);
    }

    public CommunicationException(Throwable cause) {
        super(cause);
    }

    public CommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
