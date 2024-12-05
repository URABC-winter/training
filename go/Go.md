### Go反射

```
work1 := reflect.TypeOf(workers)
```

 返回类型为 reflect.Type  返回：确切类型

```
work2 := reflect.ValueOf(workers)  
```

返回类型为 reflect.Value  返回：值 【存在方法.Type() 返回reflect.Value类型 返回：类型；存在方法.Interface() 返回any类型 返回：值；存在.kind() 返回reflect.Kind类型：返回：数据结构类型如struct】

```
x := 2
c := reflect.ValueOf(&x).Elem()
d := c.Addr().Interface().(*int)
*d = 3
```

利用.Elem()返回值的value。调用Addr()方法，它返回一个Value，里面保存了指向变量的指针。然后是在Value上调用Interface()方法， 也就是返回一个interface{}，里面包含指向变量的指针，最后用断言强转，即可得到普通的类型指针。