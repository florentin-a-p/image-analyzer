package com.florentin.imageanalyzer;

import com.florentin.imageanalyzer.service.CacheManagerService;
import jakarta.annotation.Resource;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageAnalyzerApplication implements CommandLineRunner {
	@Resource
	CacheManagerService cacheManagerService;

	public static void main(String[] args) throws TesseractException {
		SpringApplication.run(ImageAnalyzerApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		cacheManagerService.deleteAll();
		cacheManagerService.init();
	}

}
