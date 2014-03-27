package wei.web.mvc.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;

@Repository
@Aspect
public class WeiFormatInterceptor implements IFormatInterceptor {

	Logger log=Logger.getLogger(WeiFormatInterceptor.class);
	
	@Before("doThis()")
	public void preFormat() {
		log.info("--preFormat--");
	}
	
	@Pointcut("execution(* wei.web.mvc.mybatis..*.*(..))")// the pointcut expression
	public void doThis(){
		log.info("doThis");
	};
	
	@After("doThis()")
	public void afterFormat() {
		log.info("--afterFormat--");
	}

}
