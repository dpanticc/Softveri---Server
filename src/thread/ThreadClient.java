/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Igrac;
import domain.Mec;
import domain.StatistikaIgraca;
import domain.Tim;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_ADMINISTRATOR:
                    ServerController.getInstance().addAdministrator((Administrator) req.getData());
                    break;
                case Operation.ADD_IGRAC:
                    ServerController.getInstance().addIgrac((Igrac) req.getData());
                    break;
                case Operation.ADD_MEC:
                    ServerController.getInstance().addMec((Mec) req.getData());
                    break;
                case Operation.ADD_STATISTIKA_IGRACA:
                    ServerController.getInstance().addStatistikaIgraca((StatistikaIgraca) req.getData());
                    break;
                case Operation.ADD_TIM:
                    ServerController.getInstance().addTim((Tim) req.getData());
                    break;
                case Operation.DELETE_ADMINISTRATOR:
                    ServerController.getInstance().deleteAdministrator((Administrator) req.getData());
                    break;
                case Operation.DELETE_IGRAC:
                    ServerController.getInstance().deleteIgrac((Igrac) req.getData());
                    break;
                case Operation.DELETE_TIM:
                    ServerController.getInstance().deleteTim((Tim) req.getData());
                    break;
                case Operation.UPDATE_ADMINISTRATOR:
                    ServerController.getInstance().updateAdministrator((Administrator) req.getData());
                    break;
                case Operation.UPDATE_TIM:
                    ServerController.getInstance().updateTim((Tim) req.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    res.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_GRAD:
                    res.setData(ServerController.getInstance().getAllGrad());
                    break;
                case Operation.GET_ALL_IGRAC:
                    res.setData(ServerController.getInstance().getAllIgrac((Tim) req.getData()));
                    break;
                case Operation.GET_ALL_MEC:
                    res.setData(ServerController.getInstance().getAllMec());
                    break;
                case Operation.GET_ALL_STATISTIKA_IGRACA:
                    res.setData(ServerController.getInstance().getAllStatistikaIgraca((Mec)req.getData()));
                    break;
                case Operation.GET_ALL_TIM:
                    res.setData(ServerController.getInstance().getAllTim());
                    break;
                case Operation.LOGIN:
                    ArrayList<Administrator> lista = ServerController.getInstance().getAllAdministrator();
                    Administrator a = (Administrator) req.getData();
                    for (Administrator administrator : lista) {
                        if (administrator.getUsername().equals(a.getUsername())
                                && administrator.getPassword().equals(a.getPassword())) {
                            res.setData(administrator);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setData(null);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
