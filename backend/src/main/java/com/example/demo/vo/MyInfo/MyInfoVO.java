package com.example.demo.vo.MyInfo;

import com.example.demo.dto.Myinfo.MyInfoVisit;
import com.example.demo.vo.Member.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
//마이페이지
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoVO {
    private MemberVO memberVO;
    private List<MyInfoVisit> visitList;
    private FriendsVO friends;
    // 팔로워 및 팔로잉 보여주기
}
