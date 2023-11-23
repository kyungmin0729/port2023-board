package org.kyungmin0729.controllers.admins;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kyungmin0729.commons.ScriptExceptionProcess;
import org.kyungmin0729.commons.menus.Menu;
import org.kyungmin0729.commons.menus.MenuDetail;
import org.kyungmin0729.models.board.config.BoardConfigSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller("adminBoardController")
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class BoardController implements ScriptExceptionProcess { // 동적 예외발생 기능

    private final HttpServletRequest request;
    private final BoardConfigSaveService saveService;

    @GetMapping
    public String List (Model model) {
        commonProcess("list", model);

        return "admin/board/list";
    }

    @GetMapping("/add")
    public String register(@ModelAttribute BoardConfigForm form, Model model) {
        commonProcess("add", model);


        return "admin/board/add";
    }

    @GetMapping("/edit/{bId}")
    public String update(@PathVariable String bId, Model model) {
        commonProcess("edit", model);


        return "admin/board/edit";
    }

    @PostMapping("/save")
    public String save(@Valid BoardConfigForm form, Errors errors, Model model) {

        String mode = form.getMode();
        commonProcess(mode, model);

        if (errors.hasErrors()) {
            return  "admin/board/" + mode;
        }

        saveService.save(form);

        return "redirect:/admin/board";
    }

    private void commonProcess(String mode, Model model) {
        String pageTitle = "게시판 목록";

        mode = Objects.requireNonNullElse(mode, "list");

        if (mode.equals("add")) pageTitle = "게시판 등록";
        else if (mode.equals("edit")) pageTitle = "게시판 수정";
        /**
         * 추가 메뉴 등록
         * else if (mode.equals("your menu")) pageTilte = "your menu";
         */

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("menuCode","board");
        model.addAttribute("submenus", Menu.gets("board"));
        model.addAttribute("subMenuCode", Menu.getSubMenuCode(request));
    }
}


















