package com.florentin.imageanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.awt.Image;
import java.io.File;

@Service
@Slf4j
public class ImageToTextServiceImpl implements ImageToTextService {
  public  String extractTextFromImage(String imageName, String language) throws TesseractException {
    log.info("ImageToTextService.extractTextFromImage is called");
    ITesseract instance = new Tesseract();
    instance.setDatapath("src/main/resources/tessdata");
    instance.setLanguage(language);
    String result = instance.doOCR(new File("uploads/"+imageName));
    return result;
  }
}
