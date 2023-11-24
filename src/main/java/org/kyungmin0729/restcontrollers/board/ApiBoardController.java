package org.kyungmin0729.restcontrollers.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kyungmin0729.commons.exceptions.BadRequestException;
import org.kyungmin0729.commons.rest.JSONData;
import org.kyungmin0729.controllers.boards.BoardForm;
import org.kyungmin0729.entities.BoardData;
import org.kyungmin0729.models.board.BoardInfoService;
import org.kyungmin0729.models.board.BoardSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class ApiBoardController {

    private final BoardSaveService saveService;
    private final BoardInfoService infoService;

    @PostMapping("/write/{bId}")
    public ResponseEntity write(@PathVariable("bId") String bId, @RequestBody @Valid BoardForm form, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
            throw new BadRequestException(message);
        }

        saveService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/view/{seq}")
    public JSONData<BoardData> view(@PathVariable("seq") Long seq) {

        BoardData data = infoService.get(seq);

        return new JSONData<>(data);
    }
}