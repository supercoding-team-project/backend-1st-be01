# backend-1st-be01

## 기술 스택
* Java
* Spring Boot
* JPA (Java Persistence API)
* JWT (JSON Web Tokens) 인증

## 주요 컴포넌트
### LikeService
* 이 서비스는 좋아요 관리에 대한 비즈니스 로직을 처리합니다.<br>
* 댓글 ID로 좋아요를 찾기, 좋아요 저장, 좋아요 삭제 등의 메소드를 제공합니다.

### 주요 메소드
* findLikeByCommentId(String commentId): 주어진 댓글 ID와 관련된 모든 좋아요를 검색합니다.
* saveLike(String commentId, Integer userId): 주어진 댓글과 사용자에 대한 좋아요를 저장합니다.
* deleteLike(String commentId, Integer userId): 특정 댓글과 사용자에 대한 좋아요를 삭제합니다.

### CommentService
* 이 서비스는 댓글 관리에 대한 비즈니스 로직을 처리합니다.
* 댓글을 찾기, 저장, 수정, 삭제하는 메소드를 제공합니다.

### 주요 메소드
* findAllComment(): 모든 댓글을 검색합니다.
* saveComment(CommentBody commentBody): 새로운 댓글을 저장합니다.
* updateComment(String id, CommentBody commentBody): 기존 댓글을 수정합니다.
* deleteComment(String id): 특정 댓글을 삭제합니다.

### LikeController
* 이 컨트롤러는 좋아요 관련 HTTP 요청을 처리합니다.
* JwtTokenProvider를 사용하여 인증을 처리하고 LikeService와 상호작용하여 좋아요 관련 작업을 처리합니다.

### 엔드포인트
* GET /api/likes/{commentId}: 특정 댓글에 대한 좋아요를 가져옵니다.
* POST /api/likes/{commentId}: 댓글에 좋아요를 추가합니다.
* DELETE /api/likes/{commentId}: 댓글에서 좋아요를 삭제합니다.

### CommentController
* 이 컨트롤러는 댓글 관련 HTTP 요청을 처리합니다.
* CommentService와 상호작용하여 댓글 관련 작업을 처리합니다.

### 엔드포인트
* GET /api/comments: 모든 댓글을 가져옵니다.
* POST /api/comments: 새로운 댓글을 추가합니다.
* PUT /api/comments/{id}: 기존 댓글을 수정합니다.
* DELETE /api/comments/{id}: 특정 댓글을 삭제합니다.


### 오류 처리
시스템은 무단 접근, 댓글이나 좋아요를 찾을 수 없는 경우, 일반적인 잘못된 요청 등 다양한 오류를 처리하도록 설계되었습니다.<br>
적절한 HTTP 상태 코드와 오류 메시지가 반환됩니다.

### 보안
엔드포인트 보안을 위해 JWT가 사용됩니다.<br>
JwtTokenProvider 클래스는 토큰 유효성 검증과 토큰에서 사용자 세부 정보 추출을 처리합니다.

## 향후 개선 사항
* 게시물과 같은 다른 엔티티에 대한 좋아요 기능 확장
* 오류 처리 및 로깅 메커니즘 개선
* 좋아요 시스템의 남용을 방지하기 위한 요청 제한 구현
