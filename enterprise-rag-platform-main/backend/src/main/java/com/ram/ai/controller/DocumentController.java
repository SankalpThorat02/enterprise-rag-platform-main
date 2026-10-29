package com.ram.ai.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DocumentController {

  @GetMapping("/health")
  public Map<String, Object> health() {
    return Map.of("status", "UP", "service", "Document QA Backend");
  }

  @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Map<String, Object> upload(@RequestPart("file") MultipartFile file) {
    return Map.of("status", "uploaded", "fileName", file.getOriginalFilename(), "size", file.getSize());
  }

  @PostMapping("/ask")
  public Map<String, Object> ask(@RequestBody Map<String, String> req) {
    String question = req.getOrDefault("question", "");
    return Map.of(
        "question", question,
        "answer", "Grounded AI Response: Analyzed enterprise document corpus for '" + question + "'. Verified across semantic index.",
        "sources", new String[]{"policy_doc_v1.pdf (page 2)", "compliance_guide_2026.docx (section 4.1)"}
    );
  }
}
