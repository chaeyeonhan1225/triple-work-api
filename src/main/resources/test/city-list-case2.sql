
INSERT INTO CITY (id, name, countryCode, latitude, longitude, status, version, createdAt, updatedAt)
VALUES
    (1, 'Busan', 'KR', 35.179554, 129.075638,  1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (2, 'Seoul', 'KR', 37.566536, 126.977966, 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (3, 'NewYork', 'KR', 40.712776, -74.005974,  1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (4, 'Tokyo', 'KR', 35.689487, 139.691711, 1, 0, '2022-01-01T00:00:00', CURRENT_DATE),
    (5, 'London', 'KR', 51.509865, -0.118092, 1, 0,'2022-01-01T00:00:00', CURRENT_DATE),
    (6, 'Paris', 'KR', 48.864716, 2.349014, 1, 0, '2022-01-01T00:00:00', CURRENT_DATE);

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