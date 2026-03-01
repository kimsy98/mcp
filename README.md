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
