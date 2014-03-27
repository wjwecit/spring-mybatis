/**
 * 
 */
package wei.web.mvc.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wei.web.mvc.model.AreaChina;
import wei.web.mvc.mybatis.mapper.AreaChinaMapper;

/**
 * @author Jerry.Wang
 *
 */
@Controller
//@RequestMapping("/mybatis")
public class MyBatisController {
	
	@Resource(name = "acMapper")
	AreaChinaMapper acMapper;

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "mb1")
	@Transactional(value="txManagerMysql")
	public String home(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		
		/*List<AreaChina> list=acMapper.selectFive(755);
		Iterator<AreaChina> it=list.iterator();
		StringBuilder sb=new StringBuilder();
		while(it.hasNext()){
			AreaChina ac=it.next();
			if(ac.getAreaName().equals("老宝安")){
				ac.setAreaName("旧的宝安县");
				acMapper.updateAreaChina(ac);
			}
			sb.append(ac.getAreaCode());
			sb.append(":");
			sb.append(ac.getAreaName());
			sb.append(":");
			sb.append(ac.getAreaCodeDeprecated());
			sb.append("\r\n");
			System.out.println(ac.getAreaName());
		}
		model.addAttribute("acInfo", sb);
		*/
		/*List ll=acMapper.selectSix(100000);
		Iterator itr=ll.iterator();
		while(itr.hasNext()){
			java.util.Map map=(Map) itr.next();
			System.out.println(map.get("areaName"));
		}
		*/
		List l10=acMapper.select10();
		Iterator itr=l10.iterator();
		while(itr.hasNext()){
			java.util.Map map=(Map) itr.next();
			System.out.println(map.get("areaName"));
		}
		
		return "pages/mybatis/mb1";
	}
	
	@RequestMapping(value = "mbu1")
	@Transactional(value="txManagerMysql")
	public String update1(HttpServletRequest request,HttpServletResponse response, Model model,@RequestParam(required=false) int code) {
		AreaChina areachina=acMapper.selectAreaChina(code);
		areachina.setAreaName(new Random().nextInt(100000)+"");
		acMapper.updateAreaChina(areachina);
		if(code!=0){
			throw new RuntimeException("test");
		}
		areachina=acMapper.selectAreaChina(code);
		model.addAttribute("acInfo",areachina.toString());
		
		return "pages/mybatis/mb1";
	}
	
	
}
