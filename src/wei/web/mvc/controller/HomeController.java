package wei.web.mvc.controller;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Resource(name = "dataSourceMysql")
	private DataSource dataSource;

	@RequestMapping(value = "1")
	public String home(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String phone = "123dd88dsaf='aaa' or a=eea";
		// phone=phone.replaceAll(".*([';=<>]+|(--)+).*", "");
		model.addAttribute("msg", "Hello wang!" + phone);
		Pattern p = Pattern.compile("[';=<>]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(phone);
		String res = m.replaceAll("");
		model.addAttribute("msg", res);
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("date",
				DateFormat
						.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL)
						.format(new java.util.Date()));
		return "home";
	}
	
	@RequestMapping(value = "4")
	public String home4(HttpServletRequest request, HttpServletResponse response, Model model) {
		String phone = "123dd88dsaf='aaa' or a=eea";
		Pattern p = Pattern.compile("[';=<>(?:\\s+(?:or)\\s+)]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(phone);
		String res = m.replaceAll("");
		model.addAttribute("msg", res);
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("date", DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new java.util.Date()));
		return "pages/home4";
	}
	@RequestMapping(value = "5")
	public String home5(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("date", DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new java.util.Date()));
		return "pages/home5";
	}
}