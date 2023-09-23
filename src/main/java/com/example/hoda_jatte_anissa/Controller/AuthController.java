package com.example.hoda_jatte_anissa.Controller;
import com.example.hoda_jatte_anissa.DAO.UserDao;
import com.example.hoda_jatte_anissa.Entity.User;
import com.example.hoda_jatte_anissa.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class AuthController {
    private UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

   /*@GetMapping("/confirmation")
    public String conf(){
        return "confirmation";
    }*/

   /* @GetMapping("/index1")
    public String Index1(){
        return "index1";
    }*/

    /*@GetMapping("/Index-Admin")
    public String Admin(){
        return "Index-Admin";
    }*/
   /*@GetMapping("/DemandeStage")
    public String demandestage(){
        return "DemandeStage";
    }*/
    /*@GetMapping("/DemandeStage")
    public String demandestage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);

        return "DemandeStage";
    }*/

    /*@GetMapping("/demandes")
    public String ListeDemandes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);

        return "demandes";
    }*/

    ///Liste Demande Acceptées//
    /*@GetMapping("/demandes-acceptees")
    public String Liste_demande_Acceptées(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);

        return "demandes-acceptees";
    }*/


    /*@GetMapping("/dashboard_2")
    public String dash(){
        return "dashboard_2";
    }*/

    @GetMapping("/dashboardAdmin")
    public String dashboard_admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();
        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        return "dashboardAdmin";
    }

    @GetMapping("/dashboardUser")
    public String dashboard_user(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();
        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        return "dashboardUser";
    }



    /*@GetMapping("/form")
    public String demandestage(){
        return "form";
    }*/
    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }



    @RequestMapping("/Acceuil")
    public String AcceuilForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        /*Ajouter le nom de l'utilisateur connecté au modèle*/
        String username = authentication.getName();
        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        if (userRole.equals("Admin")) {
            return "Index-Admin";
        } else if (userRole.equals("User")) {
            return "Index-User";
        }
        else
            return "redirect:/login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDao user = new UserDao();
        model.addAttribute("user", user);
        return "register";
    }


    // handler method to handle register user form submit request
    /*@PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }*/







    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDao user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        // Determine the role based on the selectedRole value
        String roleName = user.getSelectedRole().equals("Admin") ? "ROLE_ADMIN" : "ROLE_USER";

        // Save the user with the appropriate role
        userService.saveUser(user,roleName);

        return "redirect:/register?success";
    }



    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDao> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }



    /*@PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes) {
        User user = userService.findByEmail(username);

        if (user != null && isPasswordCorrect(user, password)) {
            // Utilize Spring Security's built-in mechanisms for handling authentication and redirection.
            return "redirect:/users";
        } else {
            // Handle unsuccessful login (invalid email/password) and redirect back to the login page.
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        }
    }*/




    // Utility method to check if the provided password matches the stored password.
    private boolean
    isPasswordCorrect(User user, String password) {
        return userService.checkPassword(user, password);
    }
}

