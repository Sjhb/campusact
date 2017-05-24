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
//	通过活动id查询活动
	public  List<Activity> getActivityByOid(Activity activity,long oid){
		PageHelper.startPage(activity.getPageNum(),9);
		List<Activity> result=activityOperation.getActivityByOid(activity,oid);
		return  result;
	}
//	根据学生id获取活动
	public  List<Activity> getActivityByStuid(Activity activity,long stuid){
		PageHelper.startPage(activity.getPageNum(),9);
		List<Activity> r=activityOperation.getActivityByStuid(activity,stuid);
		return r;
	}
	//判断学生是否已经参加了活动
	public boolean isEngage(long actId,long stuId){
		long column=activityOperation.isEngage(actId,stuId);
//		if(column==null){
//
//		}
		if(column>0){
			return true;
		}
		return false;
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
	public boolean engageActivity(String stuId,long actId){
		int i=sqlactivityOperation.engageActivity(stuId,actId);
		if(i==0)
		return false;
		else return true;
	}
//	获取参加了活动的学生id
	public String getEngage(long actId){
		String s=sqlactivityOperation.getEngage(actId);
		return s;
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
