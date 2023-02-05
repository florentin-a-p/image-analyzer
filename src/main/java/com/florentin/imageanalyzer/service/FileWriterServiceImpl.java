package com.florentin.imageanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileWriterServiceImpl implements FileWriterService{
  @Override
  public MultipartFile writeFile(String input, String fileName) {
    log.info("FileWriterService.writeFile is called");
    String name = "output_"+fileName+".html";
    Path path = Paths.get("output/"+name);

    // write the html file into a local output (cache)
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
      writer.write("<html><body>" + input + "</body></html>");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // re-read the written file and convert it to MultipartFile
    String contentType = "text/plain";
    byte[] content = null;

    try {
      content = Files.readAllBytes(path);
    } catch (final IOException e) {
    }

    MultipartFile result = new MockMultipartFile(name, name, contentType, content);
    return result;
  }
}
