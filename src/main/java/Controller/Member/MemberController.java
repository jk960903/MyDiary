package Controller.Member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Models.Login.LoginRequestModel;
import com.example.demo.Repository.*;
import com.example.demo.VO.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = true)
	MemberService memberService;
	
	@RequestMapping(value="/")
	public String Test() {
		return "Test Page";
	}
	
	@RequestMapping(value ="/FindByID" ,method = RequestMethod.GET)
	public MemberVO FindByID(@RequestParam String ID) {
		MemberVO result;
		try {
			result = memberService.findByID(ID).get(0);
			return result;
		}catch(Exception e) {
			
		}
		return null;
	}
	
	@RequestMapping(value ="/FindByEmail", method = RequestMethod.GET)
	public MemberVO FindByEmail(@RequestParam String email) {
		MemberVO result;
		try {
			result = memberService.findByEmail(email).get(0);
			
			//null이 아니라면 메일 보내기
		}catch(Exception e) {
			result = null;
		}
		return result;
	}
	
	@RequestMapping(value ="/MakeAccount", method = RequestMethod.POST)
	public Integer MakeAccount(MemberVO memberVO) {
		memberVO.setIsdeleted(Byte.parseByte("1"));
		return memberService.MakeAccount(memberVO);
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView Login(LoginRequestModel model) {
		MemberVO result =  memberService.Login(model.getUserID(),model.getPassword()).get(0);
		
		if(model.getAutologin() == 1 && result!=null) {
			//쿠키를 생성하여 넣어줌
			return new ModelAndView("login");
			
		}
		
		return new ModelAndView("test");
	}
}
