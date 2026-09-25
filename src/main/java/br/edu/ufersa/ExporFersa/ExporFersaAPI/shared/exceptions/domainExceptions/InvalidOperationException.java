package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.BusinessException;

public class InvalidOperationException extends BusinessException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
