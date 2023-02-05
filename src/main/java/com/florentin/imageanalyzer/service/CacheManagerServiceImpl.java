package com.florentin.imageanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class CacheManagerServiceImpl implements CacheManagerService {
  private final Path rootUploads = Paths.get("./uploads");
  private final Path rootOutput = Paths.get("./output");

  @Override
  public void init() {
    log.info("CacheManagerService.init is called");
    try {
      Files.createDirectories(rootUploads);
      Files.createDirectories(rootOutput);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    log.info("CacheManagerService.save is called");
    try {
      Files.copy(file.getInputStream(), this.rootUploads.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
      }

      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    log.info("CacheManagerService.deleteAll is called");
    FileSystemUtils.deleteRecursively(rootUploads.toFile());
    FileSystemUtils.deleteRecursively(rootOutput.toFile());
  }

}
