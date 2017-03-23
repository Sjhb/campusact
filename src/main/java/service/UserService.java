package service;

import java.util.List;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import sqlInters.*;
import constant.field;
import vo.VoAdmin;
import vo.VoOrganization;
import vo.VoStudent;

import static constant.field.ADMINISTOR;
import static constant.field.ORGANIZATION;
import static constant.field.STUDENT;

@Service
public class UserService {

	@Autowired
	private SqlAdminOperation sqlAdminOperation;
	@Autowired
	private SqlOrganizationOperation sqlOrganizationOperation;
	@Autowired
	private SqlStudentOperation sqlStudentOperation;
	@Autowired
    private UserOperation userOperation;
	/*
	 * 用户登陆,并查询详细信息
	 */
	public Object userLogin(MiUserInfo miUserInfo){
		List<MiUserInfo> users=userOperation.userLogin(miUserInfo);
		if(users.size()==0){
			return null;
		}
		MiUserInfo user=users.get(0);
        switch (user.getRole().getDetail()){
            case STUDENT:
            	VoStudent student=getStudentInfo(user);
            	student.setRole(user.getRole());
				return student;
            case ADMINISTOR:
            	VoAdmin admin=getAdminInfo(user);
				admin.setRole(user.getRole());
				return admin;
            case ORGANIZATION:
            	VoOrganization organization=getOrganizationInfo(user);
				organization.setRole(user.getRole());
				return organization;
            default:return null;
        }
	}
	//管理员
	public VoAdmin getAdminInfo(MiUserInfo user){
		List<SqlAdmin> admins=sqlAdminOperation.selectSqlAdmin(user);
		if (admins.size()==0) {
			return null;
		}
		SqlAdmin result=admins.get(0);
		VoAdmin admin=new VoAdmin();
		admin.setId(result.getId());
		admin.setIcon(result.getIcon());
		admin.setName(result.getName());
		return admin;
	}
	//组织
	public VoOrganization getOrganizationInfo(MiUserInfo user){
		List<SqlOrganization> organizations=sqlOrganizationOperation.selectSqlOrganization(user);
		if (organizations==null||!(organizations.size()>0)) {
			return null;
		}
		SqlOrganization result=organizations.get(0);
		VoOrganization organization=new VoOrganization();
		organization.setId(result.getId());
		organization.setName(result.getName());
		organization.setIcon(result.getIcon());
		organization.setPhone(result.getPhone());
		organization.setMail(result.getMail());
		organization.setDetail(result.getDetail());
		organization.setAddress(result.getAddress());
		return organization;
	}
	//根据学生id查询详细信息
	public VoStudent getStudentInfo(MiUserInfo user ){
		List<SqlStudent> students=sqlStudentOperation.selectSqlStudent(user.getId());
		if (!(students.size()>0)) {
			return null;
		}
		SqlStudent result=students.get(0);
		VoStudent student=new VoStudent();
		student.setId(result.getId());
		student.setIcon(result.getIcon());
		student.setName(result.getName());
		student.setSex(result.getSex());
		student.setPhone(result.getPhone());
		student.setMajor(result.getMajor());
		student.setClasses(result.getClasses());
		student.setCollege(result.getCollege());
		return student;
	}
//修改用户信息
	public boolean alterUser(String role,Object object){
		if(role.equals("sudent")){
			int cloumns=sqlStudentOperation.alterStudent((SqlStudent)object);
			if(cloumns==0){
				return false;
			}
		}else if(role.equals("admin")){
			if(sqlOrganizationOperation.alterSqlOrganization((SqlOrganization)object)==0){
				return false;
			};
		}else if(role.equals("organization")){
			if(sqlAdminOperation.alterSqlAdmin((SqlAdmin)object)==0){
				return false;
			}
		}
		return true;
	}

	public List<SqlAdmin> findAllAdmin(MiUserInfo user){
		PageHelper.startPage(user.getPageNum(), 3);
		List<SqlAdmin> allUser=sqlAdminOperation.selectSqlAdmin(user);
		return allUser;
	}

}
