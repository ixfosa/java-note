package test;

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
public class DeleteTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        PersonDao personDao = sqlSession.getMapper(PersonDao.class);
        IdcardDao idcardDao = sqlSession.getMapper(IdcardDao.class);

        Integer id = 3;
        Integer idcardId = personDao.getIdcardId(3);

        personDao.deletePerson(id);
        idcardDao.deleteIdcard(idcardId);

        sqlSession.commit();
    }
}
