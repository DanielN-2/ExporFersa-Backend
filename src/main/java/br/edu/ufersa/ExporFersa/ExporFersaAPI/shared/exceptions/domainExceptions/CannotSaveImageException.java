package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.BusinessException;

public class CannotSaveImageException extends BusinessException {
    public CannotSaveImageException(String message) {
        super(message);
    }
}
