package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NoticeReviewVO {
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
    private Byte isDeleted;

    @Column(name="memberidx")
    private Long memberidx;

    public boolean CheckValidate() throws NullPointerException{
        if(this.idx==null ||this.idx<=0|| this.noticeidx <=0 || this.memberidx<=0 || this.content == null) throw new NullPointerException("BAD REQUEST");
        return true;
    }

    public boolean checkLoginValidate(Long memberidx) throws IllegalAccessException{
        if(this.memberidx == memberidx) return true;
        else throw new IllegalAccessException("No Token Or Token is Expired");
    }



}
