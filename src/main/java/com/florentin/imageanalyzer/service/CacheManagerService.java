package com.florentin.imageanalyzer.service;

import org.springframework.web.multipart.MultipartFile;

public interface CacheManagerService {
  public void init();
  public void save(MultipartFile file);
  public void deleteAll();
}
