/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Grad;
import domain.Igrac;
import domain.Mec;
import domain.StatistikaIgraca;
import domain.Tim;
import java.util.ArrayList;
import so.AbstractSO;
import so.administrator.SOAddAdministrator;
import so.administrator.SODeleteAdministrator;
import so.administrator.SOUpdateAdministrator;
import so.administrator.SOGetAllAdministrator;
import so.grad.SOGetAllGrad;
import so.igrac.SOAddIgrac;
import so.igrac.SODeleteIgrac;
import so.igrac.SOGetAllIgrac;
import so.mec.SOAddMec;
import so.mec.SOGetAllMec;
import so.statistikaIgraca.SOAddStatistikaIgraca;
import so.statistikaIgraca.SOGetAllStatistikaIgraca;
import so.tim.SOAddTim;
import so.tim.SODeleteTim;
import so.tim.SOGetAllTim;
import so.tim.SOUpdateTim;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addAdministrator(Administrator administrator) throws Exception {
        AbstractSO aso = new SOAddAdministrator();
        aso.templateExecute(administrator);
    }
    
    public void addIgrac(Igrac igrac) throws Exception {
        AbstractSO aso = new SOAddIgrac();
        aso.templateExecute(igrac);
    }
    
    public void addMec(Mec mec) throws Exception {
        AbstractSO aso = new SOAddMec();
        aso.templateExecute(mec);
    }
    
    public void addStatistikaIgraca(StatistikaIgraca statistikaIgraca) throws Exception {
        AbstractSO aso = new SOAddStatistikaIgraca();
        aso.templateExecute(statistikaIgraca);
    }
    
    public void addTim(Tim tim) throws Exception {
        AbstractSO aso = new SOAddTim();
        aso.templateExecute(tim);
    }

    public void deleteAdministrator(Administrator administrator) throws Exception {
        AbstractSO aso = new SODeleteAdministrator();
        aso.templateExecute(administrator);
    }
    
    public void deleteIgrac(Igrac igrac) throws Exception {
        AbstractSO aso = new SODeleteIgrac();
        aso.templateExecute(igrac);
    }
    
    public void deleteTim(Tim tim) throws Exception {
        AbstractSO aso = new SODeleteTim();
        aso.templateExecute(tim);
    }

    public void updateAdministrator(Administrator administrator) throws Exception {
        AbstractSO aso = new SOUpdateAdministrator();
        aso.templateExecute(administrator);
    }
    
    public void updateTim(Tim tim) throws Exception {
        AbstractSO aso = new SOUpdateTim();
        aso.templateExecute(tim);
    }
    
    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }
    
    public ArrayList<Grad> getAllGrad() throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.templateExecute(new Grad());
        return so.getLista();
    }
    
    public ArrayList<Igrac> getAllIgrac(Tim t) throws Exception {
        SOGetAllIgrac so = new SOGetAllIgrac();
        
        Igrac i = new Igrac();
        i.setTim(t);
        
        so.templateExecute(i);
        return so.getLista();
    }
    
    public ArrayList<Mec> getAllMec() throws Exception {
        SOGetAllMec so = new SOGetAllMec();
        so.templateExecute(new Mec());
        return so.getLista();
    }
    
    public ArrayList<StatistikaIgraca> getAllStatistikaIgraca(Mec m) throws Exception {
        SOGetAllStatistikaIgraca so = new SOGetAllStatistikaIgraca();
        
        StatistikaIgraca si = new StatistikaIgraca();
        si.setMec(m);
        
        so.templateExecute(si);
        return so.getLista();
    }
    
    public ArrayList<Tim> getAllTim() throws Exception {
        SOGetAllTim so = new SOGetAllTim();
        so.templateExecute(new Tim());
        return so.getLista();
    }

}
