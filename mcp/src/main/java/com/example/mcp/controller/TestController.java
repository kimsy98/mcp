package com.example.mcp.controller;

import com.example.mcp.service.ResearchService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ChatClient chatClient;
    private final ResearchService researchService;

    public TestController(ChatClient chatClient, ResearchService resarchService) {
        this.chatClient = chatClient;
        this.researchService = resarchService;
    }

    @GetMapping("/test")
    public String testGemini(@RequestParam(defaultValue = "안녕! 너는 누구니?") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/test-resarch")
    public String testResearch(@RequestParam(defaultValue = "백엔드") String topic, @RequestParam(defaultValue = "false") boolean saveResearch ) {
        return researchService.searchAcademicPapers(topic, saveResearch);
    }
}