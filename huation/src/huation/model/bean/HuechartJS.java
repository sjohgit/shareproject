package huation.model.bean;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import huation.model.aboard.AboardDTO;
import huation.model.board.BoardDAO;
import huation.model.chart.ChartDTO;

@Controller
@RequestMapping("/chart")
public class HuechartJS {
	@Autowired
	private BoardDAO dao = null;
	
	
	@RequestMapping("/chartJS")//메인페이지
	public String chartJS(HttpServletRequest request) {
		List<ChartDTO> list = dao.chart();
		request.setAttribute("list", list);
	return "chart/chartJS";
}
	//Java script 
			@RequestMapping("/ajaxchart.do")
			@ResponseBody
			public String deletecontent(HttpServletRequest request,AboardDTO dto) {
				List<ChartDTO> list = dao.chart();
				request.setAttribute("list", list);
				HashMap map = new HashMap();
				map.put("list",list);
				String json = null;
				try {
					json = new ObjectMapper().writeValueAsString(map);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
				return json;
			}
	
}