package com.aldenor_neto.devout_catholic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class AiRequest {
    private List<Content> contents;

    @Data
    public static class Content {
        private List<Part> parts;
    }

    @Data
    public static class Part {
        private String text;
    }

    // Construtor que recebe uma string para inicializar o campo text
    public AiRequest(String question) {
        Part part = new Part();
        part.setText(question);

        Content content = new Content();
        content.setParts(Collections.singletonList(part));

        this.contents = Collections.singletonList(content);
    }
}
