package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.BusinessException;

public class CannotDeleteImageException extends BusinessException {
    public CannotDeleteImageException(String message) {
        super(message);
    }
}