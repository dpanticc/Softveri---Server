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
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllStatistikaIgraca extends AbstractSO {

    private ArrayList<StatistikaIgraca> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StatistikaIgraca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StatistikaIgraca!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> listaStatistika
                = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado);
        lista = (ArrayList<StatistikaIgraca>) (ArrayList<?>) listaStatistika;
    }

    public ArrayList<StatistikaIgraca> getLista() {
        return lista;
    }

}
