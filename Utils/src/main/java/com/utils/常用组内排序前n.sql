一、按name分组取val最大的值所在行的数据。 
--方法1：select a.* from tb a where val = (select max(val) from tb where name = a.name) order by a.name 
--方法2： 
select a.* from tb a where not exists(select 1 from tb where name = a.name and val > a.val) 
--方法3： 
select a.* from tb a,(select name,max(val) val from tb group by name) b where a.name = b.name and a.val = b.val order by a.name 
--方法4： 
select a.* from tb a inner join (select name , max(val) val from tb group by name) b on a.name = b.name and a.val = b.val order by a.name 
--方法5 
select a.* from tb a where 1 > (select count(*) from tb where name = a.name and val > a.val ) order by a.name 

二、按name分组取val最小的值所在行的数据。 
--方法1：select a.* from tb a where val = (select min(val) from tb where name = a.name) order by a.name 
--方法2： 
select a.* from tb a where not exists(select 1 from tb where name = a.name and val < a.val) 
--方法3： 
select a.* from tb a,(select name,min(val) val from tb group by name) b where a.name = b.name and a.val = b.val order by a.name 
--方法4： 
select a.* from tb a inner join (select name , min(val) val from tb group by name) b on a.name = b.name and a.val = b.val order by a.name 
--方法5 
select a.* from tb a where 1 > (select count(*) from tb where name = a.name and val < a.val) order by a.name 

三、按name分组取第一次出现的行所在的数据。 
select a.* from tb a where val = (select top 1 val from tb where name = a.name) order by a.name 

--四、按name分组随机取一条数据。 
select a.* from tb a where val = (select top 1 val from tb where name = a.name order by newid()) order by a.name

--五、按name分组取最小的两个(N个)val 
select a.* from tb a where 2 > (select count(*) from tb where name = a.name and val < a.val ) order by a.name,a.valselect a.* from tb a where val in (select top 2 val from tb where name=a.name order by val) order by a.name,a.val 
select a.* from tb a where exists (select count(*) from tb where name = a.name and val < a.val having Count(*) < 2) order by a.name 

--六、按name分组取最大的两个(N个)val 
select a.* from tb a where 2 > (select count(*) from tb where name = a.name and val > a.val ) order by a.name,a.val 
select a.* from tb a where val in (select top 2 val from tb where name=a.name order by val desc) order by a.name,a.val 
select a.* from tb a where exists (select count(*) from tb where name = a.name and val > a.val having Count(*) < 2) order by a.name 
