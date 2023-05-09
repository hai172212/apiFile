package com.example.spring4mbankingapisasu.file.web;

import lombok.Builder;
@Builder
public record FileDto(String name,
                      String extension,
                      Long size,
                      String url) {
}
