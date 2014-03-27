/**
 * 
 */
package wei.web.mvc.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

/**
 * @author wei
 * 
 */
public class WeiAopTest {

	private static Logger log=Logger.getLogger(WeiAopTest.class);
	public Object profile(ProceedingJoinPoint call)
			throws Throwable {
		StopWatch clock = new StopWatch("Profiling for '"+call);
		try {
			clock.start(call.toShortString());
			return call.proceed();
		} finally {
			clock.stop();
			log.info(clock.prettyPrint());
		}
	}
}
