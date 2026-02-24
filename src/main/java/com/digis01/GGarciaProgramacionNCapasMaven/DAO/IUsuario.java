package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Usuario;

public interface IUsuario {

    Result GetAll();

    Result GetAllById(int IdUsuario);

    Result DeleteDireccionUsuariobyId(int IdUsuario);

    Result DeleteDireccionById(int IdDireccion);

    Result Add(Usuario usuario);

    Result UsuarioDireccionBusqueda(Usuario usuario);

    Result ModifyUsuario(Usuario usuario);
}
