### 查看linux占用磁盘情况：

df -h

### 查看大文件占用情况：

du -h / |grep '[0-9\.]\+G'

### 查看某目录下存在关键字文件【shell_code】：

find . -type d -name shell_code

find / -name qkm-down

### 查看运行java：

ps -ef |grep java

### 杀死进程：

kill -9 进程号