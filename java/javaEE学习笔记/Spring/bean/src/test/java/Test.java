import domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
    @org.junit.Test
    public void testUserBean1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) applicationContext.getBean("UsertestConstructor1");
        System.out.println(user);
    }
    @org.junit.Test
    public void testUserBean2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) applicationContext.getBean("UsertestConstructor2");
        System.out.println(user);
    }
}
