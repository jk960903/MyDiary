package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.MemberService;
import com.example.demo.VO.MemberVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import DTO.TestDTO;
@RestController
public class ControllerTest {
	@Autowired
	DataSource datasource;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/1")
	public String TableTest() {
		try {
			Connection connection = datasource.getConnection();
			String Query = "show tables";
			
			PreparedStatement pstmt = connection.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String temp = rs.getString("Tables_in_diary");
				System.out.println(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping(value="/")
	public ModelAndView Test() {
		return new ModelAndView("test");
	}
	
	@RequestMapping("/login")
	public ModelAndView Login(@CookieValue(value="id", defaultValue="0", required=true)String id,
			@CookieValue(value="pwd",defaultValue="0",required=true) String password,
			@CookieValue(value="autologin",defaultValue="0",required=true) String AutoLogin,
			HttpServletRequest req) {
		List<MemberVO> members;
		HttpSession session;
		try {
			int auto = Integer.parseInt(AutoLogin);
			if(auto == 1 && !id.equals("0") && !password.equals("0")) { // 자동로그인
				members = memberService.Login(id, password);
				
				if(members.size() >0) {//자동로그인 성공
					session = req.getSession();
					session.setAttribute("member", members.get(0));
					
					return new ModelAndView("Main");
					//세션 생성
					//메인페이지로 전달
				}
				
			}
		}catch(Exception e) {
			System.out.println("쿠키 없습니다.");
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping("/FindID")
	public ModelAndView FindID() {
		return new ModelAndView("FindID");
	}
	
	@RequestMapping("/FindPassWord")
	public ModelAndView FindPassWord() {
		return new ModelAndView("FindPassWord");
	}
	
	@RequestMapping("MakeAccount")
	public ModelAndView MakeAccount() {
		return new ModelAndView("MakeAccount");
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
	public String TestDo(String userName, String userID,TestDTO testDTO) {
		System.out.println(userName);
		System.out.println(testDTO.getUserName());
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
	
	@RequestMapping(value = "/main")
	public ModelAndView Main() {
		return new ModelAndView("Main");
	}
}
