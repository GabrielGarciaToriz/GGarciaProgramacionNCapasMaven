package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //PORQUE TIENE ACCESO A LA BASE DE DATOS
public class DireccionDAOImplementation implements IDireccion {

    @Autowired
    private JdbcTemplate JdbcTemplate;

    @Override
    public Result DireccionAdd(String Calle, String NumeroExterior, String NumeroInterior, int IdColonia, int IdUsuario) {
        Result result = new Result();
        try {
            JdbcTemplate.execute("{CALL DIREECIONADDSP(?,?,?,?,?)}", (CallableStatementCallback< Boolean>) callableStatement -> {
                callableStatement.setString(1, Calle);
                callableStatement.setString(2, NumeroExterior);
                callableStatement.setString(3, NumeroInterior);
                callableStatement.setInt(4, IdColonia);
                callableStatement.setInt(5, IdUsuario);
                callableStatement.execute();
//                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
//                while (resultSet.next()) {
//
//                }

                return result.correct;
            });

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

}
