package com.example.BootJPABoard.board.dto;

import com.example.BootJPABoard.board.entity.File;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class FileRequestDto {
    //origname,savename, size, deleteYn, inserttime id제외
    private String origname;
    private String savename;
    private int size;
    private char deleteYn;

    private Long boardId;

    public File toEntity() {
        return File.builder()
                .origname(origname)
                .savename(savename)
                .size(size)
                .deleteYn(deleteYn)
                .boardId(boardId)
                .build();
    }
}
