insert into m_dept values
(10, 'sales'),
(21, 'dev1'),
(22, 'dev2'),
(30, 'account'),
(40, 'newbiz');

insert into m_emp values
(101, 'Smith', 10, 1),
(102, 'Alleh', 21, 0),
(103, 'Ward',  10, 0),
(104, 'Jones', 22, 1),
(105, 'Smith', 30, 1),
(106, 'Blake', 30, 0),
(107, 'Clark', 40, 1),
(108, 'Scott', 22, 1);

insert into t_roster values
(101, 1, '20140401', '0900', '1800', 0),
(101, 1, '20140402', '0930', '1900', 0),
(101, 1, '20140403', '0900', '1800', 0),
(101, 1, '20140404', '0900', '1800', 0),
(102, 1, '20140401', '0900', '2100', 0),
(102, 0, '20140402', '0900', '1800', 0),
(102, 1, '20140403', '0900', '1800', 0),
(102, 1, '20140404', '0900', '1800', 0),
(102, 1, '20140405', '1100', '1800', 1);

commit;