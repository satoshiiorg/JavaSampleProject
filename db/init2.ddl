-- 変更後DB

connect 'jdbc:derby:db/Portfolio2;create=true';

create table m_dept (
    dept_no         int primary key,
    dept_nm         varchar(32),
    regular_time    varchar(32), -- 始業時刻
    regular_hour    int,         -- 就業時間
    lunch_time      varchar(32), -- 休憩時刻
    lunch_minutes   int,         -- 休憩時間(分)
    holiday1        int,         -- 休日1 0:日曜 - 6:土曜
    holiday2        int          -- 同上
);

create table m_emp (
    emp_no  int primary key,
    emp_nm  varchar(32),
    dept_no int, --FK
    sex_flg int
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

