implementation platform("org.springframework.ai:spring-ai-bom:1.1.2")
의존성 추가 필요(https://start.spring.io/ 방식이 제일 정확함)

implementation 'org.springframework.ai:spring-ai-starter-model-google-genai'
    빌드 시 yml 설정 필요 (넣어줘야 Gemini 개발자 API 사용 가능 아니면 Vertex AI 방식으로 잡혀서 에러 발생)
spring:
  application:
  ai:
    google:
      genai:
        api-key: {$API_KEY}
        chat:
          options:
            model: gemini-1.5-flash
            temperature: 0.5

추후 mcp 서버 연동 예정 
- 1차 목표 : 자료 수집 및 정리 후 저장 기능
- 2차 목표 : toneup 서비스 연동 후 로그 이상 시 디스코드 or 텔레그램으로 전송 하는 모니터링 기능
