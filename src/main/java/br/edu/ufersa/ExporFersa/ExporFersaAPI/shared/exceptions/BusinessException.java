package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions;

public abstract class BusinessException extends RuntimeException {
    protected BusinessException(String message) {
        super(message);
    }
}