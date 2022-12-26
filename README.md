# Triple-work-api
## 사용 기술
- Kotlin
- Spring Boot
- JPA
- DDD

## ERD

## 유즈 케이스
### 도시(City)
1. 도시를 등록한다.
2. 도시를 수정한다.
3. 도시를 삭제한다.
4. 도시 하나를 조회한다.
5. 사용자별 도시 리스트를 조회한다.
### 여행(Trip)
1. 여행을 등록한다.
2. 여행을 수정한다.
3. 여행을 삭제한다.
4. 여행 하나를 조회한다.
### 유저(User)
1. 유저를 등록한다.
## 사전 준비
Local MySQL에 `Triple`이라는 이름의 데이터베이스를 생성합니다.
## 실행 방법
1. `git clone`으로 프로젝트를 다운받습니다.
   ```bash
    git clone https://github.com/chaeyeonhan1225/triple-work-api.git
   ```
2. 해당 프로젝트 폴더에서 터미널을 열어서 아래의 명령어를 입력합니다.
    ```bash
    ./gradlew bootJar
   ```
3. jar 파일이 생성되면 `build/lib`로 이동한 후, 아래의 명령어로 실행합니다.
    ```bash
   java -jar sample.jar --spring.profiles.active=<환경> 
   ```
   
4. 환경에 맞는 포트 번호에서 서버가 구동되는것을 확인할 수 있습니다.
5. spring-docs에서 테스트할 수 있습니다.
   - `localhost:<port>/swagger-ui.html`

**환경별 포트 번호**

| environment | port |
|-------------|------|
| test        | 8080 |
| development | 3000 |
| production  | 8080 |

