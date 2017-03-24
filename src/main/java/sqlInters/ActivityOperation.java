package sqlInters;

import model.SqlActivity;
import vo.Activity;

import java.util.List;


public interface ActivityOperation {
	public List<Activity> getActivityById(Activity activity);
	public List<Activity> getPassedActivity();
	public List<Activity> getWaitingActivity();
	public int createActivity(SqlActivity activity);
	public void changeActivity();
	public int getActivityCloumn();
	}
