package service;

import java.util.List;

import inter.ActivityOperation;
import model.SqlActivity;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import sqlinter.SqlActivityOperation;
import vo.Activity;

@Service
public class ActivityService {
	@Autowired
	protected ActivityOperation activityOperation;
	@Autowired
	protected SqlActivityOperation SqlactivityOperation;
	//获得所有审核通过的活动
	public List<Activity> getPassedActivity(Activity activity){
		PageHelper.startPage(activity.getPageNum(), 9);
		List<Activity> activity2= activityOperation.getPassedActivity();
		return activity2;
	}
	//获得所有待审核的活动
	public List<Activity> getWaitingActivity(Activity activity){
		PageHelper.startPage(activity.getPageNum(), 10);
		List<Activity> activity2= activityOperation.getWaitingActivity();
		return activity2;
	}
	//创建活动
	public boolean createActivity(SqlActivity activity){
		int row=SqlactivityOperation.createActivity(activity);
		if(row>0)
			{
			return true;
			} else {
				return false;
			}
	}
	//审批活动
	public boolean checkActivity(SqlActivity activity){
		int i=SqlactivityOperation.checkActivity(activity);
		if(i==0){
			return false;
		}
		return true;
	}
	//参加活动
	public boolean engageActivity(SqlStudent student, SqlActivity activity){
		int i=SqlactivityOperation.engageActivity(activity.getId(),Long.toString(student.getId()));
		if(i==0)
		return false;
		else return true;
	}
	//cancel engage in activity
	public boolean cancelEngage(SqlStudent student,SqlActivity activity){
		int i=SqlactivityOperation.cancelEngage(activity.getId(),(Long.toString(student.getId())+","));
		if(i==0)
		return false;
		else return true;
	}
	//Delete activity
	public boolean deleteActivity(SqlActivity activity){
		int i=SqlactivityOperation.deleteActivity(activity);
		if(i==0) return false;
		else return true;
	}
}
