package sqlInters;

import model.SqlActivity;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;


public interface SqlActivityOperation {
	public List<SqlActivity> getSqlActivitesById(SqlActivity activity);
	public List<SqlActivity> getActivitesByName();
	public  long createActivity(SqlActivity activity);
	public  int checkActivity(SqlActivity activity);
	public  int changeState(SqlActivity activity);//改变活动状态
	public int getActivityCloumn();
	public int engageActivity(@Param("stuId") String stuId, @Param("actId") long actId);
	public String getEngage(@Param("actId") long actId);//获取活动参加的学生
	public int cancelEngage(@Param("activity_id") Long activity_id, @Param("student_id") String student_id);
	public int deleteActivity(SqlActivity acitivity);
	public int addActPhoto(@Param("filename") String filename,@Param("id") long id);//插入图片
}
