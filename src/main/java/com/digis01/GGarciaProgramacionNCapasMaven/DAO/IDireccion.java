/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.GGarciaProgramacionNCapasMaven.DAO;

import com.digis01.GGarciaProgramacionNCapasMaven.ML.Result;
import com.digis01.GGarciaProgramacionNCapasMaven.ML.Direccion;

public interface IDireccion {

    Result DireccionAdd(Direccion direccion, int IdUsuario);

    Result DireccionModify(Direccion direccion, int IdUsuario);
}
