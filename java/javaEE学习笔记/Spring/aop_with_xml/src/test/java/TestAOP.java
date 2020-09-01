import com.gg.service.IService;
import com.gg.service.impl.IServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOP {
    @Test
    public void testAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        IService service = applicationContext.getBean("testService", IService.class);
        service.test1();
        service.test2(2);
        service.test3();
    }
}
