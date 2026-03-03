package com.digis01.GGarciaProgramacionNCapasMaven.DAO.JPA;

import com.digis01.GGarciaProgramacionNCapasMaven.DAO.IColonia;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ColoniaDAOJPA")
public class ColoniaDAOJPAImplementation implements IColonia {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll(int IdMunicipio) {
        Result result = new Result();
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("municipiocoloniabyidsp");
            query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.setParameter(2, IdMunicipio);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public Result GetByCodigoPostal(String CodigoPostal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
