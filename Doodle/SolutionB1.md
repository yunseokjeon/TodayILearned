
```SQL
/*
https://leetcode.com/problems/second-highest-salary/

176. Second Highest Salary

Input: 
Employee table:
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
Output: 
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
*/

# 문제
SELECT _____(
    (SELECT _____
FROM EMPLOYEE
_____
_____ _____),
    NULL
) AS SecondHighestSalary;

# 솔루션
SELECT IFNULL(
    (SELECT DISTINCT Salary
FROM EMPLOYEE
ORDER BY SALARY DESC
LIMIT 1 OFFSET 1),
    NULL
) AS SecondHighestSalary;
```

```SQL
/*
https://leetcode.com/problems/nth-highest-salary

177. Nth Highest Salary

Input: 
Employee table:
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2
Output: 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
*/

# 문제
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  _____
  RETURN (
      # Write your MySQL query statement below.
      SELECT Salary
      FROM Employee
      ______
      ______
      _____ _____
      
  );
END;

# 솔루션
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  SET N=N-1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT Salary
      FROM Employee
      GROUP BY Salary
      ORDER BY Salary DESC
      LIMIT 1 OFFSET N
      
  );
END;
```

```SQL
/*
https://leetcode.com/problems/rank-scores/

178. Rank Scores

Input: 
Scores table:
+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
Output: 
+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
*/

# 문제
SELECT SCORE, _____ OVER (_____) AS `Rank` FROM Scores;

# 솔루션
SELECT SCORE, DENSE_RANK() OVER (ORDER BY Score DESC) AS `Rank` FROM Scores;
```

```SQL
/*
https://leetcode.com/problems/consecutive-numbers/

180. Consecutive Numbers

Input: 
Logs table:
+----+-----+
| Id | Num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
Output: 
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
Explanation: 1 is the only number that appears consecutively for at least three times.
*/

# 문제
SELECT _____ AS ConsecutiveNums
FROM 
    LOGS L1,
    LOGS L2,
    LOGS L3
WHERE
    _____
    AND _____
    AND _____
    AND _____

# 솔루션
SELECT DISTINCT L1.NUM AS ConsecutiveNums
FROM 
    LOGS L1,
    LOGS L2,
    LOGS L3
WHERE
    L1.ID = L2.ID-1
    AND L2.ID = L3.ID-1
    AND L1.NUM = L2.NUM
    AND L2.NUM = L3.NUM
```

```SQL
/*
https://leetcode.com/problems/department-highest-salary/

184. Department Highest Salary

Input: 
Employee table:
+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
Department table:
+----+-------+
| Id | Name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
Output: 
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
Explanation: Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
*/

# 문제
SELECT _____ AS 'Department',
        _____ AS 'Employee', 
        Salary
FROM Employee
JOIN Department ON Employee.DepartmentId = Department.Id
WHERE (_____, _____) IN
(
    SELECT _____, _____
    FROM Employee
    _____
);

# 솔루션
SELECT Department.Name AS 'Department',
        Employee.Name AS 'Employee', 
        Salary
FROM Employee
JOIN Department ON Employee.DepartmentId = Department.Id
WHERE (Employee.DepartmentId, Salary) IN
(
    SELECT DepartmentId, MAX(Salary)
    FROM Employee
    GROUP BY DepartmentId
);
```

```SQL
/*
https://leetcode.com/problems/game-play-analysis-iii/

534. Game Play Analysis III

Input: 
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 1         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
Output: 
+-----------+------------+---------------------+
| player_id | event_date | games_played_so_far |
+-----------+------------+---------------------+
| 1         | 2016-03-01 | 5                   |
| 1         | 2016-05-02 | 11                  |
| 1         | 2017-06-25 | 12                  |
| 3         | 2016-03-02 | 0                   |
| 3         | 2018-07-03 | 5                   |
+-----------+------------+---------------------+
Explanation: 
For the player with id 1, 5 + 6 = 11 games played by 2016-05-02, and 5 + 6 + 1 = 12 games played by 2017-06-25.
For the player with id 3, 0 + 5 = 5 games played by 2018-07-03.
Note that for each player we only care about the days when the player logged in.
*/

# 문제
select a1.player_id, a1.event_date, _____ as games_played_so_far
from Activity a1 join Activity a2 
on _____ and _____
_____

# 솔루션
select a1.player_id, a1.event_date, sum(a2.games_played) as games_played_so_far
from Activity a1 join Activity a2 
on a1.event_date >= a2.event_date and a1.player_id = a2.player_id
group by a1.player_id, a1.event_date;
```

```SQL
/*
https://leetcode.com/problems/game-play-analysis-iv/

550. Game Play Analysis IV

Input: 
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
Output: 
+-----------+
| fraction  |
+-----------+
| 0.33      |
+-----------+
Explanation: 
Only the player with id 1 logged back in after the first day he had logged in so the answer is 1/3 = 0.33
*/

# 문제
select _____( _____(case when _____ then 1 else 0 end)
             / _____, 2) 
             as fraction
from (select player_id, _____ as min_date from activity _____) as temp
join activity a
on temp.player_id = a.player_id

# 솔루션
select round( sum(case when temp.min_date+1 = a.event_date then 1 else 0 end)
             / count(distinct temp.player_id), 2) 
             as fraction
from (select player_id, min(event_date) as min_date from activity group by player_id) as temp
join activity a
on temp.player_id = a.player_id
```

```SQL
/*
https://leetcode.com/problems/managers-with-at-least-5-direct-reports/

570. Managers with at Least 5 Direct Reports

Employee
+------+----------+-----------+----------+
|Id    |Name 	  |Department |ManagerId |
+------+----------+-----------+----------+
|101   |John 	  |A 	      |null      |
|102   |Dan 	  |A 	      |101       |
|103   |James 	  |A 	      |101       |
|104   |Amy 	  |A 	      |101       |
|105   |Anne 	  |A 	      |101       |
|106   |Ron 	  |B 	      |101       |
+------+----------+-----------+----------+

+-------+
| Name  |
+-------+
| John  |
+-------+
*/

# 문제
SELECT
    Name
FROM
    Employee AS t1 JOIN
    (SELECT
        ManagerId
    FROM
        Employee
    _____
    _____) AS t2
    ON _____

# 솔루션
SELECT
    Name
FROM
    Employee AS t1 JOIN
    (SELECT
        ManagerId
    FROM
        Employee
    GROUP BY ManagerId
    HAVING COUNT(ManagerId) >= 5) AS t2
    ON t1.Id = t2.ManagerId;

```

```SQL
/*
https://leetcode.com/problems/winning-candidate/

574. Winning Candidate

Table: Candidate

+-----+---------+
| id  | Name    |
+-----+---------+
| 1   | A       |
| 2   | B       |
| 3   | C       |
| 4   | D       |
| 5   | E       |
+-----+---------+  

Table: Vote

+-----+--------------+
| id  | CandidateId  |
+-----+--------------+
| 1   |     2        |
| 2   |     4        |
| 3   |     3        |
| 4   |     2        |
| 5   |     5        |
+-----+--------------+
id is the auto-increment primary key,
CandidateId is the id appeared in Candidate table.

+------+
| Name |
+------+
| B    |
+------+
*/

# 문제
SELECT name AS 'Name'
FROM 
    Candidate
JOIN
    (
        SELECT CandidateId
        FROM Vote
        _____
        _____
        _____
    ) AS winner
WHERE
    Candidate.id = winner.CandidateId;

# 솔루션
SELECT name AS 'Name'
FROM 
    Candidate
JOIN
    (
        SELECT CandidateId
        FROM Vote
        GROUP BY CandidateId
        ORDER BY COUNT(*) DESC
        LIMIT 1
    ) AS winner
WHERE
    Candidate.id = winner.CandidateId;

```

```SQL
/*
https://leetcode.com/problems/get-highest-answer-rate-question/

578. Get Highest Answer Rate Question

Input:
+------+-----------+--------------+------------+-----------+------------+
| id   | action    | question_id  | answer_id  | q_num     | timestamp  |
+------+-----------+--------------+------------+-----------+------------+
| 5    | show      | 285          | null       | 1         | 123        |
| 5    | answer    | 285          | 124124     | 1         | 124        |
| 5    | show      | 369          | null       | 2         | 125        |
| 5    | skip      | 369          | null       | 2         | 126        |
+------+-----------+--------------+------------+-----------+------------+
Output:
+-------------+
| survey_log  |
+-------------+
|    285      |
+-------------+
Explanation:
question 285 has answer rate 1/1, while question 369 has 0/1 answer rate, so output 285.
*/

# 문제
SELECT _____ AS survey_log
FROM survey_log
GROUP BY _____
ORDER BY _____/_____ DESC
LIMIT 1;

# 솔루션
SELECT question_id AS survey_log
FROM survey_log
GROUP BY question_id
ORDER BY SUM(IF(action='answer',1,0))/SUM(IF(action='show',1,0)) DESC
LIMIT 1;
```

```SQL
/*
https://leetcode.com/problems/count-student-number-in-departments/

580. Count Student Number in Departments

Here is an example input:

student table:
| student_id | student_name | gender | dept_id |
|------------|--------------|--------|---------|
| 1          | Jack         | M      | 1       |
| 2          | Jane         | F      | 1       |
| 3          | Mark         | M      | 2       |

department table:
| dept_id | dept_name   |
|---------|-------------|
| 1       | Engineering |
| 2       | Science     |
| 3       | Law         |

The Output should be:
| dept_name   | student_number |
|-------------|----------------|
| Engineering | 2              |
| Science     | 1              |
| Law         | 0              |
*/

# 문제
SELECT
    dept_name, _____ AS student_number
FROM
    department
        LEFT JOIN
    student ON department.dept_id = student.dept_id
_____
_____

# 솔루션
SELECT
    dept_name, COUNT(student_id) AS student_number
FROM
    department
        LEFT JOIN
    student ON department.dept_id = student.dept_id
GROUP BY department.dept_name
ORDER BY student_number DESC;
```

```SQL
/*
https://leetcode.com/problems/investments-in-2016/

585. Investments in 2016

Sample Input

| PID | TIV_2015 | TIV_2016 | LAT | LON |
|-----|----------|----------|-----|-----|
| 1   | 10       | 5        | 10  | 10  |
| 2   | 20       | 20       | 20  | 20  |
| 3   | 10       | 30       | 20  | 20  |
| 4   | 10       | 40       | 40  | 40  |

Sample Output

| TIV_2016 |
|----------|
| 45.00    |

Explanation

The first record in the table, like the last record, meets both of the two criteria.
The TIV_2015 value '10' is as the same as the third and forth record, and its location unique.

The second record does not meet any of the two criteria. Its TIV_2015 is not like any other policyholders.

And its location is the same with the third record, which makes the third record fail, too.

So, the result is the sum of TIV_2016 of the first and last record, which is 45.
*/

# 문제
SELECT
    _____ AS TIV_2016
FROM
    insurance
WHERE
    insurance.TIV_2015 IN
    (
      SELECT
        TIV_2015
      FROM
        insurance
      _____
      _____
    )
    AND _____ IN
    (
      SELECT
        _____
      FROM
        insurance
      _____
      _____
    );

# 솔루션
SELECT
    SUM(insurance.TIV_2016) AS TIV_2016
FROM
    insurance
WHERE
    insurance.TIV_2015 IN
    (
      SELECT
        TIV_2015
      FROM
        insurance
      GROUP BY TIV_2015
      HAVING COUNT(*) > 1
    )
    AND CONCAT(LAT, LON) IN
    (
      SELECT
        CONCAT(LAT, LON)
      FROM
        insurance
      GROUP BY LAT , LON
      HAVING COUNT(*) = 1
    );
```

```SQL
/*
https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/

602. Friend Requests II: Who Has the Most Friends

Table request_accepted

+--------------+-------------+------------+
| requester_id | accepter_id | accept_date|
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
+--------------+-------------+------------+
This table holds the data of friend acceptance, while requester_id and accepter_id both are the id of a person.

Result table:
+------+------+
| id   | num  |
|------|------|
| 3    | 3    |
+------+------+
The person with id '3' is a friend of people '1', '2' and '4', so he has 3 friends in total, which is the most number than any others.
*/

# 문제
select _____, _____ from 
(
      (select _____ _____ from request_accepted) 
      _____ 
      (select _____ _____ from request_accepted)
) tb 
_____ 
_____ 
_____

# 솔루션
select id, count(*) num from 
(
      (select requester_id id from request_accepted) 
      union all 
      (select accepter_id id from request_accepted)
) tb 
group by id 
order by num desc 
limit 1;
```

```SQL
/*
https://leetcode.com/problems/tree-node/

608. Tree Node

+----+------+
| id | p_id |
+----+------+
| 1  | null |
| 2  | 1    |
| 3  | 1    |
| 4  | 2    |
| 5  | 2    |
+----+------+

+----+------+
| id | Type |
+----+------+
| 1  | Root |
| 2  | Inner|
| 3  | Leaf |
| 4  | Leaf |
| 5  | Leaf |
+----+------+

			  1
			/   \
          2       3
        /   \
      4       5
*/

# 문제
SELECT
    id AS `Id`,
    CASE
        WHEN tree.id = _____
          THEN 'Root'
        WHEN tree.id IN _____
          THEN 'Inner'
        ELSE 'Leaf'
    END AS Type
FROM
    tree
ORDER BY `Id`;

# 솔루션
SELECT
    id AS `Id`,
    CASE
        WHEN tree.id = (SELECT atree.id FROM tree atree WHERE atree.p_id IS NULL)
          THEN 'Root'
        WHEN tree.id IN (SELECT atree.p_id FROM tree atree)
          THEN 'Inner'
        ELSE 'Leaf'
    END AS Type
FROM
    tree
ORDER BY `Id`;
```

```SQL
/*
https://leetcode.com/problems/shortest-distance-in-a-plane/

612. Shortest Distance in a Plane

| x  | y  |
|----|----|
| -1 | -1 |
| 0  | 0  |
| -1 | -2 |

| shortest |
|----------|
| 1.00     |
*/

# 문제
SELECT
    _____(_____( _____((_____(p1.x - p2.x, 2) + _____(p1.y - p2.y, 2))) ), 2) AS shortest
FROM
    point_2d p1
        JOIN
    point_2d p2 ON _____ OR _____;

# 솔루션
SELECT
    ROUND(SQRT(MIN((POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2)))), 2) AS shortest
FROM
    point_2d p1
        JOIN
    point_2d p2 ON p1.x != p2.x OR p1.y != p2.y;
```

```SQL
/*
https://leetcode.com/problems/second-degree-follower/

614. Second Degree Follower

For example:
+-------------+------------+
| followee    | follower   |
+-------------+------------+
|     A       |     B      |
|     B       |     C      |
|     B       |     D      |
|     D       |     E      |
+-------------+------------+

should output:
+-------------+------------+
| follower    | num        |
+-------------+------------+
|     B       |  2         |
|     D       |  1         |
+-------------+------------+

Explaination:
Both B and D exist in the follower list, when as a followee, B's follower is C and D, and D's follower is E. A does not exist in follower list.
*/

# 문제
Select f1.follower, count(_____ _____) as num
from follow f1
inner join follow f2 on f1._____ = f2._____
Group by _____


# 솔루션
Select f1.follower, count(distinct f2.follower) as num
from follow f1
inner join follow f2 on f1.follower = f2.followee
Group by f1.follower
```

```SQL
/*
https://leetcode.com/problems/exchange-seats/solution/

626. Exchange Seats

+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Abbot   |
|    2    | Doris   |
|    3    | Emerson |
|    4    | Green   |
|    5    | Jeames  |
+---------+---------+
For the sample input, the output is:

+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Doris   |
|    2    | Abbot   |
|    3    | Green   |
|    4    | Emerson |
|    5    | Jeames  |
+---------+---------+
*/

# 문제
SELECT
    (CASE
        WHEN _____ != 0 AND _____ THEN id + 1
        WHEN _____ != 0 AND _____ THEN id
        ELSE id - 1
    END) AS id,
    student
FROM
    seat,
    (SELECT
        _____ AS counts
    FROM
        seat) AS seat_counts
ORDER BY _____


# 솔루션
SELECT
    (CASE
        WHEN MOD(id, 2) != 0 AND counts != id THEN id + 1
        WHEN MOD(id, 2) != 0 AND counts = id THEN id
        ELSE id - 1
    END) AS id,
    student
FROM
    seat,
    (SELECT
        COUNT(*) AS counts
    FROM
        seat) AS seat_counts
ORDER BY id;
```

```SQL
/*
https://leetcode.com/problems/customers-who-bought-all-products/

1045. Customers Who Bought All Products

Customer table:
+-------------+-------------+
| customer_id | product_key |
+-------------+-------------+
| 1           | 5           |
| 2           | 6           |
| 3           | 5           |
| 3           | 6           |
| 1           | 6           |
+-------------+-------------+

Product table:
+-------------+
| product_key |
+-------------+
| 5           |
| 6           |
+-------------+

Result table:
+-------------+
| customer_id |
+-------------+
| 1           |
| 3           |
+-------------+
The customers who bought all the products (5 and 6) are customers with id 1 and 3.
*/

# 문제
select customer_id
from customer 
group by customer_id
having count(_____ _____)=(select count(_____ _____) from product)

# 솔루션
select customer_id
from customer 
group by customer_id
having count(distinct product_key)=(select count(distinct product_key) from product)
```

```SQL
/*
https://leetcode.com/problems/product-sales-analysis-iii/

1070. Product Sales Analysis III

Sales table:
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+ 
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+

Product table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |
+------------+--------------+

Result table:
+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+ 
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |
+------------+------------+----------+-------+
*/

# 문제
SELECT product_id, year AS first_year, quantity, price
FROM Sales
WHERE (_____, _____) IN (
SELECT _____, _____ as year
FROM Sales
GROUP BY _____);

# 솔루션
SELECT product_id, year AS first_year, quantity, price
FROM Sales
WHERE (product_id, year) IN (
SELECT product_id, MIN(year) as year
FROM Sales
GROUP BY product_id);
```

```SQL
/*
https://leetcode.com/problems/project-employees-iii/

1077. Project Employees III

Project table:
+-------------+-------------+
| project_id  | employee_id |
+-------------+-------------+
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |
+-------------+-------------+

Employee table:
+-------------+--------+------------------+
| employee_id | name   | experience_years |
+-------------+--------+------------------+
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 3                |
| 4           | Doe    | 2                |
+-------------+--------+------------------+

Result table:
+-------------+---------------+
| project_id  | employee_id   |
+-------------+---------------+
| 1           | 1             |
| 1           | 3             |
| 2           | 1             |
+-------------+---------------+
Both employees with id 1 and 3 have the most experience among the employees of the first project. For the second project, the employee with id 1 has the most experience.
*/

# 문제
SELECT p1.project_id, p1.employee_id
FROM Project p1 JOIN Employee e1 ON p1._____ = e1._____
    JOIN (
        SELECT _____ AS project_id, MAX(_____) AS m
        FROM Project p2 JOIN Employee e2 ON p2.employee_id = e2.employee_id
        GROUP BY p2.project_id
        ) AS t ON p1._____ = t._____
WHERE e1._____ = t._____

# 솔루션
SELECT p1.project_id, p1.employee_id
FROM Project p1 JOIN Employee e1 ON p1.employee_id = e1.employee_id
    JOIN (
        SELECT p2.project_id AS project_id, MAX(e2.experience_years) AS m
        FROM Project p2 JOIN Employee e2 ON p2.employee_id = e2.employee_id
        GROUP BY p2.project_id
        ) AS t ON p1.project_id = t.project_id
WHERE e1.experience_years = t.m
```

```SQL
/*
https://leetcode.com/problems/unpopular-books/

1098. Unpopular Books

Books table:
+---------+--------------------+----------------+
| book_id | name               | available_from |
+---------+--------------------+----------------+
| 1       | "Kalila And Demna" | 2010-01-01     |
| 2       | "28 Letters"       | 2012-05-12     |
| 3       | "The Hobbit"       | 2019-06-10     |
| 4       | "13 Reasons Why"   | 2019-06-01     |
| 5       | "The Hunger Games" | 2008-09-21     |
+---------+--------------------+----------------+

Orders table:
+----------+---------+----------+---------------+
| order_id | book_id | quantity | dispatch_date |
+----------+---------+----------+---------------+
| 1        | 1       | 2        | 2018-07-26    |
| 2        | 1       | 1        | 2018-11-05    |
| 3        | 3       | 8        | 2019-06-11    |
| 4        | 4       | 6        | 2019-06-05    |
| 5        | 4       | 5        | 2019-06-20    |
| 6        | 5       | 9        | 2009-02-02    |
| 7        | 5       | 8        | 2010-04-13    |
+----------+---------+----------+---------------+

Result table:
+-----------+--------------------+
| book_id   | name               |
+-----------+--------------------+
| 1         | "Kalila And Demna" |
| 2         | "28 Letters"       |
| 5         | "The Hunger Games" |
+-----------+--------------------+
*/

# 문제
select b.book_id, b.name from
(select * from books where _____ < '2019-05-23') b
_____ join
(select * from Orders where _____ > '2018-06-23') o
on b._____ = o._____
group by b.book_id, b.name
having sum(o._____) is null or sum(o._____) <10;

# 솔루션
select b.book_id, b.name from
(select * from books where available_from < '2019-05-23') b
left join
(select * from Orders where dispatch_date > '2018-06-23') o
on b.book_id = o.book_id
group by b.book_id, b.name
having sum(o.quantity) is null or sum(o.quantity) <10;
```

```SQL
/*
https://leetcode.com/problems/new-users-daily-count/

1107. New Users Daily Count

Traffic table:
+---------+----------+---------------+
| user_id | activity | activity_date |
+---------+----------+---------------+
| 1       | login    | 2019-05-01    |
| 1       | homepage | 2019-05-01    |
| 1       | logout   | 2019-05-01    |
| 2       | login    | 2019-06-21    |
| 2       | logout   | 2019-06-21    |
| 3       | login    | 2019-01-01    |
| 3       | jobs     | 2019-01-01    |
| 3       | logout   | 2019-01-01    |
| 4       | login    | 2019-06-21    |
| 4       | groups   | 2019-06-21    |
| 4       | logout   | 2019-06-21    |
| 5       | login    | 2019-03-01    |
| 5       | logout   | 2019-03-01    |
| 5       | login    | 2019-06-21    |
| 5       | logout   | 2019-06-21    |
+---------+----------+---------------+

Result table:
+------------+-------------+
| login_date | user_count  |
+------------+-------------+
| 2019-05-01 | 1           |
| 2019-06-21 | 2           |
+------------+-------------+
Note that we only care about dates with non zero user count.
The user with id 5 first logged in on 2019-03-01 so he's not counted on 2019-06-21.
*/

# 문제
select login_date, _____ user_count
from
(select user_id, _____(activity_date) login_date
from traffic
where activity = 'login'
group by _____) a
where _____ between _____('2019-06-30', interval -90 day) and '2019-06-30'
group by login_date

# 솔루션
select login_date, count(1) user_count
from
(select user_id, min(activity_date) login_date
from traffic
where activity = 'login'
group by user_id) a
where login_date between date_add('2019-06-30', interval -90 day) and '2019-06-30'
group by login_date
```

```SQL
/*
https://leetcode.com/problems/highest-grade-for-each-student/

1112. Highest Grade For Each Student

Enrollments table:
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 2          | 2         | 95    |
| 2          | 3         | 95    |
| 1          | 1         | 90    |
| 1          | 2         | 99    |
| 3          | 1         | 80    |
| 3          | 2         | 75    |
| 3          | 3         | 82    |
+------------+-----------+-------+

Result table:
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |
+------------+-----------+-------+
*/

# 문제
SELECT student_id, _____(course_id) AS course_id, grade
FROM Enrollments
WHERE (student_id, grade) IN
(SELECT student_id, _____(grade)
FROM Enrollments
GROUP BY student_id)
GROUP BY student_id, grade
ORDER BY student_id

# 솔루션
SELECT student_id, MIN(course_id) AS course_id, grade
FROM Enrollments
WHERE (student_id, grade) IN
(SELECT student_id, MAX(grade)
FROM Enrollments
GROUP BY student_id)
GROUP BY student_id, grade
ORDER BY student_id
```

```SQL
/*
https://leetcode.com/problems/active-businesses/

1126. Active Businesses

Events table:
+-------------+------------+------------+
| business_id | event_type | occurences |
+-------------+------------+------------+
| 1           | reviews    | 7          |
| 3           | reviews    | 3          |
| 1           | ads        | 11         |
| 2           | ads        | 7          |
| 3           | ads        | 6          |
| 1           | page views | 3          |
| 2           | page views | 12         |
+-------------+------------+------------+

Result table:
+-------------+
| business_id |
+-------------+
| 1           |
+-------------+ 
The average activity for each event can be calculated as follows:
- 'reviews': (7+3)/2 = 5
- 'ads': (11+7+6)/3 = 8
- 'page views': (3+12)/2 = 7.5
The business with id=1 has 7 'reviews' events (more than 5) and 11 'ads' events (more than 8), so it is an active business.
*/

# 문제
select business_id                                      
from
(select event_type, _____(occurences) as ave_occurences   
 from events as e1
 group by _____
) as temp1
join events as e2 on temp1.event_type = e2.event_type   
where e2._____ > temp1._____             
group by business_id
having count(distinct _____) > 1    

# 솔루션
select business_id                                      
# Finally, select 'business_id'
from
(select event_type, avg(occurences) as ave_occurences   
# First, take the average of 'occurences' group by 'event_type'
 from events as e1
 group by event_type
) as temp1
join events as e2 on temp1.event_type = e2.event_type   
# Second, join Events table on 'event_type'
where e2.occurences > temp1.ave_occurences              
# Third, the 'occurences' should be greater than the average of 'occurences'
group by business_id
having count(distinct temp1.event_type) > 1             
# (More than one event type with 'occurences' greater than 1)
```

```SQL
/*
https://leetcode.com/problems/reported-posts-ii/

1132. Reported Posts II

Actions table:
+---------+---------+-------------+--------+--------+
| user_id | post_id | action_date | action | extra  |
+---------+---------+-------------+--------+--------+
| 1       | 1       | 2019-07-01  | view   | null   |
| 1       | 1       | 2019-07-01  | like   | null   |
| 1       | 1       | 2019-07-01  | share  | null   |
| 2       | 2       | 2019-07-04  | view   | null   |
| 2       | 2       | 2019-07-04  | report | spam   |
| 3       | 4       | 2019-07-04  | view   | null   |
| 3       | 4       | 2019-07-04  | report | spam   |
| 4       | 3       | 2019-07-02  | view   | null   |
| 4       | 3       | 2019-07-02  | report | spam   |
| 5       | 2       | 2019-07-03  | view   | null   |
| 5       | 2       | 2019-07-03  | report | racism |
| 5       | 5       | 2019-07-03  | view   | null   |
| 5       | 5       | 2019-07-03  | report | racism |
+---------+---------+-------------+--------+--------+

Removals table:
+---------+-------------+
| post_id | remove_date |
+---------+-------------+
| 2       | 2019-07-20  |
| 3       | 2019-07-18  |
+---------+-------------+

Result table:
+-----------------------+
| average_daily_percent |
+-----------------------+
| 75.00                 |
+-----------------------+
The percentage for 2019-07-04 is 50% because only one post of two spam reported posts was removed.
The percentage for 2019-07-02 is 100% because one post was reported as spam and it was removed.
The other days had no spam reports so the average is (50 + 100) / 2 = 75%
Note that the output is only one number and that we do not care about the remove dates.
*/

# 문제
SELECT ROUND(_____(cnt), 2) AS average_daily_percent FROM
(SELECT (COUNT(DISTINCT _____.post_id)/ COUNT(DISTINCT _____.post_id))*100  AS cnt
FROM Actions a
LEFT JOIN Removals r
ON a._____ = r._____
WHERE extra='_____'
GROUP BY action_date) tmp

# 솔루션
SELECT ROUND(AVG(cnt), 2) AS average_daily_percent FROM
(SELECT (COUNT(DISTINCT r.post_id)/ COUNT(DISTINCT a.post_id))*100  AS cnt
FROM Actions a
LEFT JOIN Removals r
ON a.post_id = r.post_id
WHERE extra='spam'
GROUP BY action_date) tmp
```

```SQL
/*
https://leetcode.com/problems/article-views-ii/

1149. Article Views II

Views table:
+------------+-----------+-----------+------------+
| article_id | author_id | viewer_id | view_date  |
+------------+-----------+-----------+------------+
| 1          | 3         | 5         | 2019-08-01 |
| 3          | 4         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |
+------------+-----------+-----------+------------+

Result table:
+------+
| id   |
+------+
| 5    |
| 6    |
+------+
*/

# 문제
SELECT DISTINCT viewer_id AS id
FROM Views
GROUP BY _____, _____
HAVING COUNT(DISTINCT _____) > 1
ORDER BY 1

# 솔루션
SELECT DISTINCT viewer_id AS id
FROM Views
GROUP BY viewer_id, view_date
HAVING COUNT(DISTINCT article_id) > 1
ORDER BY 1
```

```SQL
/*
https://leetcode.com/problems/market-analysis-i/

1158. Market Analysis I

Users table:
+---------+------------+----------------+
| user_id | join_date  | favorite_brand |
+---------+------------+----------------+
| 1       | 2018-01-01 | Lenovo         |
| 2       | 2018-02-09 | Samsung        |
| 3       | 2018-01-19 | LG             |
| 4       | 2018-05-21 | HP             |
+---------+------------+----------------+

Orders table:
+----------+------------+---------+----------+-----------+
| order_id | order_date | item_id | buyer_id | seller_id |
+----------+------------+---------+----------+-----------+
| 1        | 2019-08-01 | 4       | 1        | 2         |
| 2        | 2018-08-02 | 2       | 1        | 3         |
| 3        | 2019-08-03 | 3       | 2        | 3         |
| 4        | 2018-08-04 | 1       | 4        | 2         |
| 5        | 2018-08-04 | 1       | 3        | 4         |
| 6        | 2019-08-05 | 2       | 2        | 4         |
+----------+------------+---------+----------+-----------+

Items table:
+---------+------------+
| item_id | item_brand |
+---------+------------+
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |
+---------+------------+

Result table:
+-----------+------------+----------------+
| buyer_id  | join_date  | orders_in_2019 |
+-----------+------------+----------------+
| 1         | 2018-01-01 | 1              |
| 2         | 2018-02-09 | 2              |
| 3         | 2018-01-19 | 0              |
| 4         | 2018-05-21 | 0              |
+-----------+------------+----------------+
*/

# 문제
SELECT user_id AS buyer_id, join_date, IFNULL(_____(o.order_id),0) AS orders_in_2019
FROM Users u
LEFT JOIN Orders o ON u._____ = o._____ AND _____(order_date)='2019'
GROUP BY user_id
ORDER BY user_id

# 솔루션
SELECT user_id AS buyer_id, join_date, IFNULL(COUNT(o.order_id),0) AS orders_in_2019
FROM Users u
LEFT JOIN Orders o ON u.user_id = o.buyer_id AND YEAR(order_date)='2019'
GROUP BY user_id
ORDER BY user_id
```

