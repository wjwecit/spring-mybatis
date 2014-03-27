package wei.web.mvc.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import wei.web.mvc.model.AreaChina;

/**
 * @author Jerry.Wang
 *  2013-07-25
 */
@Component("acMapper")
public interface AreaChinaMapper {

	public AreaChina selectAreaChina(int id);

	public void insertAreaChina(AreaChina ac);

	public void updateAreaChina(AreaChina ac);

	public void deleteAreaChina(int code);

	public List<AreaChina> selectAll();
	
	//@Select("select * from AreaChina where areaCode>=#{code} limit 5")
	public List<AreaChina> selectFive(int code);
	
	public List<AreaChina> selectSix(int areaCode);
	
	@SuppressWarnings("rawtypes")
	public List<HashMap> select10();
}