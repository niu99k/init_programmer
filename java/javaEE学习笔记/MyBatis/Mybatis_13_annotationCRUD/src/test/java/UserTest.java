import com.gg.dao.IUserDao;
import com.gg.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao iUserDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("sqlSession.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession();
        iUserDao = sqlSession.getMapper(com.gg.dao.IUserDao.class);
    }

    @After
    public void deinit() throws Exception {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<User> userList = iUserDao.findAll();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("test insert");
        user.setAddress("-----1");
        user.setBirthday(new Date());
        user.setSex("男");
        iUserDao.saveUser(user);
        sqlSession.commit();
    }
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(50);
        user.setUserName("test insert");
        user.setAddress("-----1");
        user.setBirthday(new Date());
        user.setSex("m");
        iUserDao.update(user);
        sqlSession.commit();
    }
}
