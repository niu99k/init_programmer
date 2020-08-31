import com.gg.AnnotationIoc;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotationIoc {
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationIoc annotationIoc = (AnnotationIoc) applicationContext.getBean("testAnno");
        System.out.println(annotationIoc);
    }
}
