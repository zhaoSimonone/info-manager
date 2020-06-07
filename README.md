## 校园信息管理系统
作者：Yinglin Zhao

## Github认证接入

[认证接入](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)

> href="https://github.com/login/oauth/authorize?client_id=1db7187bf4bd2227f3b6&redirect_uri=http://localhost:8887/callback&scope=user&status=1"
>https://api.github.com/user?access_token=e2732197d11e9440e7d819fff86d757c8473a73f

```sql
create table info
(
    id            int auto_increment
        primary key,
    creator       int           null,
    title         varchar(50)   null,
    description   text          null,
    gmt_create    bigint        null,
    gmt_modified  bigint        null,
    comment_count int           null,
    view_count    int default 0 null,
    like_count    int default 0 null,
    tag           varchar(50)   null
);

alter table info alter column comment_count set default 0;
```

## MyBatis
导入插件
```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.4.0</version>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.20</version>
        </dependency>
    </dependencies>
</plugin>
```
执行命令
```
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

