connect 'jdbc:derby:db/Portfolio;create=true';

create table m_emp (
    emp_no  int primary key,
    emp_nm  varchar(32),
    dept_no int, --FK
    sex_flg int
);

create table m_dept (
    dept_no int primary key,
    dept_nm varchar(32)
);

create table t_roster (
    emp_no      int, --FK
    work_flg    int,
    roster_day  varchar(32),
    time_from   varchar(32),
    time_to     varchar(32),
    holiday_flg int
);

ALTER TABLE m_emp ADD CONSTRAINT fk_dept_no
FOREIGN KEY (dept_no) REFERENCES m_dept (dept_no);
-- ON DELETE RESTRICT ON UPDATE CASCADE;
-- ON DELETE SET NULL ON UPDATE CASCADE;

commit;

