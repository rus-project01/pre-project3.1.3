package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller // /api/user/{id}
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleServiceImpl;

    @GetMapping(value = "/")
    public String printUsers(HttpServletRequest httpServletRequest, ModelMap model) {
        User users = new User();
        model.addAttribute("userk", users);
        model.addAttribute("enter", userServiceImpl.findUserByName((String)httpServletRequest.getSession().getAttribute("enterUser")));
        model.addAttribute("listRoles", roleServiceImpl.listRoles());
        model.addAttribute("listPersons", userServiceImpl.listUsers());
        return "users";
    }
}
