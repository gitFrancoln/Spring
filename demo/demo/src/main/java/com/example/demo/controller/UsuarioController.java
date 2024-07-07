package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller 
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
//L
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String mostrar(Model model){
        model.addAttribute("titulo", "Lista de usuarios");
        model.addAttribute("usuarios", usuarioService.getTodosLosUsuarios());
        return "usuarios";
    }
//O
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("usuarios", usuarioService.getTodosLosUsuarios());
        model.addAttribute("usuario", new Usuario());
        return "index";
    }
//G
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String addUsuario(@ModelAttribute Usuario usuario, SessionStatus status) {
        usuarioService.guardarUsuario(usuario);
        status.setComplete();

        return "redirect:/usuarios";
    }
//E
    @RequestMapping (value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value="id")long id) {
            usuarioService.eliminarUsuario(id);
    
        return "redirect:/usuarios";
    }
}
