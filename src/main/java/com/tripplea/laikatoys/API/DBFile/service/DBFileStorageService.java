package com.tripplea.laikatoys.API.DBFile.service;

import com.tripplea.laikatoys.API.DBFile.exception.FileStorageException;
import com.tripplea.laikatoys.API.DBFile.exception.MyFileNotFoundException;
import com.tripplea.laikatoys.API.DBFile.model.DBFile;
import com.tripplea.laikatoys.API.DBFile.repository.DBFileRepository;
import com.tripplea.laikatoys.product.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class DBFileStorageService {

    private final DBFileRepository dbFileRepository;

    public DBFileStorageService(DBFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}