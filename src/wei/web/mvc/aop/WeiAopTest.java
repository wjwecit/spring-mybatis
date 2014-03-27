/**
 * 
 */
package wei.web.mvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

/**
 * @author wei
 * 
 */
public class WeiAopTest {

	public Object profile(ProceedingJoinPoint call)
			throws Throwable {
		StopWatch clock = new StopWatch("Profiling for '"+call);
		try {
			clock.start(call.toShortString());
			return call.proceed();
		} finally {
			clock.stop();
			System.out.println(clock.prettyPrint());
		}
	}
}
