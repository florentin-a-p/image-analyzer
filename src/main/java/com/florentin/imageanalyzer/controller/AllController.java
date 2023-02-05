package com.florentin.imageanalyzer.controller;

import com.florentin.imageanalyzer.service.CacheManagerService;
import com.florentin.imageanalyzer.service.ColourCoderService;
import com.florentin.imageanalyzer.service.GoogleDriveFileManagerService;
import com.florentin.imageanalyzer.service.FileWriterService;
import com.florentin.imageanalyzer.service.ImageToTextService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/image-analyzer")
public class AllController {
  @Autowired
  private GoogleDriveFileManagerService googleDriveFileManagerService;
  @Autowired
  private CacheManagerService cacheManagerService;
  @Autowired
  private ImageToTextService imageToTextService;
  @Autowired
  private ColourCoderService colourCoderService;
  @Autowired
  private FileWriterService fileWriterService;

  @GetMapping({"/", "/home"})
  public String home () {
    return "index";
  }

  @GetMapping({"/extract-english"})
  public String showExtractEnglishPage () {
    return "extractEnglishHomepage";
  }

  @PostMapping(value = "/extract-english",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE} )
  public String extractEnglishText(@RequestParam("file") MultipartFile file) {
    String path = "image-analyzer-db";
    log.info("Request contains, File: " + file.getOriginalFilename());

    try {
      String fileId = googleDriveFileManagerService.uploadFile(file, path);
      if(fileId == null){
        return "redirect:/image-analyzer/extract-english?error";
      }

      cacheManagerService.save(file);
      String extractedText = imageToTextService.extractTextFromImage(file.getOriginalFilename(),"eng");
      String colourCodedText = colourCoderService.colourCodeOnAlphabet(extractedText, file.getOriginalFilename(), "blue", "o");
      MultipartFile extractedTextFile = fileWriterService.writeFile(colourCodedText,file.getOriginalFilename());
      googleDriveFileManagerService.uploadFile(extractedTextFile, path);

      return "redirect:/image-analyzer/extract-english?success";
    } catch (Exception e) {
      return "redirect:/image-analyzer/extract-english?error";
    }
  }

  @GetMapping({"/extract-chinese"})
  public String showExtractChinesePage () {
    return "extractChineseHomepage";
  }

  @PostMapping(value = "/extract-chinese",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE} )
  public String extractChineseText (@RequestParam("file") MultipartFile file) {
    String path = "image-analyzer-db";
    log.info("Request contains, File: " + file.getOriginalFilename());

    try {
      String fileId = googleDriveFileManagerService.uploadFile(file, path);
      if(fileId == null){
        return "redirect:/image-analyzer/extract-chinese?error";
      }

      cacheManagerService.save(file);
      String extractedText = imageToTextService.extractTextFromImage(file.getOriginalFilename(),"chi_sim");
      MultipartFile extractedTextFile = fileWriterService.writeFile(extractedText,file.getOriginalFilename());
      googleDriveFileManagerService.uploadFile(extractedTextFile, path);

      return "redirect:/image-analyzer/extract-chinese?success";
    } catch (Exception e) {
      return "redirect:/image-analyzer/extract-chinese?error";
    }
  }
}
