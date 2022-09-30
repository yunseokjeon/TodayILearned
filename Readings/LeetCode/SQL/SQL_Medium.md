[1] 176. Second Highest Salary

https://leetcode.com/problems/second-highest-salary/

```SQL
# Write your MySQL query statement below
select( 
    ifnull(
        (
            select distinct salary
            from Employee
            order by salary desc
            limit 1 
            offset 1
        )
        ,null)
) as SecondHighestSalary
```

[2] 177. Nth Highest Salary

https://leetcode.com/problems/nth-highest-salary/

```SQL
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    
    set N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      select distinct salary
      from Employee
      order by salary desc
      limit 1
      offset N
  );
END
```

[3] 178. Rank Scores

https://leetcode.com/problems/rank-scores/

```SQL
select score,
    dense_rank() over (order by score desc) as 'rank'
from Scores
```

[4] 180. Consecutive Numbers

https://leetcode.com/problems/consecutive-numbers/

```SQL
select distinct l1.num as 'ConsecutiveNums'
from Logs l1, Logs l2, Logs l3
where l1.id = l2.id - 1 and
        l2.id = l3.id - 1 and
        l1.num = l2.num and
        l2.num = l3.num
```

[5] 184. Department Highest Salary

https://leetcode.com/problems/department-highest-salary/

```SQL
select
    d.name as Department,
    e.name as Employee,
    salary as Salary
from Employee e join Department d
on e.departmentId = d.id
where (e.departmentId, salary) in
(
    select departmentId, max(salary)
    from Employee
    group by departmentId
)
```

