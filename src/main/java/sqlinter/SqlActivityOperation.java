package sqlinter;

import model.SqlActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlActivityOperation {
	public List<SqlActivity> getSqlActivitesById(SqlActivity activity);
	public List<SqlActivity> getActivitesByName();
	public  int createActivity(SqlActivity activity);
	public  int checkActivity(SqlActivity activity);
	public int getActivityCloumn();
	public int engageActivity(@Param("activity_id") int activity_id, @Param("student_id") String student_id);
	public int cancelEngage(@Param("activity_id") int activity_id, @Param("student_id") String student_id);
	public int deleteActivity(SqlActivity acitivity);
}
