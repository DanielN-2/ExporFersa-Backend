package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.BusinessException;

public class ImageNotFoundException extends BusinessException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
