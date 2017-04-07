package constant;

/**
 * Created by wanghuan on 2017/3/24.
 */
//定义所有用户的行为，和数据库保持一致
public class Behave {
    //公共行为：
    public static final String PUB_LOGIN = "PUB1";//登陆
    public static final String PUB_LOGOUT = "PUB2";// 注销
    public static final String PUB_GET_ALL_ACTIVITY = "PUB3";//查看活动
    public static final String PUB_GET_ORGANIZED_ACTIVITY = "PUB4";//查看历史活动
    public static final String PUB_GET_TARGET_ACTIVITY = "PUB5";//查看个别活动
    public static final String PUB_GET_ORGANIZATION_INFO = "PUB6";//    查看组织的详细信息
    //学生：
    public static final String STU_ALTER_INFO = "STU1";//更改个人资料
    public static final String STU_ALTER_PASSWORD = "STU2";//    更改密码
    public static final String STU_ENGAGE_ACTIVITY = "STU3";//    参加活动
    public static final String STU_EXIT_ACTIVITY = "STU4";//    退出已经参加的活动
    public static final String STU_GET_ENGAGED_ACTIVITY = "STU5";//    查询活动参加历史
    public static final String STU_COMMENT = "STU6";//    评论活动
    public static final String STU_GET_ANOTHER_STUDENT = "STU7";//    查询参与相同活动的同学
    public static final String STU_REGISTER="STU8"; /*注册*/
   //这里应当设置一定权限，不一定所有同学会愿意暴露自己的联系方式
    public static final String STU_WRITE_PERSONAL_WORDS = "STU8";//    编写个人心得
    //管理员：
    //    管理活动 包括：查看活动、审阅活动、打回已经通过审核的活动
    public static final String ADM_REVIEW_ACTIVITY = "ADM1";//审阅活动
    public static final String ADM_WITHDRAW_ACTIVITY = "ADM2";//打回通过的活动
    public static final String ADM_AGREE_ACTIVITY = "ADM3";//同意活动举办
    public static final String ADM_REJECT_ACTIVITY = "ADM4";//拒绝活动的举办
    //    重置用户密码 包括学生、组织
    public static final String ADM_RESET_PASSWORD= "ADM5";
//    管理组织 包括：
//    管理学生 包括：
    //组织：
    public static final String ORG_APPLY= "ORG1";//    申请组织活动
    public static final String ORG_REGISTER= "ORG2";//    注册
    public static final String ORG_GET_REJECTED_ACTIVITY= "ORG3";//    获取不通过的活动
//    管理活动：查看活动参与者,历史活动举办结果
    public static final String ORG_GET_ENGAGEED_STUDENT = "ORG4";//获得活动参与者
    public static final String ORG_GET_ORGANIZED_ACTIVITY= "ORG5";//查询举办过的活动
    public static final String ORG_PASSED_ACTIVITY= "ORG6";//查询通过审核的活动
    public static final String ORG_BE_ORGANIZING_ACTIVITY= "ORG7";//查询通过待举办的活动

}
