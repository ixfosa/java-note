package dao;

import pojo.Person;
import pojo.SelectPersonById;

/**
 * Created by ixfosa on 2021/3/26 14:53
 */
public interface PersonDao {
    Person selectPersonById1(Integer id);
    Person selectPersonById2(Integer id);
    SelectPersonById selectPersonById3(Integer id);
    Integer insertPerson(Person person);

    Integer getIdcardId(Integer id);
    Integer updatePerson(Person person);

    Integer deletePerson(Integer id);
}
