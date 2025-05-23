## 👥 담당 역할

(※ 리팩토링은 각자의 기준에 따라 개별적으로 진행되어, 코드 스타일 및 구조에 일부 통일성이 없을 수 있습니다.)

| 이름 | 담당 기능 |
|------|------------|
| **나 (정은세)** | **친구 기능 전체 구현 및 실무형 리팩토링 담당** |


---


## ✨ 주요 구현 기능 - 친구(Friend) 도메인

> 친구 요청, 수락, 거절, 삭제 기능을 모두 직접 구현하고 실무 기준에 맞게 리팩토링했습니다.

### ✅ 담당 기능 요약
- 친구 요청/수락/거절/삭제 API
- 친구 상태(FriendStatus: 대기/수락/거절) 관리
- 친구 목록, 대기 목록 조회 API

### 🧠 실무형 리팩토링 포인트
- `Entity` 중심 도메인 주도 설계 (DDD)
- `FriendService`에서 비즈니스 로직 분리 및 위임
- API 응답 포맷 통일 (`CommonResponse<T>`, `SuccessCode`)
- 커스텀 예외 처리 (`FriendErrorCode`, `GlobalExceptionHandler`)
- enum 상태값에 설명 추가 (`FriendStatus.getDescription()`)

---

## 🔍 API 명세 예시 

| 메서드 | 경로 | 설명 |
|--------|------|------|
| POST | `/api/friends/request/{userId}` | 친구 요청 |
| POST | `/api/friends/accept/{friendId}` | 친구 요청 수락 |
| DELETE | `/api/friends/{friendId}` | 친구 삭제 |

---

## ✅ 기술 스택

- Java 17
- Spring Boot 3
- JPA (Hibernate)
- Spring MVC
- MySQL
