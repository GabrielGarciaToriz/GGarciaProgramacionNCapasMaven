package com.digis01.GGarciaProgramacionNCapasMaven.Controller;

import com.digis01.GGarciaProgramacionNCapasMaven.DAO.ColoniaDAOImplmentation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.RolDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Direccion;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Colonia;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Estado;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Municipio;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Pais;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Rol;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Usuario;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        Usuario usuarioBusqueda = new Usuario();
        usuarioBusqueda.setRol(new Rol());
        model.addAttribute("usuarioBusqueda", usuarioBusqueda);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        model.addAttribute("usuarios", usuarioDAOImplementation.GetAll().objects);
        return "Usuario";
    }

    @PostMapping("/buscar")
    public String BuscarUsuario(@ModelAttribute("usuarioBusqueda") Usuario usuarioBusqueda, Model model) {
        Result result = usuarioDAOImplementation.UsuarioDireccionBusqueda(usuarioBusqueda);
        model.addAttribute("usuarioBusqueda", usuarioBusqueda);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        model.addAttribute("usuarios", result.objects);
        return "Usuario";

    }

    /*Carga en la vista los datos de los roles,paises y el modelo de usuario*/
    @GetMapping("form")

    public String FormularioUsuario(Model model) {
        Usuario usuario = new Usuario();
        usuario.setRol(new Rol());
        Direccion direccion = new Direccion();
        Colonia colonia = new Colonia();
        Municipio municipio = new Municipio();
        Estado estado = new Estado();
        Pais pais = new Pais();

        estado.setPais(pais);
        municipio.setEstado(estado);
        colonia.setMunicipio(municipio);
        direccion.setColonia(colonia);

        usuario.setDirecciones(new ArrayList<>());
        usuario.getDirecciones().add(direccion);

        LocalDate fechaMax = LocalDate.now().minusYears(-18);
        model.addAttribute("fechaMaxima", fechaMax.toString());
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
    public String FormularioUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        LocalDate fechaMax = LocalDate.now().minusYears(-18);
        model.addAttribute("fechaMaxima", fechaMax.toString());
        if (usuario.getFechaNacimiento() != null) {
            Calendar fechaMayorEdad = Calendar.getInstance();
            fechaMayorEdad.add(Calendar.YEAR, -18);
            if (usuario.getFechaNacimiento().after(fechaMayorEdad.getTime())) {
                bindingResult.rejectValue("FechaNacimiento", "error.usuario", "Debes der mayor de edad para registrarte");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            try {
                int idPais = usuario.getDirecciones().get(0).getColonia().getMunicipio().getEstado().getPais().getIdPais();
                if (idPais > 0) {
                    model.addAttribute("estados", estadoDAOImplementation.GetAll(idPais).objects);
                }
                int idEstado = usuario.getDirecciones().get(0).getColonia().getMunicipio().getEstado().getIdEstado();
                if (idEstado > 0) {
                    model.addAttribute("municipios", municipioDAOImplementation.GetAll(idEstado));
                }
                int idMunicipio = usuario.getDirecciones().get(0).getColonia().getMunicipio().getIdMunicipio();
                if (idMunicipio > 0) {
                    model.addAttribute("colonias", coloniaDAOImplmentation.GetAll(idMunicipio));
                }
            } catch (Exception e) {
            }
            return "UsuarioForm";
        }
        Result result = usuarioDAOImplementation.Add(usuario);
        if (result.correct) {
            redirectAttributes.addFlashAttribute("mensajeExito", "Usuario registrado con exito");
            return "redirect:/usuario";
        } else {
            model.addAttribute("mensajeError", "Error en la base de datos: " + result.errorMessage);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            return "UsuarioForm";
        }

    }

    /*Envia los datos del usuario a la vista detalle para su edicion o eliminacion*/
    @GetMapping("detail/{IdUsuario}")
    public String DetalleUsuario(@PathVariable("IdUsuario") int IdUsuario, Model model) {
        Result result = usuarioDAOImplementation.GetAllById(IdUsuario);
        model.addAttribute("usuario", result.objects.get(0));
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        return "UsuarioDetail";
    }

    /*Elimina al usuario y sus direccion */
    @PostMapping("detail/delete/{IdUsuario}")
    public String EliminarDireccionUsuario(@PathVariable("IdUsuario") int IdUsaurio, RedirectAttributes redirectAttributes) {
        Result result = usuarioDAOImplementation.DeleteDireccionUsuariobyId(IdUsaurio);
        if (result.correct) {
            redirectAttributes.addFlashAttribute("mensajeExito", "El registro se ha eliminado ");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "Hubo un problema al eliminar: " + result.errorMessage);
        }
        return "redirect:/usuario";
    }

    @PostMapping("detail/delete/direccion/{IdDireccion}")
    public String EliminarDireccion(@PathVariable("IdDireccion") int IdDireccion, RedirectAttributes redirectAttributes) {
        Result result = usuarioDAOImplementation.DeleteDireccionById(IdDireccion);
        if (result.correct) {
            redirectAttributes.addFlashAttribute("mensajeExito", "La dieccion se ha eliminado ");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "Huno un problema al eliminar la direccion: " + result.errorMessage);
        }
        return "redirect:/usuario";
    }

    @PostMapping("/editarUsuario")
    public String EditarUsuario() {
//        Result result = usuarioDAOImplementation.
        return "";
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
