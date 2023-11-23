package org.kyungmin0729.models.board.config;

import org.kyungmin0729.commons.Utils;
import org.kyungmin0729.commons.exceptions.AlertBackException;
import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends AlertBackException {

    public BoardNotFoundException() {
        super(Utils.getMessage("NotFound.board", "error"));
        setStatus(HttpStatus.NOT_FOUND); // 404 에러코드로 변경
    }
}
