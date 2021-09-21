package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="notice_review_review")
public class ReadNoticeReviewReviewVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(name ="content")
    private String content;

    @Column(name ="reviewidx")
    private Long reviewidx;

    @Column(name="memberidx")
    private Long memberidx;

    @Column(name="regdate")
    private Date regdate;

    @Column(name="isdeleted")
    private Integer isdeleted;

    @OneToOne
    @JoinColumn(name="memberidx",referencedColumnName = "idx",insertable = false, updatable = false)
    private ReviewWriterVO reviewWriterVO;

    public boolean CheckValidate(){
        if(content==null || content.equals("") || this.reviewidx <=0 || this.memberidx <=0) throw new NullPointerException("BAD REQEUST");
        return true;
    }
    public boolean CheckLoginValidate(Long member_idx) throws IllegalAccessException{
        if(this.memberidx == member_idx) return true;
        else throw new IllegalAccessException("No Token Or Token is Expired");
    }
}
