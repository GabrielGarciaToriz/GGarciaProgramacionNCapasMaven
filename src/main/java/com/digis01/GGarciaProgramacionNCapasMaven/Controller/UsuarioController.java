package com.digis01.GGarciaProgramacionNCapasMaven.Controller;

import com.digis01.GGarciaProgramacionNCapasMaven.DAO.ColoniaDAOImplmentation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.RolDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Direccion;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Usuario;
import jakarta.validation.Valid;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplmentation coloniaDAOImplmentation;

    @GetMapping("")
    public String Usuario(Model model) {
        Result result = usuarioDAOImplementation.GetAll();
        model.addAttribute("usuarios", result.objects);
        return "Usuario";
    }

    @GetMapping("form")
    public String FormularioUsuario(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        return "UsuarioForm";
    }

    @PostMapping("form")
    public String FormularioUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("direccion", usuario.Direcciones);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            return "UsuarioForm";
        }
        return "redirect:/Usuario";
    }

    @GetMapping("detail/{IdUsuario}")
    public String DetalleUsuario(@PathVariable("IdUsuario") int IdUsuario, Model model) {
        Result result = usuarioDAOImplementation.GetAllById(IdUsuario);
        model.addAttribute("usuario", result.objects.get(0));
        return "UsuarioDetail";
    }

    @PostMapping("detail/{IdDireccion}")
    public String EliminarDireccion(@PathVariable("IdDireccion") int IdDIreccion, Model model) {
        
        return "UsuarioDetail";
    }

    @GetMapping("getEstadoByPais/{IdPais}")
    @ResponseBody
    public Result getEstadoByPais(@PathVariable("IdPais") int IdPais) {
        Result result = estadoDAOImplementation.GetAll(IdPais);
        return result;
    }

    @GetMapping("getMunicipioByEstado/{IdEstado}")
    @ResponseBody
    public Result getMunicipioByEstado(@PathVariable("IdEstado") int IdEstado) {
        Result result = municipioDAOImplementation.GetAll(IdEstado);
        return result;
    }

    @GetMapping("getColoniabyMunicipio/{IdMunicipio}")
    @ResponseBody
    public Result getColoniabyMunicipio(@PathVariable("IdMunicipio") int IdMunicipio) {
        Result result = coloniaDAOImplmentation.GetAll(IdMunicipio);
        return result;
    }

    @GetMapping("getDireccionByCodigoPostal/{CodigoPostal}")
    @ResponseBody
    public Result getDireccionByCodigoPostal(@PathVariable("CodigoPostal") String CodigoPostal) {
        Result result = coloniaDAOImplmentation.GetByCodigoPostal(CodigoPostal);
        return result;
    }

}
