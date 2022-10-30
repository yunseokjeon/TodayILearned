# JPQL INNER JOIN

회원과 팀을 내부 조인해서 팀A에 소속된 회원을 조회하는 JPQL

```SQL
SELECT m
FROM Member m INNER JOIN m.team t
WHERE t.name = :teamName
```

생성된 내부 조인 SQL

```SQL
SELECT
    M.ID AS ID,
    M.AGE AS AGE,
    M.TEAM_ID AS TEAM_ID,
    M.NAME AS NAME
FROM
    MEMBER M INNER JOIN TEAM T ON M.TEAM_ID = T.ID
WHERE
    T.NAME = ?
```

# JPQL FETCH JOIN

페치 조인을 사용해서 회원 엔티티를 조회하면서 연관된 팀 엔티티도 함께 조회하는 JPQL

```SQL
SELECT m
FROM Member JOIN FETCH m.team
```

실행된 SQL

```SQL
SELECT
    M.*, T.*
FROM MEMBER M
INNER JOIN TEAM T ON M.TEAM = T.ID
```

엔티티 페치 조인 JPQL에서 select m으로 회원 엔티티만 선택했는데 실해된 SQL을 보면 SELECT M.\*, T.\* 로 회원과 연관된 팀도 함께 조회된 것을 확인할 수 있다.

# N + 1 문제

```Java
@Entity
public class Member{
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<Order>();
    
    ...
}
```

```Java
@Entity
@Table(name = "ORDERS")
public class Order{
    
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    ...
}
```

## 즉시 로딩과 N + 1

```Java
List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
```

다음과 같은 SQL이 실행된다.

```SQL
SELECT * FROM MEMBER
```

SQL의 실행 결과로 먼저 회원 엔티티를 애플리케이션에 로딩한다. 그런데 회원 엔티티와 연관된 주문 컬렉션이 즉시 로딩으로 설정되어 있으므로 JPA는 주문 컬렉션을 즉시 로딩하려고 다음 SQL을 추가로 실행한다.

```SQL
SELECT * FROM ORDERS WHERE MEMBER_ID=?
```

조회된 회원이 5명이라면?

```SQL
SELECT * FROM MEMBER
SELECT * FROM ORDERS WHERE MEMBER_ID=1
SELECT * FROM ORDERS WHERE MEMBER_ID=2
SELECT * FROM ORDERS WHERE MEMBER_ID=3
SELECT * FROM ORDERS WHERE MEMBER_ID=4
SELECT * FROM ORDERS WHERE MEMBER_ID=5
```

조회한 각각의 회원 엔티티와 연관된 주문 컬렉션을 즉시 조회하려고 총 5번의 SQL을 추가로 실행한다. 이처럼 처음 실행한 SQL의 수만큼 추가로 SQL을 실행하는 것을 N + 1 문제라 한다.

## 지연 로딩과 N + 1

지연 로딩으로 설명하면 JPQL에서는 N + 1 문제가 발생하지 않는다.

문제는 다음처럼 모든 회원에 대해 연관된 주문 컬렉션을 사용할 때 발생한다.

```Java
for(Member member : members){
    member.getOrders().size();
}
```

회원이 5명이면 회원에 따른 주문도 5번 조회된다.

## FETCH JOIN을 통한 해결

페치 조인은 SQL 조인을 사용해서 연관된 엔티티를 함께 조회하므로 N + 1 문제가 발생하지 않는다.

페치 조인을 사용하는 JPQL

```SQL
select m from Member m join fetch m.orders
```

실행된 SQL

```SQL
SELECT M.*, O.* FROM MEMBER M 
INNER JOIN ORDERS O ON M.ID = O.MEMBER_ID
```

