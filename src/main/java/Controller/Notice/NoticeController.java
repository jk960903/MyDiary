package Controller.Notice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Notice.NoticeRequest;
import com.example.demo.Repository.Notice.NoticeService;
import com.example.demo.VO.Notice.NoticeVO;

@RestController
@RequestMapping(value="/Notice")
public class NoticeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required=true)
	NoticeService noticeService;
	
	@RequestMapping(value="/Notice")
	public ModelAndView GetNoticeList() {
		return new ModelAndView("Notice");
	}
	
	@RequestMapping(value="/NoticeGet", method = RequestMethod.GET)
	public List<NoticeVO> NoticeGet(NoticeRequest request){
		List<NoticeVO> list;
		if(request.getEmail().equals("")) {	
			list=noticeService.GetNoticeList();
		}else {
			list=noticeService.GetSearchNoticeList(request.getEmail());
		}
		
		return list;
	}
}
