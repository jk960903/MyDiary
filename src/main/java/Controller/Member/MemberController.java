package Controller.Member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.*;
import com.example.demo.VO.*;

@RestController
@RequestMapping(value = "member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = true)
	MemberService memberService;
	
	
	
	@RequestMapping(value ="/FindByID" ,method = RequestMethod.GET)
	public MemberVO FindByID(@RequestParam String ID) {
		return memberService.findByID(ID);
	}
	
	@RequestMapping(value ="/FindByEmail", method = RequestMethod.GET)
	public MemberVO FindByEmail(@RequestParam String email) {
		return memberService.findByEmail(email);
	}
	@RequestMapping(value ="/MakeAccount", method = RequestMethod.POST)
	public Integer MakeAccount(MemberVO memberVO) {
		return memberService.MakeAccount(memberVO);
	}
}
