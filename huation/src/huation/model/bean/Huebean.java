package huation.model.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Huebean {
	@RequestMapping("/huation")
	public String huationMain(Locale locale,HttpServletRequest request) {
		Date date = new Date();
		DateFormat dateFormat =DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		request.setAttribute("serverTime", formattedDate);
		

	return "member/huationMain";
}

}
