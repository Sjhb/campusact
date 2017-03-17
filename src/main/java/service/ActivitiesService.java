package service;

import java.util.List;

import inter.ActivitiesOperation;
import model.SqlActivities;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import sqlinter.SqlActivitiesOperation;
import vo.Activities;

@Service
public class ActivitiesService {
	@Autowired
	protected ActivitiesOperation activitiesOperation;
	@Autowired
	protected SqlActivitiesOperation SqlactivitiesOperation;
	//获得所有审核通过的活动
	public List<Activities> getPassedActivities(Activities activities){
		PageHelper.startPage(activities.getPageNum(), 9);
		List<Activities> activities2=activitiesOperation.getPassedActivities();
		return activities2;
	}
	//获得所有待审核的活动
	public List<Activities> getWaitingActivities(Activities activities){
		PageHelper.startPage(activities.getPageNum(), 10);
		List<Activities> activities2=activitiesOperation.getWaitingActivities();
		return activities2;
	}
	//创建活动
	public boolean createActivities(SqlActivities activities){
		int row=SqlactivitiesOperation.createActivities(activities);
		if(row>0)
			{
			return true;
			} else {
				return false;
			}
	}
	//审批活动
	public boolean checkActivities(SqlActivities activities){
		int i=SqlactivitiesOperation.checkActivities(activities);
		if(i==0){
			return false;
		}
		return true;
	}
	//参加活动
	public boolean engageActivities(SqlStudent student, SqlActivities activities){
		int i=SqlactivitiesOperation.engageActivities(activities.getId(),Long.toString(student.getId()));
		if(i==0)
		return false;
		else return true;
	}
	//cancel engage in activities
	public boolean cancelEngage(SqlStudent student,SqlActivities activities){
		int i=SqlactivitiesOperation.cancelEngage(activities.getId(),(Long.toString(student.getId())+","));
		if(i==0)
		return false;
		else return true;
	}
	//Delete activity
	public boolean deleteActivities(SqlActivities activities){
		int i=SqlactivitiesOperation.deleteActivities(activities);
		if(i==0) return false;
		else return true;
	}
}
