/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tim;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Igrac;
import domain.Tim;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddTim extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tim)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tim!");
        }
        
        Tim t = (Tim) ado;
        
        if(t.getIgraci().size() != 3){
            throw new Exception("Tim mora imati 3 igraca!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long timID = tableKeys.getLong(1);
        
        Tim t = (Tim) ado;
        t.setTimID(timID);

        for (Igrac igrac : t.getIgraci()) {
            igrac.setTim(t);
            DBBroker.getInstance().insert(igrac);
        }
        
    }

}
