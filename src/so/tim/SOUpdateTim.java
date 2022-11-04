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
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateTim extends AbstractSO {

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
        DBBroker.getInstance().update(ado);
        
        Tim t = (Tim) ado;
        
        DBBroker.getInstance().delete(t.getIgraci().get(0));
        
        for (Igrac igrac : t.getIgraci()) {
            igrac.setTim(t);
            DBBroker.getInstance().insert(igrac);
        }
        
    }

}
