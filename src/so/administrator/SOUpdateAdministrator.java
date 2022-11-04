/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.administrator;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Administrator;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateAdministrator extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
        
        Administrator a = (Administrator) ado;
        
        ArrayList<Administrator> administratori 
                = (ArrayList<Administrator>)(ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Administrator administrator : administratori) {
            if(a.getUsername().equals(administrator.getUsername()))
                throw new Exception("Vec postoji taj username!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().update(ado);
    }

}
