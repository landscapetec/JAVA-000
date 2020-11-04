import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
   private Logger logger = LoggerFactory.getLogger(LoggerTest .class);
   @Test
    public void testLoggerInfo(){
       logger.info("adsfasdfasf{}{}",100,"|||||");
   }
}
