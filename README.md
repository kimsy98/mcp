implementation platform("org.springframework.ai:spring-ai-bom:1.1.2")
의존성 추가 필요(https://start.spring.io/ 방식이 제일 정확함)

implementation 'org.springframework.ai:spring-ai-starter-model-google-genai'
    빌드 시 yml 설정 필요 (넣어줘야 Gemini 개발자 API 사용 가능 아니면 Vertex AI 방식으로 잡혀서 에러 발생)
```
spring:
  ai:
    google:
      genai:
        api-key: {$API_KEY}
        chat:
          options:
            model: gemini-1.5-flash
            temperature: 0.5
```
추후 mcp 서버 연동 예정 
- 1차 목표 : 자료 수집 및 정리 후 저장 기능
- 2차 목표 : toneup 서비스 연동 후 로그 이상 시 디스코드 or 텔레그램으로 전송 하는 모니터링 기능


## arxiv-mcp-server(논문 검색 mcp 서버) 다운로드 및 실행 방법 윈도우
## https://github.com/blazickjp/arxiv-mcp-server
### mcp 서버 연동 방식은 yml 로 연결 방식 or json 파일로 연동 방식 두가지
- 파워쉘 열고 powershell -ExecutionPolicy ByPass -c "irm https://astral.sh/uv/install.ps1 | iex" (uv 명령어 설치)
- 파워쉘 껐다 키고 uv --version 확인
- uv tool install arxiv-mcp-server(mcp 서버 설치)
- application.yml 설정 스프링 구동시 자동으로 서버 올려서 연결함
  ```
     spring:
        ai:
          mcp:
           client:
            stdio:
              connections:
                arxiv-researcher:
                  command: cmd
                  args:
                    - "/c"
                    - "uv"
                    - "tool"
                    - "run"
                    - "arxiv-mcp-server"
                    - "--storage-path"
                    - "C:/Users/gkstj/Desktop/mcp/mcp/papers" # 파일 저장 위치
  ```
chatclient 빈 생성 시 defaultSystem 페르소나 설정, defaultToolCallbacks mcp 서버 주입, defaultAdvisors 대화 맥락 기억용 
- SyncMcpToolCallbackProvider: MCP 서버가 제공하는 도구(예: arxiv_search, save_file)들을 Spring AI가 이해할 수 있는 FunctionCallback 형태로 자동 변환
- MessageChatMemoryAdvisor: 대화가 오갈 때마다 이전 메시지들을 자동으로 프롬프트에 포함시켜 주는 역할
- MessageWindowChatMemory: * Window 방식: 무한정 기억하는 것이 아니라, 최신 N개의 메시지만 '창(Window)' 안에 담아 기억
기본 저장소: 별도의 설정이 없으면 **서버의 RAM(InMemory)**을 사용(추후에 이전에 가져온 자료 제외를 위해 db 연동 필요)
