package com.florentin.imageanalyzer.service;

import net.sourceforge.tess4j.TesseractException;

public interface ImageToTextService {
  public String extractTextFromImage(String imageName, String language) throws TesseractException;
}
