package sqlinter;

import model.SqlActivities;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlActivitiesOperation {
	public List<SqlActivities> getSqlActivitesById(SqlActivities activities);
	public List<SqlActivities> getActivitesByName();
	public  int createActivities(SqlActivities activities);
	public  int checkActivities(SqlActivities activities);
	public int getActivitiesCloumn();
	public int engageActivities(@Param("activities_id") int activities_id, @Param("student_id") String student_id);
	public int cancelEngage(@Param("activities_id") int activities_id, @Param("student_id") String student_id);
	public int deleteActivities(SqlActivities acitivities);
}
