/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.statistikaIgraca;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StatistikaIgraca;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddStatistikaIgraca extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StatistikaIgraca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StatistikaIgraca!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);
    }

}
