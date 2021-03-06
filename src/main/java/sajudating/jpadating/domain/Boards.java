package sajudating.jpadating.domain;

import lombok.Builder;
import lombok.Getter;
import sajudating.jpadating.domainDto.BoardDTO;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
public class Boards {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private Long rowNum;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime pubTime;
    private LocalDateTime modTime;

    @Lob
    @Column(columnDefinition = "longtext")
    private String context;

    private Long views;
    private Long good;
    private Long bad;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @OneToMany(mappedBy = "boards", orphanRemoval = true)
    private List<Images> imageList= new ArrayList<>();
//
//    @OneToMany(mappedBy = "boardId")
//    private List<ReportBoard> reportBoardList= new ArrayList<>();

    @OneToMany(mappedBy = "boards")
    private List<Comment> comments = new ArrayList<>();

    private Long reportCount;

    protected Boards(){

    }

    @Builder
    public Boards(Long rowNum, String title, Member member, LocalDateTime pubTime, LocalDateTime modTime,
                  String context, Long views, Long good, Long bad, BoardType boardType,
                  Long reportCount) {
        this.rowNum =rowNum;
        this.title = title;
        this.member = member;
        this.pubTime = pubTime;
        this.modTime = modTime;
        this.context = context;
        this.views = views;
        this.good = good;
        this.bad = bad;
        this.boardType = boardType;
        this.reportCount = reportCount;
    }

    private void changeTitle(String title){
        if(title!=null)
            this.title=title;
    }
    //작성자는 변경할 필요가 없음.
    private void changeMemberNull(){
        if(member!=null){
            this.member=null;
        }
    }
    private void changeModTime(LocalDateTime modTime){
        if(modTime!=null)
            this.modTime=modTime;
    }
    private void changeContext(String context){
        if(context!=null)
            this.context=context;
    }
    private void changeView(Long views){
        if(views!=null)
            this.views=views;
    }
    private void changeGood(Long good){
        if(good!=null)
            this.good=good;
    }
    private void changeContext(Long bad){
        if(bad!=null)
            this.bad=bad;
    }
    private void changeBoardType(BoardType boardType){
        if(boardType!=null)
            this.boardType=boardType;
    }

    public void viewAdd(){
        this.views++;
    }

    public void updateBoard(BoardDTO boardDTO){
        changeTitle(boardDTO.getTitle());
        changeModTime(java.time.LocalDateTime.now());
        changeContext(boardDTO.getContext());
        changeBoardType(boardDTO.getBoardType());
    }

    public void deleteBoard(){

        changeMemberNull();

    }

    public void addImageList(Images images){
        if(images!=null){
            this.imageList.add(images);
        }
    }



}
