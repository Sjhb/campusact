package sqlinter;

import model.SqlStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlStudentOperation {
	public List<SqlStudent> selectSqlStudent(@Param("userid") long userid, @Param("password") String password) ;//通过id以及名字
	public int alterStudent(SqlStudent student);//修改学生信息
	
}
