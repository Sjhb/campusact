package sqlInters;

import model.SqlActivity;
import org.apache.ibatis.annotations.Param;
import vo.Activity;

import java.util.List;


public interface ActivityOperation {
	public List<Activity> getActivityById(Activity activity);
	public List<Activity> getActivityByState(@Param("stateId") int stateId);//根据状态获取活动
	public List<Activity> getActivityByOid(Activity activity,@Param("Oid") long Oid);//根据组织id查找活动
	public List<Activity> getActivityByStuid(Activity activity,@Param("Stuid") long Stuid);//根据学生id查找活动
	public int createActivity(SqlActivity activity);
	public void changeActivity();
	public int getActivityCloumn();
	}
