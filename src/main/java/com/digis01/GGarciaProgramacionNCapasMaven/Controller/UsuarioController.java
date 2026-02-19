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

    /*
        Carga los datos de todos los usuarios en una vsita para seleccionar si se deben de editar o eliminar
        - Falta 
     */
    @GetMapping("")
    public String Usuario(Model model) {
        Result result = usuarioDAOImplementation.GetAll();
        model.addAttribute("usuarios", result.objects);
        return "Usuario";
    }

    /*Carga en la vista los datos de los roles,paises y el modelo de usuario*/
    @GetMapping("form")
    public String FormularioUsuario(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        return "UsuarioForm";
    }

    /*
        Envia los datos del usuario y su direccion a la base de datos
        - Falta checar a detalle las validaciones del lado del servidor
        - Cargar los datos nuevamente en caso de que el cliente tenga algun fallo
        - Mostrar si el formulario esta llenado correcta o incorrectamente del lado del cliente
     */
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

    /*Envia los datos del usuario a la vista detalle para su edicion o eliminacion*/
    @GetMapping("detail/{IdUsuario}")
    public String DetalleUsuario(@PathVariable("IdUsuario") int IdUsuario, Model model) {
        Result result = usuarioDAOImplementation.GetAllById(IdUsuario);
        model.addAttribute("usuario", result.objects.get(0));
        return "UsuarioDetail";
    }

    /*Elimina una direecion */
    @PostMapping("detail/{IdDireccion}")
    public String EliminarDireccion(@PathVariable("IdDireccion") int IdDIreccion, Model model) {

        return "UsuarioDetail";
    }

    /*Cargar los datos del estado */
    @GetMapping("getEstadoByPais/{IdPais}")
    @ResponseBody
    public Result getEstadoByPais(@PathVariable("IdPais") int IdPais) {
        Result result = estadoDAOImplementation.GetAll(IdPais);
        return result;
    }

    /*Cargar los datos del municipio*/
    @GetMapping("getMunicipioByEstado/{IdEstado}")
    @ResponseBody
    public Result getMunicipioByEstado(@PathVariable("IdEstado") int IdEstado) {
        Result result = municipioDAOImplementation.GetAll(IdEstado);
        return result;
    }

    /*Cargar los datos del colonia*/
    @GetMapping("getColoniabyMunicipio/{IdMunicipio}")
    @ResponseBody
    public Result getColoniabyMunicipio(@PathVariable("IdMunicipio") int IdMunicipio) {
        Result result = coloniaDAOImplmentation.GetAll(IdMunicipio);
        return result;
    }

    /*Buscar la colonia usando el codigo postal*/
    @GetMapping("getDireccionByCodigoPostal/{CodigoPostal}")
    @ResponseBody
    public Result getDireccionByCodigoPostal(@PathVariable("CodigoPostal") String CodigoPostal) {
        Result result = coloniaDAOImplmentation.GetByCodigoPostal(CodigoPostal);
        return result;
    }

}
