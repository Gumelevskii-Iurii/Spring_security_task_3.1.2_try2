package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller


public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String users(Model model) {
        Collection<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

//    @GetMapping("/addUsers")
//    public String addNewUsers(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "addUsers";
//    }
//
//    @PostMapping("/addUsers")
//    public String saveNewUser(@ModelAttribute("user") User user,
//                              @RequestParam(required = false) String roleAdmin) {
//        Collection<Role> roles = new HashSet<>();
//        roles.add(roleService.getRoleByName("ROLE_USER"));
//        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
//            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
//        }
//        user.setRoles(roles);
//        userService.addUser(user);
//        return "redirect:/admin";
//    }

    @GetMapping("/addUsers")
    public String addUser(Model model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "addUsers";
    }

    @PostMapping("/addUsers")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.addUser(user);

        return "redirect:/admin";
    }



    @GetMapping("deleteUsers/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @GetMapping(value = "/edit/{id}")
//    public String editUser(ModelMap model, @PathVariable("id") Long id) {
////        String<Role> roles = roleService.listRoles();
//        User user = userService.getUserById(id);
//        String lol = user.getRolesString();
//
////        user.getRoles();
//        model.addAttribute("roles", lol);
//        model.addAttribute("user", user);
//
////        Role role = roleService.getRoleByName(user.getRoles().toString());
////        model.addAttribute("role", role);
//        return "changeUsers";
//    }
//
//    @PutMapping(value = "/changeUsers/{id}")
//    public String putEditUser(@ModelAttribute("user") User user) {
//        userService.changeUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("/changeUsers/{id}")
//    public String editPage(@PathVariable("id") Long id, Model model) {
//        Collection<Role> roles = roleService.listRoles();
//        model.addAttribute("roles", roles);
//        model.addAttribute("user", userService.getUserById(id));
//        return "changeUsers";
//    }
//    @PutMapping("/changeUsers/{id}")
//    public String editUser (@ModelAttribute("user") User user) {
////        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userService.changeUser(user);
//
//        return "redirect:/admin";
//    }

//    @GetMapping("changeUsers/{id}")
//    public String pageEditUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user",userService.getUserById(id));
//        model.addAttribute("listRoles",roleService.listRoles());
//        return "changeUsers";
//    }
//
//    @PutMapping("/changeUsers")
//    public String pageEdit(@RequestParam("role") long id,
//                           @Valid User user,
//                           BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "changeUsers";
//        } else {
//            user.setRoles((Collection<Role>) roleService.findByIdRoles(id));
//            userService.changeUser(user);
//            return "redirect:/admin";
//        }
//    }

    @RequestMapping(value = "/changeUsers/{id}",
            method = {RequestMethod.GET, RequestMethod.PUT})
    public String editPage(@PathVariable("id") Long id, Model model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userService.getUserById(id));
        return "/changeUsers";
    }
    @RequestMapping(value = "/changeUsers",
            method=RequestMethod.PUT)
    public String editUser (@ModelAttribute("user") User user) {

        userService.changeUser(user);

        return "redirect:/admin";
    }



}



//            , @ModelAttribute("role") Role role)
//            ,
//                               @RequestParam(required=false) String roleAdmin,
//                               @RequestParam(required=false) String roleUser)

//        Collection<Role> roles = new ArrayList<>();

//        if (roleUser != null && roleUser.equals("ROLE_USER") && (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN"))) {
//            roles.add(roleService.getRoleByName("ROLE_USER"));
//            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
//        } else if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
//            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
//        }
//        else roles.add(roleService.getRoleByName("ROLE_USER"));

//        if (user.getRoles().contains("ROLE_ADMIN")) {
//            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
//        } else if (user.getRoles().contains("ROLE_USER")) {
//            roles.add(roleService.getRoleByName("ROLE_USER"));
//        }



//        roles.add(user.getRolesString());

//        user.setRoles(roles);


//        public String getRolesString() {
//
//        Collection<Role> roles1 = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        for (Role role : roles) {
//            if (role.getName().contains("ROLE_ADMIN")) {
//                roles1.add(roleService.getRoleByName("ROLE_ADMIN"));
//            } else if (role.getName().contains("ROLE_USER")) {
//                sb.append("USER");
//            }
//            sb.append(" ");
//        }
//        return sb.toString();
//    }
//
//
//    th:each="role: ${user.roles}"
//    th:text="${role.name} + ' '






