/* 1. 없어진 기록 찾기 */
SELECT b.ANIMAL_ID, b.NAME
FROM ANIMAL_INS a
RIGHT JOIN ANIMAL_OUTS b ON a.ANIMAL_ID = b.ANIMAL_ID
WHERE a.ANIMAL_ID IS NULL
ORDER BY ANIMAL_ID;

/* 2.있었는데요 없었습니다 */
SELECT b.ANIMAL_ID, b.NAME
FROM ANIMAL_INS a
RIGHT JOIN ANIMAL_OUTS b ON a.ANIMAL_ID = b.ANIMAL_ID
WHERE a.DATETIME >= b.DATETIME
ORDER BY a.DATETIME;

/* 3.오랜기간 보호한 동물(1) */
SELECT a.NAME, a.DATETIME
FROM ANIMAL_INS a
LEFT JOIN ANIMAL_OUTS b ON a.ANIMAL_ID = b.ANIMAL_ID
WHERE b.ANIMAL_ID IS NULL
ORDER BY a.DATETIME
LIMIT 3;

/* 4.보호소에서 중성화한 동물 */
SELECT b.ANIMAL_ID, b.ANIMAL_TYPE, b.NAME
FROM ANIMAL_INS a
INNER JOIN ANIMAL_OUTS b ON a.ANIMAL_ID = b.ANIMAL_ID
WHERE a.SEX_UPON_INTAKE LIKE 'Intact%'
AND NOT b.SEX_UPON_OUTCOME LIKE 'Intact%';