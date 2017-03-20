##数据库名称：campusact 编码：utf-8
### 学生表（student）

| 学号   | 头像     | 姓名     | 密码         | 性别    | 电话      | 专业      | 班级      | 学院        | 角色     |
| ---- | ------ | ------ | ---------- | ----- | ------- | ------- | ------- | --------- | ------ |
| id | icon | name | password | sex | phone | major | class | college | role |
|BIGINT|VARCHAR(25)|VARCHAR(25)|VARCHAR(20)|CHAR(2)DEFUALT '男'CHECK(SEX IN('男','女')|VARCHAR(11)|VARCHAR(15)|VARCHAR(10)|VARCHAR(15)|INT|
###组织表（organization）

| 编号   | 组织头像   | 名称     | 密码         | 电话      | 邮箱     | 详情       | 地址        | 角色     |
| ---- | ------ | ------ | ---------- | ------- | ------ | -------- | --------- | ------ |
| id | icon | name | password | phone | mail | detail | address | role |
|BIGINT|VARCHAR(25)|VARCAHR(20)|VARCHAR(20)|VARCHAR(200)|VARCHAR(20)|TEXT|VARCHAR(50)|ROLE|
###管理员（admin）

| 工号   | 头像     | 姓名     | 密码         | 角色     |
| ---- | ------ | ------ | ---------- | ------ |
| id | icon | name | password | role |
|BIGINT|VARCHAR(25)|VARCHAR(25)|VARCHAR(20)|INT|
###活动表（activity）

| 活动编号  | 活动名称    | 组织者  | 报名开始时间      | 报名结束时间         | 开始时间     | 结束时间     | 活动介绍      | 图片       | 地址         | 赞助商        | 审核状态     | 参与学生      |
| ----- | ------- | ---- | ----------- | -------------- | -------- | -------- | --------- | -------- | ---------- | ---------- | -------- | --------- |
| id | name | organizationId | signtime |endsigntime | stime | etime | detail | photo |address | sponsor | stateId | engage |
|BIGINT|VARCHAR(30)|BIGINT|DATETIME|DATETIME|DATETIME|DATETIME|TEXT|VARCHAR(200)|VARCHAR(200)|VARCHAR(200)|INT|JSON|


### 权限表（power）

| 角色     | 详情       |
| ------ | -------- |
| role | detail |
|INT|VARCHAR(20)|



### 状态表（state）

| 状态码      | 详情        |
| -------- | --------- |
| state | detail |
|INT|VARCHAR|