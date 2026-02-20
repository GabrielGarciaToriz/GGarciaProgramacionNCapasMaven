package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;

public interface IUsuario {

    Result GetAll();

    Result GetAllById(int IdUsuario);
    
    Result DeleteDireccionUsuariobyId(int IdUsuario);
}
