package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.CannotCreateDirectoryException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.CannotDeleteImageException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.CannotSaveImageException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.ImageData;

@Service 
public class FileService {

    private final Path imagesDirectory = Path.of("images/");
    private final Set<String> SuportedExtensions = Set.of(
        "jpeg", "jpg", "png"
    );
    
    FileService() {
        if (!Files.exists(imagesDirectory)) {
            try {
                Files.createDirectory(imagesDirectory);
                Files.createDirectory(imagesDirectory.resolve("eventos"));
                Files.createDirectory(imagesDirectory.resolve("projetos"));
            } catch (IOException e) {
                System.out.println("Erro ao criar o diretório de images.");
                throw new CannotCreateDirectoryException(e.getMessage());
            }
        }
    }

    public ImageData saveImage(String directory, String title, MultipartFile file) {

        String extension = retrieveFileExtension(file.getOriginalFilename());
        if (isImageByExtension(extension)) {
            throw new CannotSaveImageException("O arquivo não possui uma extensão válida.");
        }

        LocalDateTime savedAt = LocalDateTime.now();

        String imageURL = directory + "/" + title + "-" + savedAt.toString().replace(":", "") + extension;

        try {
            Files.copy(file.getInputStream(), imagesDirectory.resolve(imageURL));
        } catch (IOException e) {
            throw new CannotSaveImageException("Não foi possível salvar a imagem: " + e.getMessage());
        }

        return new ImageData(imageURL, title);
    }

    public boolean existsImage(String imageURL) {
        return Files.exists(imagesDirectory.resolve(imageURL));
    }

    public boolean deleteImage(String imageURL) {
        try {
            return Files.deleteIfExists(imagesDirectory.resolve(imageURL));
        } catch (IOException e) {
            throw new CannotDeleteImageException("Não foi possível deletar a imagem: " + e.getMessage());
        }
    }

    private String retrieveFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") == -1) {
            return null;
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }

    private boolean isImageByExtension(String extension) {
        if (extension == null) {
            return false;
        }

        return SuportedExtensions.contains(extension.toLowerCase());
    }
    
}
