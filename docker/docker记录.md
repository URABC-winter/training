### docker服务部署

docker下的docker-compose.yaml新增需要的服务。具体涉及容器服务名，镜像名，依赖容器，以及volumes挂载映射。

```
镜像：
container_name:
  image:
  pid:
  privileged:
  environment:
  volumes:
    - 本地文件映射:程序内存储
    - 本地文件映射:程序内存储
  depends_on:
    - 依赖镜像
  links:
    - 连接容器
  ports:
    - 映射端口:端口
  deploy:
    resources:
      limits:
        memory: 512M
```

②　修改Dockerfile，修改Docker容器程序。【COPY所需依赖，ENTRYPOINT为所需调用地点】，如果导出docker镜像，直接load。(Dockerfile构建镜像脚本的文件，build依靠)

```
FORM ubuntu:latest          #指定基础镜像

COPY 依赖 目录文件          #本地文件添加到容器中
COPY 依赖 目录文件

ENTRYPOINT ["执行路径文件"] #启动指令，使其可执行化
```

docker-compose build 镜像名             进行编译
docker-compose up 镜像名                 进行启动

### Docker镜像打包脚本

docker-compose -f docker-compose.yaml.all build 服务名
docker save -o images/服务名-latest.tar 服务名:latest

### Docker镜像更包

docker load

### 备份docker镜像数据库

`docker exec -i 镜像名 /bin/bash -c 'mysqldump -uroot -p123456 --databases 数据库名' > + 存储位置` 