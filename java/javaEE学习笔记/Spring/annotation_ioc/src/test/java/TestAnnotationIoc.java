import com.gg.AnnotationIoc;
import com.gg.sevice.TestService;
import com.gg.sevice.impl.TestServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotationIoc {
    @Test
    public void testComponent() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationIoc annotationIoc = (AnnotationIoc) applicationContext.getBean("testAnno");
        System.out.println(annotationIoc);
    }

    @Test
    public void testResources() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        TestService testService = (TestService) applicationContext.getBean("testService");
        testService.testService();
    }

    @Test
    public void testScope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        TestServiceImpl testService1 = (TestServiceImpl) applicationContext.getBean("testService");
//        TestServiceImpl testService2 = (TestServiceImpl) applicationContext.getBean("testService");

        TestService testService1 = (TestServiceImpl) applicationContext.getBean("testService");
        TestService testService2 = (TestServiceImpl) applicationContext.getBean("testService");
        System.out.println(testService1 == testService2);
    }

    @Test
    public void testLifeCycle() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        TestService testService = (TestService) classPathXmlApplicationContext.getBean("testService");
        testService.testService();
        classPathXmlApplicationContext.close();
    }
}
