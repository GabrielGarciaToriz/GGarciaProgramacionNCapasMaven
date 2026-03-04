package com.digis01.GGarciaProgramacionNCapasMaven.DAO.InterfaceJPA.JPA;

import com.digis01.GGarciaProgramacionNCapasMaven.DAO.InterfaceJPA.IUsuarioJPA;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Colonia;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Direccion;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Estado;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Municipio;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Pais;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Rol;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.Tuple;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("UsuarioDAOJPA")
public class UsuarioDAOJPAImplementation implements IUsuarioJPA {

    @Autowired
    private EntityManager EntityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("UsuarioDireccionGetAllSP");
            query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
            query.execute();

            List<Object[]> usuarios = query.getResultList();
            for (Object[] usuario : usuarios) {
                int IdUsuario = ((Number) usuario[0]).intValue();

                if (!result.objects.isEmpty() && IdUsuario == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {
                    Direccion direccionML = new Direccion();
                    direccionML.setIdDireccion(((Number) usuario[14]).intValue());
                    direccionML.setCalle((String) usuario[15]);
                    direccionML.setNumeroExterior((String) usuario[16]);
                    direccionML.setNumeroInterior((String) usuario[17]);
                    direccionML.Colonia = new Colonia();
                    direccionML.Colonia.setIdColonia(((Number) usuario[18]).intValue());
                    direccionML.Colonia.setNombre((String) usuario[19]);
                    direccionML.Colonia.setCodigoPostal((String) usuario[20]);
                    direccionML.Colonia.Municipio = new Municipio();
                    direccionML.Colonia.Municipio.setIdMunicipio(((Number) usuario[21]).intValue());
                    direccionML.Colonia.Municipio.setNombre((String) usuario[22]);
                    direccionML.Colonia.Municipio.Estado = new Estado();
                    direccionML.Colonia.Municipio.Estado.setIdEstado(((Number) usuario[23]).intValue());
                    direccionML.Colonia.Municipio.Estado.setNombre((String) usuario[24]);
                    direccionML.Colonia.Municipio.Estado.Pais = new Pais();
                    direccionML.Colonia.Municipio.Estado.Pais.setIdPais(((Number) usuario[25]).intValue());
                    direccionML.Colonia.Municipio.Estado.Pais.setNombre((String) usuario[26]);

                    ((Usuario) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccionML);
                } else {
                    Usuario usuarioML = new Usuario();
                    usuarioML.Rol = new Rol();
                    usuarioML.Direcciones = new ArrayList<>();

                    usuarioML.setIdUsuario(IdUsuario);
                    usuarioML.setNombre((String) usuario[1]);
                    usuarioML.setApellidoPaterno((String) usuario[2]);
                    usuarioML.setApellidoMaterno((String) usuario[3]);
                    if (usuario[4] != null) {
                        LocalDateTime fecha = (LocalDateTime) usuario[4];
                        Date fechaDate = Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
                        usuarioML.setFechaNacimiento(fechaDate);
                    }
                    usuarioML.setCelular((String) usuario[5]);
                    usuarioML.setCurp((String) usuario[6]);
                    usuarioML.setUserName((String) usuario[7]);
                    usuarioML.setEmail((String) usuario[8]);
                    usuarioML.setPassword((String) usuario[9]);
                    usuarioML.setSexo((String) usuario[10]);
                    usuarioML.setTelefono((String) usuario[11]);
                    usuarioML.Rol.setIdRol(((Number) usuario[12]).intValue());
                    usuarioML.Rol.setNombre((String) usuario[13]);

                    Direccion direccionML = new Direccion();
                    direccionML.setIdDireccion(((Number) usuario[14]).intValue());
                    direccionML.setCalle((String) usuario[15]);
                    direccionML.setNumeroExterior((String) usuario[16]);
                    direccionML.setNumeroInterior((String) usuario[17]);
                    direccionML.Colonia = new Colonia();
                    direccionML.Colonia.setIdColonia(((Number) usuario[18]).intValue());
                    direccionML.Colonia.setNombre((String) usuario[19]);
                    direccionML.Colonia.setCodigoPostal((String) usuario[20]);
                    direccionML.Colonia.Municipio = new Municipio();
                    direccionML.Colonia.Municipio.setIdMunicipio(((Number) usuario[21]).intValue());
                    direccionML.Colonia.Municipio.setNombre((String) usuario[22]);
                    direccionML.Colonia.Municipio.Estado = new Estado();
                    direccionML.Colonia.Municipio.Estado.setIdEstado(((Number) usuario[23]).intValue());
                    direccionML.Colonia.Municipio.Estado.setNombre((String) usuario[24]);
                    direccionML.Colonia.Municipio.Estado.Pais = new Pais();
                    direccionML.Colonia.Municipio.Estado.Pais.setIdPais(((Number) usuario[25]).intValue());
                    direccionML.Colonia.Municipio.Estado.Pais.setNombre((String) usuario[26]);
                    usuarioML.setEstatus(((Number) usuario[27]).intValue());
                    usuarioML.Direcciones.add(direccionML);
                    result.objects.add(usuarioML);
                }
            }
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result GetAllById(int IdUsuario) {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("USUARIODIRECCIONGETALLBYIDSP");
            query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.setParameter(2, IdUsuario);
            query.execute();

            List<Object[]> rows = query.getResultList();

            for (Object[] row : rows) {
                int idUsuarioBase = ((Number) row[0]).intValue();

                if (!result.objects.isEmpty() && idUsuarioBase == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {
                    Direccion direccionML = new Direccion();
                    direccionML.setIdDireccion(((Number) row[1]).intValue());
                    direccionML.setCalle((String) row[2]);
                    direccionML.setNumeroExterior((String) row[3]);
                    direccionML.setNumeroInterior((String) row[4]);
                    direccionML.Colonia = new Colonia();
                    direccionML.Colonia.setIdColonia(((Number) row[5]).intValue());
                    direccionML.Colonia.setNombre((String) row[6]);
                    direccionML.Colonia.setCodigoPostal((String) row[7]);
                    direccionML.Colonia.Municipio = new Municipio();
                    direccionML.Colonia.Municipio.setIdMunicipio(((Number) row[8]).intValue());
                    direccionML.Colonia.Municipio.setNombre((String) row[9]);
                    direccionML.Colonia.Municipio.Estado = new Estado();
                    direccionML.Colonia.Municipio.Estado.setIdEstado(((Number) row[10]).intValue());
                    direccionML.Colonia.Municipio.Estado.setNombre((String) row[11]);
                    direccionML.Colonia.Municipio.Estado.Pais = new Pais();
                    direccionML.Colonia.Municipio.Estado.Pais.setIdPais(((Number) row[12]).intValue());
                    direccionML.Colonia.Municipio.Estado.Pais.setNombre((String) row[13]);

                    ((Usuario) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccionML);
                } else {
                    Usuario usuarioML = new Usuario();
                    usuarioML.Rol = new Rol();
                    usuarioML.Direcciones = new ArrayList<>();

                    usuarioML.setIdUsuario(IdUsuario);
                    usuarioML.setNombre((String) row[14]);
                    usuarioML.setApellidoPaterno((String) row[15]);
                    usuarioML.setApellidoMaterno((String) row[16]);
                    usuarioML.setFechaNacimiento((Date) row[17]);
                    usuarioML.setCelular((String) row[18]);
                    usuarioML.setCurp((String) row[19]);
                    usuarioML.setUserName((String) row[20]);
                    usuarioML.setEmail((String) row[21]);
                    usuarioML.setPassword((String) row[22]);
                    usuarioML.setSexo((String) row[23]);
                    usuarioML.setTelefono((String) row[24]);
                    usuarioML.Rol.setIdRol(((Number) row[25]).intValue());
                    usuarioML.Rol.setNombre((String) row[26]);

                    Direccion direccionML = new Direccion();
                    direccionML.setIdDireccion(((Number) row[1]).intValue());
                    direccionML.setCalle((String) row[2]);
                    direccionML.setNumeroExterior((String) row[3]);
                    direccionML.setNumeroInterior((String) row[4]);
                    direccionML.Colonia = new Colonia();
                    direccionML.Colonia.setIdColonia(((Number) row[5]).intValue());
                    direccionML.Colonia.setNombre((String) row[6]);
                    direccionML.Colonia.setCodigoPostal((String) row[7]);
                    direccionML.Colonia.Municipio = new Municipio();
                    direccionML.Colonia.Municipio.setIdMunicipio(((Number) row[8]).intValue());
                    direccionML.Colonia.Municipio.setNombre((String) row[9]);
                    direccionML.Colonia.Municipio.Estado = new Estado();
                    direccionML.Colonia.Municipio.Estado.setIdEstado(((Number) row[10]).intValue());
                    direccionML.Colonia.Municipio.Estado.setNombre((String) row[11]);
                    direccionML.Colonia.Municipio.Estado.Pais = new Pais();
                    direccionML.Colonia.Municipio.Estado.Pais.setIdPais(((Number) row[12]).intValue());
                    direccionML.Colonia.Municipio.Estado.Pais.setNombre((String) row[13]);

                    usuarioML.Direcciones.add(direccionML);
                    result.objects.add(usuarioML);
                }
            }
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result DeleteDireccionUsuariobyId(int IdUsuario) {
        Result result = new Result();

        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("deletedireccionusuariosp");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.setParameter(1, IdUsuario);
            query.execute();
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;

        }
        return result;
    }

    @Override
    public Result DeleteDireccionById(int IdDireccion) {
        Result result = new Result();
        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("deletedireccionbyidsp");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.execute();
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result Add(Usuario usuario) {
        Result result = new Result();
        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("usuariodireccionaddsp");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(4, java.util.Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(12, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(13, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(14, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(15, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(16, Integer.class, ParameterMode.IN);

            query.setParameter(1, usuario.getNombre());
            query.setParameter(2, usuario.getApellidoPaterno());
            query.setParameter(3, usuario.getApellidoMaterno());
            query.setParameter(4, usuario.getFechaNacimiento());
            query.setParameter(5, usuario.getCelular());
            query.setParameter(6, usuario.getCurp());
            query.setParameter(7, usuario.getUserName());
            query.setParameter(8, usuario.getEmail());
            query.setParameter(9, usuario.getPassword());
            query.setParameter(10, usuario.getSexo());
            query.setParameter(11, usuario.getTelefono());
            query.setParameter(12, usuario.getRol().getIdRol());

            Direccion direccion = usuario.getDirecciones().get(0);
            query.setParameter(13, direccion.getCalle());
            query.setParameter(14, direccion.getNumeroExterior());
            query.setParameter(15, direccion.getNumeroInterior());
            query.setParameter(16, direccion.getColonia().getIdColonia());

            query.execute();
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result UsuarioDireccionBusqueda(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result ModifyUsuario(Usuario usuario) {
        Result result = new Result();
        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("usuariomodifysp");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(11, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(12, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(13, Integer.class, ParameterMode.IN);

            query.setParameter(1, usuario.getNombre());
            query.setParameter(2, usuario.getApellidoPaterno());
            query.setParameter(3, usuario.getApellidoMaterno());
            query.setParameter(4, usuario.getCelular());
            query.setParameter(5, usuario.getCurp());
            query.setParameter(6, usuario.getUserName());
            query.setParameter(7, usuario.getEmail());
            query.setParameter(8, usuario.getPassword());
            query.setParameter(9, usuario.getSexo());
            query.setParameter(10, usuario.getTelefono());
            query.setParameter(11, usuario.getFechaNacimiento());
            query.setParameter(12, usuario.getRol().getIdRol());
            query.setParameter(13, usuario.getIdUsuario());
            query.execute();
            result.correct = true;
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public Result CambiarEstatus(int IdUsuario, int Estatus) {
        Result result = new Result();
        try {
            StoredProcedureQuery query = EntityManager.createStoredProcedureQuery("cambiarestatussp");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.setParameter(1, IdUsuario);
            query.setParameter(2, Estatus);
            query.execute();
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result AddAll(List<Usuario> usuarios) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
