package org.kyungmin0729.models.board.config;

import lombok.RequiredArgsConstructor;
import org.kyungmin0729.commons.constants.BoardAuthority;
import org.kyungmin0729.controllers.admins.BoardConfigForm;
import org.kyungmin0729.entities.Board;
import org.kyungmin0729.repositories.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {
    private final BoardRepository boardRepository;

    public void save(BoardConfigForm form) {

        String bId = form.getBId();
        String mode = form.getMode();
        Board board = null;

        if (mode.equals("edit") && StringUtils.hasText(bId)) {
            board = boardRepository.findById(bId).orElseThrow(BoardNotFoundException::new); // ::new == () -> new
        } else { // 추가 될 때만 기능
            board = new Board();
            board.setBId(bId);
        }

        board.setBName(form.getBName());
        board.setActive(form.isActive());
        board.setAuthority(BoardAuthority.valueOf(form.getAuthority()));
        board.setCategory(form.getCategory());

        boardRepository.saveAndFlush(board);

    }
}
