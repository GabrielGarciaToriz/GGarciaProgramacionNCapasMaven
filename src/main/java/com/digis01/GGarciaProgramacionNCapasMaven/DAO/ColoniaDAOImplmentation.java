package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Colonia;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Municipio;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplmentation implements IColonia {

    @Autowired
    private JdbcTemplate JdbcTemplate;

    @Override
    public Result GetAll(int IdMunicipio) {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            JdbcTemplate.execute("{CALL municipiocoloniabyid(?,?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, IdMunicipio);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                while (resultSet.next()) {
                    Colonia colonia = new Colonia();
                    colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    colonia.setNombre(resultSet.getString("Colonia"));
                    colonia.setCodigoPostal(resultSet.getString("CP"));
                    Municipio municipio = new Municipio();
                    municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                    colonia.setMunicipio(municipio);
                    result.objects.add(colonia);
                }
                return result.correct;
            });
        } catch (Exception e) {
        }

        return result;
    }

}
