##目录
* [背景介绍](#背景介绍)
* [项目介绍](#项目介绍)
* [使用说明](#使用说明)
  * [获取代码](#获取代码)
  * [开发插件](#开发插件)
  * [接口设置](#接口设置)
* [其他](#其他)
<a name="背景介绍"></a>
## 背景介绍
未解决高校校园内校园文化活动的诸多问题，搭建该校园活动管理平台，并尝试加入一些情景化的东西进去。
<a name="项目介绍"></a>
## 项目介绍
使用SpringMVC、Mybatis和Angularjs搭建。<br>
前后端分离,后台只提供数据。

<a name="使用说明"></a>
## 使用说明

<a name="获取代码"></a>
### 获取代码

<a name="开发插件"></a>
### 开发插件

<a name="接口设置"></a>

### 接口设置

####公共行为

| 接口    |访问地址| 详情     |方法|接收参数|返回参数|权限|

| ------ |------ | -------- |------|------ | -------- |------ | 

| PUB_LOGIN|/user/login|登陆|post|id(String,必需),password(String,必需)|BaseModel(data=MiUserInfo)|guest|
| PUB_LOGOUT|/user/logout|注销|post|id(String,必需)|BaseModel(data=MiUserInfo)|guest|
| PUB_GET_ALL_ACTIVITY|/activity/getall|查看审核通过即将举办的活动|post|pageNum(int,必需)|BaseModel(data=List(Activity))|guest|
|STU_REGISTER|/student/register|学生注册|post|Sqlstudent类型(必须)|BaseModel(String)|guest|
|ORG_REGISTER|/organization/register|组织注册|post|Sqlorganization类型(必须)|BaseModel(String)|guest|
PUB_GET_ORGANIZED_ACTIVITY = "PUB4";//查看历史活动<br>
PUB_GET_TARGET_ACTIVITY = "PUB5";//查看个别活动<br>
PUB_GET_ORGANIZATION_INFO = "PUB6";//    查看组织的详细信息<br>
//学生：<br>
STU_ALTER_INFO = "STU1";//更改个人资料<br>
STU_ALTER_PASSWORD = "STU2";//    更改密码<br>
STU_ENGAGE_ACTIVITY = "STU3";//    参加活动<br>
STU_EXIT_ACTIVITY = "STU4";//    退出已经参加的活动<br>
STU_GET_ENGAGED_ACTIVITY = "STU5";//    查询活动参加历史<br>
STU_COMMENT = "STU6";//    评论活动<br>
STU_GET_ANOTHER_STUDENT = "STU7";//    查询参与相同活动的同学<br>
 
*这里应当设置一定权限，不一定所有同学会愿意暴露自己的联系方式<br>*
STU_WRITE_PERSONAL_WORDS = "STU8";//    编写个人心得<br>
//管理员：<br>
//    管理活动 包括：查看活动、审阅活动、打回已经通过审核的活动<br>
ADM_REVIEW_ACTIVITY = "ADM1";//审阅活动<br>
ADM_WITHDRAW_ACTIVITY = "ADM2";//打回通过的活动<br>
ADM_AGREE_ACTIVITY = "ADM3";//同意活动举办<br>
ADM_REJECT_ACTIVITY = "ADM4";//拒绝活动的举办<br>
//    重置用户密码 包括学生、组织<br>
ADM_RESET_PASSWORD= "ADM5";<br>
//    管理组织 包括：<br>
//    管理学生 包括：<br>
//组织：<br>
ORG_APPLY= "ORG1";//    申请组织活动<br>
ORG_REGISTER= "ORG2";//    注册<br>
ORG_GET_REJECTED_ACTIVITY= "ORG3";//    获取不通过的活动<br>
//    管理活动：查看活动参与者,历史活动举办结果<br>
ORG_GET_ENGAGEED_STUDENT = "ORG4";//获得活动参与者<br>
ORG_GET_ORGANIZED_ACTIVITY= "ORG5";//查询举办过的活动<br>
ORG_PASSED_ACTIVITY= "ORG6";//查询通过审核的活动<br>
ORG_BE_ORGANIZING_ACTIVITY= "ORG7";//查询通过待举办的活动<br>

<a name="其他"></a>
## 其他
