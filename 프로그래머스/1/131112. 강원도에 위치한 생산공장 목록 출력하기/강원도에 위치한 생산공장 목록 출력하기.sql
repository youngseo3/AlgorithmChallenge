-- 코드를 입력하세요
SELECT F.FACTORY_ID, F.FACTORY_NAME, F.ADDRESS
FROM FOOD_FACTORY AS F
WHERE ADDRESS
LIKE '강원도%';