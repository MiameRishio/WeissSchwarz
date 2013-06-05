package ws.test.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {

	public static void main(String[] args) {
		System.out.println("log4j test");
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getRootLogger();
		logger.debug("abc");
	}

}
