```SQL
/*
https://leetcode.com/problems/nth-highest-salary/

Write a SQL query to get the nth highest salary from the Employee table.

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
For example, given the above Employee table, the nth highest salary where n = 2 is 200. If there is no nth highest salary, then the query should return null.

+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
*/

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      SELECT
        WNDW.SALARY
      FROM
        (
            SELECT
                SALARY,
                DENSE_RANK() OVER(ORDER BY SALARY DESC) RNK
            FROM
                EMPLOYEE
        ) WNDW
      WHERE
        WNDW.RNK = N
      LIMIT 1
  );
END
 
```