package com.florentin.imageanalyzer.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriterService {
  public MultipartFile writeFile(String input, String fileName);
}
