# Triple-work-api
## 사용 기술
- Kotlin
- Spring Boot
- JPA
- DDD

## 클래스 다이어그램
![](triple-work.drawio.png)
## 유즈 케이스
### 도시(City)
1. 도시를 등록한다. 
   ```http request
   POST "http://localhost:{port}/v1/cities"
   ```
2. 도시를 수정한다.
   ```http request
   PUT "http://localhost:{port}/v1/cities/{id}"
   ```
3. 도시를 삭제한다.
   ```http request
   DELETE "http://localhost:{port}/v1/cities/{id}"
   ```
4. 도시 하나를 조회한다.
    ```http request
   GET "http://localhost:{port}/v1/cities/{id}"
   ```
5. 사용자별 도시 리스트를 조회한다.
     ```http request
   GET "http://localhost:{port}/v1/cities?userId={userId}"
   ```
### 여행(Trip)
1. 여행을 등록한다.
     ```http request
   POST "http://localhost:{port}/v1/trips"
   ```
2. 여행을 수정한다.
    ```http request
   PUT "http://localhost:{port}/v1/trips/{id}"
   ```
3. 여행을 삭제한다.
    ```http request
   DELETE "http://localhost:{port}/v1/trips/{id}"
   ```
4. 여행 하나를 조회한다.
    ```http request
   GET "http://localhost:{port}/v1/trips/{id}"
   ```
### 유저(User)
1. 유저를 등록한다.
    ```http request
   POST "http://localhost:{port}/v1/users/{id}"
   ```
## 사전 준비(production)
Local MySQL에 `TripleWork`이라는 이름의 데이터베이스를 생성합니다.
## 설정


- **환경별 포트 번호**

   | environment | port |
   |------|------|
   | test        | 8080 |
   | development | 3000 |
   | production  | 6080 |

- DB 설정 정보(production)
  ```yaml
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/Triple?serverTimezone=UTC&useCursors=false&sendStringParametersAsUnicode=false&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false
    username: root
    password:
   ```
  
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


