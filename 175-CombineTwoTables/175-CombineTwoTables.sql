-- Algorithm:
-- 1. Perform a LEFT JOIN between the Person and Address tables using personId as the joining key.
-- 2. Select firstName and lastName from the Person table, and city and state from the Address table.
-- 3. Since it's a LEFT JOIN, if a person doesn't have a corresponding address, city and state will be returned as NULL.
-- 4. Return the resulting table.

-- Pseudo Code:
-- SELECT p.firstName, p.lastName, a.city, a.state
-- FROM Person p
-- LEFT JOIN Address a ON p.personId = a.personId;

-- Visualization:
-- Person Table:                           Address Table:
-- +----------+----------+-----------+     +-----------+----------+---------------+----------+
-- | personId | lastName | firstName |     | addressId | personId | city          | state    |
-- +----------+----------+-----------+     +-----------+----------+---------------+----------+
-- | 1        | Wang     | Allen     |     | 1         | 2        | New York City | New York |
-- | 2        | Alice    | Bob       |     | 2         | 3        | Leetcode      | California|
-- +----------+----------+-----------+     +-----------+----------+---------------+----------+
--
-- Expected Output:
-- For personId 1 (Allen Wang): No matching address -> (Allen, Wang, NULL, NULL)
-- For personId 2 (Bob Alice): Matching address found -> (Bob, Alice, New York City, New York)

-- SQL Query:
SELECT p.firstName, p.lastName, a.city, a.state  -- Select required columns from both tables
FROM Person p                                    -- From Person table aliased as 'p'
LEFT JOIN Address a ON p.personId = a.personId;   -- Left join on personId to include persons without an address
