
INSERT INTO CITY (id, name, countryCode, status, version, createdAt, updatedAt)
VALUES
    (1, 'Busan', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (2, 'Seoul', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (3, 'NewYork', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (4, 'Tokyo', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (5, 'London', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (6, 'Paris', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (7, 'Hanoi', 'KR', 1, 0, CURRENT_DATE, CURRENT_DATE),
    (8, 'Bangkok', 'KR', 1, 0, CURRENT_DATE, CURRENT_DATE);


INSERT INTO "User" (id, email, nickname, status, version, createdAt, updatedAt)
VALUES
    (1, 'test1@gmail.com', '테스트 계정1', 1, 0, CURRENT_DATE, CURRENT_DATE),
    (2, 'test2@gmail.com', '테스트 계정2', 1, 0, CURRENT_DATE, CURRENT_DATE),
    (3, 'test3@gmail.com', '테스트 계정3', 1, 0, CURRENT_DATE, CURRENT_DATE);

INSERT INTO CityViewLog (userId, cityId, version, createdAt, updatedAt)
VALUES
    (1, 3, 0, CURRENT_DATE, CURRENT_DATE),
    (1, 3, 0, CURRENT_DATE, CURRENT_DATE),
    (1, 3, 0, CURRENT_DATE, CURRENT_DATE);

INSERT INTO DatabaseSequence (id, seq)
VALUES
    ('City', 8),
    ('User', 3);