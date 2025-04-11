# 📸 Pstagram

일상을 공유하고 친구들과 소통하는 SNS 프로젝트입니다.  
회원가입부터 친구 기능, 게시물/댓글/좋아요까지 — 뉴스피드 기반의 소셜 플랫폼을 구현합니다.

---

## 🛠 기술 스택

| 구분       | 기술                             |
|------------|----------------------------------|
| Language   | Java 17                          |
| Framework  | Spring Boot 3.2                  |
| ORM        | Spring Data JPA                  |
| DB         | MySQL (H2 → 전환)                |
| Build Tool | Gradle                           |
| 인증       | Session 기반 (@SessionAttribute) |
| 테스트     | Postman / IntelliJ HTTP Client   |
| 기타       | JPA Auditing, Soft Delete, 커스텀 예외 처리 등 |

---

## 📂 프로젝트 구조

```
com.example.pstagram
├── config                  # 설정 (JPA Auditing, PasswordEncoder 등)
├── controller              # API 진입 지점 (User, Post, Comment 등)
├── domain                  # JPA 엔티티
│   ├── user
│   ├── post
│   ├── comment
│   └── friend
├── dto                     # 요청/응답 DTO
│   ├── user
│   ├── post
│   ├── comment
│   └── friend
├── exception               # 공통 및 도메인별 예외 정의
│   └── user / post / comment / friend
├── repository              # JPA 인터페이스
├── service                 # 비즈니스 로직
└── common                  # ApiResponse, ResponseCode 등 공통 응답 도구
```

---

## 👥 팀 역할 분담

| 이름   | 담당 |
|--------|------|
| 정이슬 | 사용자 인증, 비밀번호 변경, 회원 탈퇴, 게시물 기능 |
| 윤소현 | 프로필 관리, 예외 처리 |
| 이윤혁 | 댓글, 좋아요 기능 |
| 정은세 | 친구 기능, 전체 테스트 작성, 문서화 |
| 한시습 | 팀장, 스프링 강의 및 적용 |

---

## ✅ 예외 응답 구조 예시

```json
{
  "message": "비밀번호가 일치하지 않습니다.",
  "data": null
}
```

- 모든 응답은 `ApiResponse<T>` 형태로 일관성 있게 제공됩니다.  
- 메시지는 `message.properties`에서 관리됩니다.

---

## 📌 추가 예정 기능

- 이미지 업로드 (S3)
- 해시태그 기능
- 마이페이지 (내 게시물/친구 리스트 등)
