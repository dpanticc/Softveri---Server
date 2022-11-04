/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mec;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Mec;
import domain.StatistikaIgraca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddMec extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Mec)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mec!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long mecID = tableKeys.getLong(1);
        
        Mec m = (Mec) ado;
        m.setMecID(mecID);

        for (StatistikaIgraca si : m.getStavkeStatistike()) {
            si.setMec(m);
            DBBroker.getInstance().insert(si);
        }
    }

}
