/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tim;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Tim;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SODeleteTim extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tim)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tim!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        Tim t = (Tim) ado;
        
        DBBroker.getInstance().delete(t.getIgraci().get(0));
        
        DBBroker.getInstance().delete(ado);
    }

}
