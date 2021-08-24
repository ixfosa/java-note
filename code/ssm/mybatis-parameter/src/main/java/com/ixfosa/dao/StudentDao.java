package com.ixfosa.dao;

import com.ixfosa.pojo.QueryParam;
import com.ixfosa.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ixfosa on 2021/4/22 14:32
 */
public interface StudentDao {

    /**
     * 一个简单类型的参数：
     *   简单类型： mybatis把java的基本数据类型和String都叫简单类型。
     *   在mapper文件获取简单类型的一个参数的值，使用 #{任意字符}
     */
    Student selectStudentById(@Param("studentId") Integer id);

    /**
     * 多个参数： 命名参数，在形参定义的前面加入 @Param("自定义参数名称")
     */
    List<Student> selectMultiParam(@Param("mymame") String name,
                                   @Param("myage") Integer age);

    /**
     * 多个参数，使用java对象作为接口中方法的参数
     */
    List<Student> selectMultiObject(QueryParam param);
    List<Student> selectMultiStudent(Student student);

    /**
     * 多个参数-简单类型的，按位置传值，
     * mybatis 3.4之前，使用 #{0} ，#{1}
     * mybatis 3.4之后 ，使用 #{arg0} ,#{arg1}
     */
    List<Student> selectMultiPosition(String name, Integer age);

    /**
     * 多个参数，使用Map存放多个值
     */
    List<Student> selectMultiByMap(Map<String, Object> map);


    List<Student> selectUse$(@Param("sname") String name);
    List<Student> selectUse$Order(@Param("colName") String colName);
}
