package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeService;
import com.example.demo.dto.Notice.UpdateNoticeCountRequest;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.NoticeRequest;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.vo.notice.NoticeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="api/notice")
public class NoticeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required=true)
    NoticeService noticeService;

    @Autowired(required = true)
    JwtService jwtService;


    @RequestMapping(value="/noticeget", method = RequestMethod.GET)
    public List<NoticeVO> NoticeGet(NoticeRequest request, HttpServletRequest servletRequest){
        List<NoticeVO> list;
        if(request.getSearch()==null||request.getSearch().equals("")) {
            list=noticeService.GetNoticeList();
        }else {
            list=noticeService.GetSearchNoticeList(request.getSearch());
        }

        return list;
    }

    /*
    공지사항 추가 api

     */
    @RequestMapping(value="/addnotice",method=RequestMethod.POST)
    public ResponseEntity<SendMessage<NoticeVO>> AddNotice(NoticeVO noticeVO,HttpServletRequest request){
        Map<String,Object> auth;
        SendMessage<NoticeVO> message;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            auth=jwtService.requestAuthorization(request);
            noticeVO.CheckValidate();
            noticeService.AddNotice(noticeVO);
        } catch (IllegalAccessException e) {
            message= new SendMessage<>(null, StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){
            message= new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            message=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        message = new SendMessage<>(noticeVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);

    }

    @RequestMapping(value="/updatenoticeview" ,method=RequestMethod.PATCH)
    public ResponseEntity<SendMessage<Integer>> UpdateNoticeCountView( UpdateNoticeCountRequest updateNoticeCountRequest){
        SendMessage<Integer> message;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> auth;
        NoticeVO noticeVO = null;
        try{
            if(updateNoticeCountRequest.checkValidate()){
                noticeVO=noticeService.GetNoticeViewCount(updateNoticeCountRequest.getIdx());
                updateNoticeCountRequest.setViewCount(noticeVO.getViewcount()+1);
                noticeService.UpdateNotoiceView(updateNoticeCountRequest);
            }
        }catch(NullPointerException e){
            message =new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(IndexOutOfBoundsException e){
            message=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            message= new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message = new SendMessage<>(noticeVO.getViewcount()+1,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


}
