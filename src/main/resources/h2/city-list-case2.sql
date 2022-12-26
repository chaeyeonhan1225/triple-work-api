
INSERT INTO CITY (id, name, countryCode, status, version, createdAt, updatedAt)
VALUES
    (1, 'Busan', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (2, 'Seoul', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (3, 'NewYork', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (4, 'Tokyo', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (5, 'London', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (6, 'Paris', 'KR', 1, 0, '2022-01-01T00:00:00', CURRENT_DATE);


-- TODO: 개선 필요
INSERT INTO "User" (id, email, nickname, status, version, createdAt, updatedAt)
VALUES
    (1, 'test1@gmail.com', '테스트 계정1', 1, 0, CURRENT_DATE, CURRENT_DATE),
    (2, 'test2@gmail.com', '테스트 계정2', 1, 0, CURRENT_DATE, CURRENT_DATE),
    (3, 'test3@gmail.com', '테스트 계정3', 1, 0, CURRENT_DATE, CURRENT_DATE);

-- TODO: 개선 필요
INSERT INTO TRIP (id, userId, cityId, title, startedAt, endedAt, status, version, createdAt, updatedAt)
VALUES
    (1, 1, 2, '테스트 여행', '2022-01-02', CURRENT_DATE, 1, 0, CURRENT_DATE, CURRENT_DATE),
    (2, 1, 3, '테스트 여행', '2022-01-01', CURRENT_DATE, 1, 0, CURRENT_DATE, CURRENT_DATE);


INSERT INTO DatabaseSequence (id, seq)
VALUES
    ('City', 6),
    ('User', 3),
    ('Trip', 2);