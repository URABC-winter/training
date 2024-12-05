不等于 <>        检测控制 is null        
正则匹配：REGEXP  ‘test’            
REGEXP ‘[123] ton’匹配：1 ton，2 ton []中为或，如果用 ‘1|2|3 ton’ 会单独匹配数字1，2，3

连接两行数据到1个字段上 ==concat()==

```
select concat(id, ' (', 字段名, ')') from 表名; 
```

分组：group by    having进行过滤
==Between==为 30-35；

```
between 30 and 35
```

==In== 为 这两值返回：

```
in (30,35)
```

==UNION进行联合查询==时，必须列一致。

```
select 字段1, 字段2, 字段3 from `表名` where id in (30,35) union select 字段1, 字段2, 字段3 from `表名` where 字段4 like '%87QA';
```

==Insert into 插入==（保证非空值有值被插入）

```
INSERT INTO `表名` (`字段1` , `字段2`, `字段3`, `字段4`) VALUES (15, 4, 'AT2Z', 2);
```

==Update利用set==

```
update `表名` set 字段1 = 5 where 字段2 = 15;
```

==Delete from ==删除

```
delete from `表名` where 字段1 = 15;
```

==Trigger触发器==

```
create trigger newq after insert on `表名` for each row insert into `表名` (字段1 , 字段2, 字段3, 字段4) values (`表名`.d_id , `表名`.b_id , `表名`.device , `表名`.location);
```
