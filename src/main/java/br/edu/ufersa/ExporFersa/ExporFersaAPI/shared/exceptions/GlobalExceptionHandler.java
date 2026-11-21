package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.UUID;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler()
    public ProblemDetail handleInvalidArgument(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Argumento inválido");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Recurso Não Encontrado");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(DataConflictException.class)
    public ProblemDetail handleConflict(DataConflictException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Conflito de Dados");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ProblemDetail handleConflict(InvalidOperationException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());
        problem.setTitle("Regra de Negócio Violada");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDenied(AccessDeniedException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problem.setTitle("Acesso negado");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleInvalidRequestBody(HttpMessageNotReadableException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "O corpo da requisição contém dados inválidos."
        );
        problem.setTitle("Requisição Inválida");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleInvalidLoginCredentials(BadCredentialsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Usuário inexistente ou senha inválida."
        );
        problem.setTitle("Credenciais Inválidas");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(CannotCreateDirectoryException.class)
    public ProblemDetail handleCannotCreateDirectory(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Não foi possível criar o diretório de imagens."
        );
        problem.setTitle("Erro no serviço de imagens");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(CannotSaveImageException.class)
    public ProblemDetail handleCannotSaveImage(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Não foi possível salvar a imagem."
        );
        problem.setTitle("Erro no serviço de imagens");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(CannotDeleteImageException.class)
    public ProblemDetail handleCannotDeleteImage(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Não foi possível deletar a imagem."
        );
        problem.setTitle("Erro ao deletar a imagem.");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ProblemDetail handleImageNotFound(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Não foi possível achar a imagem."
        );
        problem.setTitle("Imagem não encontrada");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedErrors(Exception ex) {
        String traceId = UUID.randomUUID().toString();
        log.error("Erro interno inesperado detectado [Trace ID: {}]", traceId, ex);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno inesperado no sistema. Contate o suporte com o código de rastreio.");
        problem.setTitle("Erro Interno do Servidor");
        problem.setProperty("traceId", traceId);
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
