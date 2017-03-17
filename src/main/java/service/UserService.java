package service;

import java.util.List;

import model.MiUserInfo;
import model.SqlAdmin;
import model.SqlOrganization;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import sqlinter.SqlAdminOperation;
import sqlinter.SqlOrganizationOperation;
import sqlinter.SqlStudentOperation;

@Service
public class UserService {

	@Autowired
	private SqlAdminOperation sqlAdminOperation;
	@Autowired
	private SqlOrganizationOperation sqlOrganizationOperation;
	@Autowired
	private SqlStudentOperation sqlStudentOperation;
	/*
	 * 用户登陆
	 */
	//管理员
	public SqlAdmin AdminLogin(MiUserInfo user){
		List<SqlAdmin> admins=sqlAdminOperation.selectSqlAdmin(user);
		if (admins.size()==0) {
			return null;
		}
		SqlAdmin result=admins.get(0);
		SqlAdmin admin=new SqlAdmin();
		admin.setId(result.getId());
		admin.setIcon(result.getIcon());
		admin.setName(result.getName());
		admin.setRole(result.getRole());
		return admin;
	}
	//组织
	public SqlOrganization OrganizationLogin(MiUserInfo user){
		List<SqlOrganization> organizations=sqlOrganizationOperation.selectSqlOrganization(user);
		if (organizations==null||!(organizations.size()>0)) {
			return null;
		}
		SqlOrganization result=organizations.get(0);
		SqlOrganization organization=new SqlOrganization();
		organization.setId(result.getId());
		organization.setName(result.getName());
		organization.setIcon(result.getIcon());
		organization.setPhone(result.getPhone());
		organization.setMail(result.getMail());
		organization.setDetail(result.getDetail());
		organization.setAddress(result.getAddress());
		organization.setRole(result.getRole());
		return organization;
	}
	//学生
	public SqlStudent StudentLogin(MiUserInfo user ){
		List<SqlStudent> students=sqlStudentOperation.selectSqlStudent(user.getUserid(),user.getPassword());
		if (!(students.size()>0)) {
			return null;
		}
		SqlStudent result=students.get(0);
		SqlStudent student=new SqlStudent();
		student.setId(result.getId());
		student.setIcon(result.getIcon());
		student.setName(result.getName());
		student.setSex(result.getSex());
		student.setPhone(result.getPhone());
		student.setMajor(result.getMajor());
		student.setClasses(result.getClasses());
		student.setCollege(result.getCollege());
		student.setRole(result.getRole());
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
