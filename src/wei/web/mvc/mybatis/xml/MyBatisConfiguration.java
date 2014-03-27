/**
 * 
 */
package wei.web.mvc.mybatis.xml;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import wei.web.mvc.mybatis.mapper.AreaChinaMapper;

/**
 * @author Jerry.Wang
 * 2013-07-29
 */
//@Component
public class MyBatisConfiguration {

	@Resource(name = "sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;

	@Bean
	@Qualifier("acMapper")
	public MapperFactoryBean<AreaChinaMapper> acMapper() {
		MapperFactoryBean<AreaChinaMapper> bean = new MapperFactoryBean<AreaChinaMapper>();
		bean.setMapperInterface(AreaChinaMapper.class);		
		bean.setSqlSessionFactory(sqlSessionFactory);
		return bean;
	}
}
