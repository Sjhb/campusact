package inter;

import vo.Activities;

import java.util.List;


public interface ActivitiesOperation {
	public List<Activities> getActivitesById(Activities activities);
	public List<Activities> getPassedActivities();
	public List<Activities> getWaitingActivities();
	
	public void changeActivites();
	public int getActivitiesCloumn();
	}
