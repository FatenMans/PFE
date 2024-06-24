package com.example.jwtauth.Service;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file) throws IOException {
        // Vérifie si le répertoire de téléchargement existe, sinon le créer
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Chemin complet du fichier
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Path path = Paths.get(filePath);

        // Sauvegarde du fichier
        Files.write(path, file.getBytes());

        return file.getOriginalFilename();
    }
}
