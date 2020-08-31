import com.gg.config.RabbitMQConfig;
import com.gg.service.Sender;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMQ {

    @Test
    public void testSend() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        Sender sender = applicationContext.getBean("sender", Sender.class);
        sender.send();
    }
}
