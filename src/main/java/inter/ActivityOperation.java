package inter;

import vo.Activity;

import java.util.List;


public interface ActivityOperation {
	public List<Activity> getActivitesById(Activity activity);
	public List<Activity> getPassedActivity();
	public List<Activity> getWaitingActivity();
	
	public void changeActivites();
	public int getActivityCloumn();
	}
