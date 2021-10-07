
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