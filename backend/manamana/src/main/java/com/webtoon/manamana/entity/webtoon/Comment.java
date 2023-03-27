package com.webtoon.manamana.entity.webtoon;

import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.webtoon.dto.request.CommentRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    private String content;
    @Column(name = "is_spoiler")
    private boolean isSpoiler;
    @Column(name = "report")
    private long report;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comment(long id, String content, boolean isSpoiler, long report, boolean isDeleted, Webtoon webtoon, User user) {
        this.id = id;
        this.content = content;
        this.isSpoiler = isSpoiler;
        this.report = report;
        this.isDeleted = isDeleted;
        this.webtoon = webtoon;
        this.user = user;
    }

    //생성 메서드
    public static Comment createComment(User user, Webtoon webtoon, CommentRequestDTO commentRequestDTO){

        return Comment.builder()
                .content(commentRequestDTO.getContent())
                .isSpoiler(commentRequestDTO.isSpoiler())
                .report(0)
                .isDeleted(false)
                .webtoon(webtoon)
                .user(user).build();
    }

    //수정 메서드
    public void updateComment(CommentRequestDTO commentRequestDTO){

        this.content = commentRequestDTO.getContent();
        this.isSpoiler = commentRequestDTO.isSpoiler();
    }

    //삭제 메서드
    public void removeComment(){
        this.isDeleted = true;
    }

    //신고수 증가
    public void commentReport(){
        this.report++;
    }
}
