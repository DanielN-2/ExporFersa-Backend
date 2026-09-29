package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.BusinessException;

public class DataConflictException extends BusinessException {
    public DataConflictException(String message) {
        super(message);
    }
}