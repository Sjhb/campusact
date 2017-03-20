##数据库名称：campusact 编码：utf-8
### 学生表（student）

| 学号   | 头像     | 姓名     | 密码         | 性别    | 电话      | 专业      | 班级      | 学院        | 角色     |
| ---- | ------ | ------ | ---------- | ----- | ------- | ------- | ------- | --------- | ------ |
| s-id | s-icon | s-name | s-password | s-sex | s-phone | s-major | s-class | s-college | p-role |
###组织表（organization）

| 编号   | 组织头像   | 名称     | 密码         | 电话      | 邮箱     | 详情       | 地址        | 角色     |
| ---- | ------ | ------ | ---------- | ------- | ------ | -------- | --------- | ------ |
| o-id | o-icon | o-name | o-password | o-phone | o-mail | o-detail | o-address | p-role |
###管理员（admin）

| 工号   | 头像     | 姓名     | 密码         | 角色     |
| ---- | ------ | ------ | ---------- | ------ |
| a-id | a-icon | a-name | a-password | p-role |
###活动表（activities）

| 活动编号  | 活动名称    | 组织者  | 报名开始时间      | 报名结束时间         | 开始时间     | 结束时间     | 活动介绍      | 图片       | 地址         | 赞助商        | 审核状态     | 参与学生      |
| ----- | ------- | ---- | ----------- | -------------- | -------- | -------- | --------- | -------- | ---------- | ---------- | -------- | --------- |
| ac-id | ac-name | o-id | ac-signtime | ac-endsigntime | ac-stime | ac-etime | ac-detail | ac-photo | ac-address | ac-sponsor | ac-state | ac-engage |



### 权限表（power）

| 角色     | 详情       |
| ------ | -------- |
| p-role | p-detail |



### 状态表（state）

| 状态码      | 详情        |
| -------- | --------- |
| st-state | st-detail |