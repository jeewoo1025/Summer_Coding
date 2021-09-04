루시와 엘라 찾기

select animal_id, name, sex_upon_intake
from animal_ins
where name in ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
order by animal_id;


이름에 el이 들어가는 동물 찾기

SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE NAME LIKE '%el%' and ANIMAL_TYPE = 'DOG'
ORDER BY NAME;


중성화 여부 파악하기

SELECT ANIMAL_ID, NAME,
    CASE
        WHEN SEX_UPON_INTAKE LIKE 'Intact%' THEN 'X' ELSE 'O'
    END AS '중성화'
FROM ANIMAL_INS;


오랜기간 보호한 동물(2)

SELECT b.ANIMAL_ID, b.NAME
FROM ANIMAL_INS a
RIGHT JOIN ANIMAL_OUTS b ON a.ANIMAL_ID=b.ANIMAL_ID
ORDER BY (b.DATETIME - a.DATETIME) DESC
LIMIT 2;


DATETIME에서 DATE로 형 변환

SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') AS '날짜'
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;