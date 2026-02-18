package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //PORQUE TIENE ACCESO A LA BASE DE DATOS
public class DireccionDAOImplementation implements IDireccion {

    @Autowired
    private JdbcTemplate JdbcTemplate;

    @Override
    public Result DireccionAdd(int IdUsuario) {
        Result result = new Result();
        return result;
    }

}
