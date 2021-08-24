package test;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.IdcardDao;
import dao.PersonDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Idcard;
import pojo.Person;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ixfosa on 2021/3/26 14:57
 */
public class UpdateTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        PersonDao personDao = sqlSession.getMapper(PersonDao.class);
        IdcardDao idcardDao = sqlSession.getMapper(IdcardDao.class);


        Person person = new Person();
        person.setId(3);
        person.setName("黑菊花");
        person.setAge(16);

        Integer idcardId = personDao.getIdcardId(person.getId());
        Idcard idcard = new Idcard();
        idcard.setCode("ixfosa");
        idcard.setId(idcardId);
        idcardDao.updateIdcard(idcard);

        personDao.updatePerson(person);
        sqlSession.commit();
    }
}
