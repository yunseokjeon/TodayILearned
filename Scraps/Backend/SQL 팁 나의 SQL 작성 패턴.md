SQL 팁 : 나의 SQL 작성 패턴

- 항상 CTE를 사용할 것
- CTE를 가능한 작게 만들고, 한가지 용도로 유지할 것
- Don’t repeat yourself (DRY)
ㅤ→ 한 쿼리에서 같은 조인을 하고 있다면 CTE로 추상화 할 것
ㅤ→ 여러 쿼리에서 같은 조인을 하고 있다면, View로 추상화 할 것
- 종속성을 길게 연결하지 말 것
- 조인 하기 전에 데이터를 줄일 것
- 필요한 컬럼만 선택할 것
- Expect the unexpected : NULL, 중복 행, 임의 값까지 실제 데이터는 지저분함
- Left Join으로 시작할 것 : 실제 데이터는 난장판이기 때문에, Inner Join으로는 제외되는 중요 데이터가 있을 수 있음

https://news.hada.io/topic?id=5896
