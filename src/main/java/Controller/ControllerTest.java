package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@RestController
public class ControllerTest {
	@Autowired
	DataSource datasource;
	
	@RequestMapping(value="/")
	public ModelAndView Test() {
		return new ModelAndView("test");
	}
	@RequestMapping("/login")
	public ModelAndView Login() {
		return new ModelAndView("login");
	}
	@RequestMapping(value = "/restTest")
	public String restTest(@RequestParam String str) {
		str ="{\r\n" + 
				"    \"contactInfo\": {\r\n" + 
				"        \"contactEmail\": \"test@test.com   \",\r\n" + 
				"        \"contactName\": \"구매자\",\r\n" + 
				"        \"mobile\": \"  01099999999    \"\r\n" + 
				"    },\r\n" + 
				"    \"items\": {\r\n" + 
				"        \"itemType\": \"Foods\",\r\n" + 
				"        \"id\": 1\r\n" + 
				"    }\r\n" + 
				"}";
		return str;
	}
	@RequestMapping(value ="test.do", method = RequestMethod.GET)
	public String TestDo(String userName, String userID) {
		System.out.println(userName);
		return "succesS";
	}
	@RequestMapping(value = "/ReadAPI")
	public void ReadAPI() {
		List<HttpMessageConverter<?>> converters =new ArrayList<>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("str", "this is Rest API");
		try {
			Connection connection = datasource.getConnection();
			String query = "show tables"; // ?? 왜 0일까
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			int num = rs.getRow();
			
			System.out.println("디비 연결 성공 후 데이터베이스는 없습니다. "+num);
			String script = "insert into hello(temp) values('10')";
			pstmt = connection.prepareStatement(script);
			boolean results = pstmt.execute();
			System.out.println(results);
			
			//rs.getType(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("디비연결이 되지 않음");
		}
		String result = restTemplate.postForObject("http://localhost:8080/SampleProject/restTest/",map,String.class);
		
		JsonParser jp = new JsonParser();
	    JsonElement je = jp.parse(result);

	    //String id = je.getAsJsonObject().get("id").getAsString();
		//System.out.println(id);
	}
	
	@RequestMapping(value ="/home" , method=RequestMethod.GET)
	public ModelAndView TestDB(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<String> resultList = new ArrayList<>();
		
		return mav;
	}
	
	@RequestMapping(value = "jsptest")
	private String JspTest() {
		return "test";
	}
}
