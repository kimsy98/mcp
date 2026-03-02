package com.example.mcp.service;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ResearchService {

    private final ChatClient chatClient;
//    private final List<McpSyncClient> mcpSyncClients ;

    public ResearchService(ChatClient chatClient) {
        this.chatClient = chatClient;
//        this.mcpSyncClients = mcpSyncClients;
    }

    public String searchAcademicPapers(String topic, boolean saveResearch) {

        String behavior = saveResearch
                ? "검색한 논문 정보를 arxiv 도구로 저장할 때, 나에게 다시 묻지 말고 즉시 파일로 생성해. 파일명은 '논문제목_날짜' 형식으로 자동 지정해."
                : "논문을 요약해서 텍스트로만 알려줘. 절대 파일을 생성하거나 저장 기능을 호출하지 마.";

        return chatClient.prompt()
                .system(behavior)
                .user(topic + "에 대한 최신 논문 3개만 찾아줘.")
                .call()
                .content();
    }
}
