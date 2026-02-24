package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //PORQUE TIENE ACCESO A LA BASE DE DATOS
public class DireccionDAOImplementation implements IDireccion {

    @Autowired
    private JdbcTemplate JdbcTemplate;

    @Override
    public Result DireccionAdd(Direccion direccion, int IdUsuario) {
        Result result = new Result();
        try {
            JdbcTemplate.execute("{CALL DIREECIONADDSP(?,?,?,?,?)}", (CallableStatementCallback< Boolean>) callableStatement -> {
                callableStatement.setString(1, direccion.getCalle());
                callableStatement.setString(2, direccion.getNumeroExterior());
                callableStatement.setString(3, direccion.getNumeroInterior());
                callableStatement.setInt(4, direccion.getColonia().getIdColonia());
                callableStatement.setInt(5, IdUsuario);
                callableStatement.execute();
                return result.correct = true;
            });

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result DireccionModify(Direccion direccion, int IdUsuario) {
        Result result = new Result();
        try {
            JdbcTemplate.execute("{CALL modifydireccionsp(?,?,?,?,?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
                callableStatement.setString(1, direccion.getCalle());
                callableStatement.setString(2, direccion.getNumeroExterior());
                callableStatement.setString(3, direccion.getNumeroInterior());
                callableStatement.setInt(4, direccion.getColonia().getIdColonia());
                callableStatement.setInt(5, IdUsuario);
                callableStatement.execute();
                return result.correct = true;

            });
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }
}
