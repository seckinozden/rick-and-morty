package com.ingress.rick;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@SpringBootApplication
public class RickApplication {

  public static void main(String[] args) {
    SpringApplication.run(RickApplication.class, args);
  }

  @GetMapping("/")
  public ResponseEntity<byte[]> welcome() throws IOException {
    File img = new File("rick.png");
    return ResponseEntity
        .ok()
        .contentType(MediaType.IMAGE_PNG)
        .body(Files.readAllBytes(img.toPath()));
  }

  @GetMapping("/hello")
  public ResponseEntity<byte[]> hello() throws IOException {
    File img = new File("rick_pickle.gif");
    return ResponseEntity
        .ok()
        .contentType(MediaType.IMAGE_GIF)
        .body(Files.readAllBytes(img.toPath()));
  }
}
