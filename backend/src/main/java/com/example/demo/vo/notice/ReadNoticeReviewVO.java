package com.example.demo.vo.notice;

import com.example.demo.vo.Member.MemberVO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="notice_review")
@Builder
//읽기 전용 vo
public class ReadNoticeReviewVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(name="noticeidx")
    private Long  noticeidx;

    @Column(name="content")
    private String content;

    @Column(name="regdate")
    private Date regDate;

    @Column(name="isdeleted")
    private Integer isDeleted;

    @Column(name="memberidx")
    private Long memberidx;

    @OneToOne
    @JoinColumn(name="memberidx" ,referencedColumnName = "idx",insertable = false,updatable = false)
    private ReviewWriterVO reviewWriterVO;

    public boolean CheckValidate() throws NullPointerException{
        if(this.idx==null ||this.idx<=0|| this.noticeidx <=0 || this.memberidx<=0 || this.content == null) throw new NullPointerException("BAD REQUEST");
        return true;
    }

    public boolean checkLoginValidate(Long memberidx) throws IllegalAccessException{
        if(this.memberidx == memberidx) return true;
        else throw new IllegalAccessException("No Token Or Token is Expired");
    }



}
