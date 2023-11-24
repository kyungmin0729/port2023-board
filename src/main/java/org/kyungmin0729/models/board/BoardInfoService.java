package org.kyungmin0729.models.board;

import lombok.RequiredArgsConstructor;
import org.kyungmin0729.entities.BoardData;
import org.kyungmin0729.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardInfoService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long seq) {

        BoardData data = boardDataRepository.findById(seq).orElseThrow(BoardDataNotFoundException::new);

        return data;
    }
}