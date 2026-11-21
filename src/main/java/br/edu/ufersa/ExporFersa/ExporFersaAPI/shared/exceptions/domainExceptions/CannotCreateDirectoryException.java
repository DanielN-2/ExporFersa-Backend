package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.BusinessException;

public class CannotCreateDirectoryException extends BusinessException{
    public CannotCreateDirectoryException(String message) {
        super(message);
    }
}
