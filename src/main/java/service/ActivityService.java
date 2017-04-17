package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.SqlActivity;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import sqlInters.ActivityOperation;
import sqlInters.SqlActivityOperation;
import vo.Activity;

@Service
public class ActivityService {
	@Autowired
	protected ActivityOperation activityOperation;
	@Autowired
	protected SqlActivityOperation sqlactivityOperation;
	//获得所有审核通过的活动
	public List<Activity> getPassedActivity(Activity activity){
		PageHelper.startPage(activity.getPageNum(), 9);
		List<Activity> result= activityOperation.getActivityByState(2000);

		return result;
	}
	//获得所有待审核的活动
	public List<Activity> getWaitingActivity(Activity activity){
		PageHelper.startPage(activity.getPageNum(), 10);
		List<Activity> activity2= activityOperation.getActivityByState(1000);
		return activity2;
	}
	//创建活动
	public long createActivity(SqlActivity activity){
		long row=sqlactivityOperation.createActivity(activity);
//        if (row>0){
//			return sqlactivityOperation.getIdentity();
//		}
		return row;
    	}
//    	插入活动图片
    public boolean addActPhoto(String filename,long id){
	    int row=sqlactivityOperation.addActPhoto(filename,id);
	    if (row>0){
	        return true;
        }
        return false;
    }
//    修改活动
    public boolean alterActivity(SqlActivity activity){
	    return true;
    }
	//审批活动
	public boolean checkActivity(SqlActivity activity){
		int i=sqlactivityOperation.checkActivity(activity);
		if(i==0){
			return false;
		}
		return true;
	}
	//参加活动
	public boolean engageActivity(SqlStudent student, SqlActivity activity){
		int i=sqlactivityOperation.engageActivity(activity.getId(),Long.toString(student.getId()));
		if(i==0)
		return false;
		else return true;
	}
	//cancel engage in activity
	public boolean cancelEngage(SqlStudent student,SqlActivity activity){
		int i=sqlactivityOperation.cancelEngage(activity.getId(),(Long.toString(student.getId())+","));
		if(i==0)
		return false;
		else return true;
	}
	//Delete activity
	public boolean deleteActivity(SqlActivity activity){
		int i=sqlactivityOperation.deleteActivity(activity);
		if(i==0) return false;
		else return true;
	}
}
