package com.florentin.imageanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ColourCoderServiceImpl implements ColourCoderService{
  public String colourCodeOnAlphabet(String input, String fileName, String colour, CharSequence letter) {
    log.info("ColourCoderService.colourCodeOnAlphabet is called");
    StringBuilder output = new StringBuilder();
    String[] words = input.split(" ");
    for (String word : words) {
      if (word.toLowerCase().contains(letter)) {
        output.append("<span style='color:" + colour + "'>" + word + "</span> ");
      } else {
        output.append(word + " ");
      }
    }

    return output.toString();
  }
}
