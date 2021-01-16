package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.website.bg.model.MenuModel;
import net.zhqu.website.bg.model.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created By y__ong@outlook.com On 2018/4/4
 *
 * @author xtyong
 */
@Slf4j
@Controller
public class BGIndexController {

    @Autowired
    private MenuService menuService;

    @Value("${net.zhqu.framework.authority}")
    private boolean isAuthority;

    @Autowired private HttpSession session;

    @GetMapping("/bg/index.html")
    public String bgIndex(Model model) {
        String logout = "/logout";
        model.addAttribute("logoutUrl", logout);
        try {
            List<MenuModel> list = menuService.findAllForTree(1, null, isAuthority);
            model.addAttribute("menus", list);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/index";
    }


}

