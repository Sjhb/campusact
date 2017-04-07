package sqlInters;

import model.SqlStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlStudentOperation {
	public List<SqlStudent> selectSqlStudent(@Param("id") long id) ;//通过id
	public int alterStudent(SqlStudent student);//修改学生信息
	public int insertStu(SqlStudent sqlStudent);//插入学生
}
