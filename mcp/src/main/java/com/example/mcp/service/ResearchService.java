package com.example.mcp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ResearchService {

    private final ChatClient chatClient;

    public ResearchService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String searchAcademicPapers(String topic) {
        return chatClient.prompt()
                .system("너는 전문 연구원이야. 'arxiv-researcher' 도구를 사용하여 주제에 대한 최신 논문을 검색하고 핵심을 한국어로 요약해.")
                .user(topic + "에 대한 최신 논문 3개만 찾아줘.")
                .call()
                .content();
    }
}
