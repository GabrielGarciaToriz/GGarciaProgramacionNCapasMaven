package com.digis01.GGarciaProgramacionNCapasMaven.DAO.JPA;

import com.digis01.GGarciaProgramacionNCapasMaven.DAO.IRol;
import com.digis01.GGarciaProgramacionNCapasMaven.JPA.Rol;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("RolDAOJPA")
public class RolDAOJPAImplementation implements IRol {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<Rol> query = entityManager.createQuery("FROM Rol", Rol.class);
            List<Rol> resultList = query.getResultList();
            result.objects = new ArrayList<>(resultList);
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }
}
