package org.iesfm.shop.controller;

import org.iesfm.shop.Client;
import org.iesfm.shop.dao.ClientDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ClientController {

    private ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clients")
    public List<Client> getClient(){
        return clientDAO.list();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clients/{id}")
    public Client getClient(int id){
        if (clientDAO.get(id) != null){
            return clientDAO.get(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "El cliente no existe"
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/clients")
    public void insert(@RequestBody Client client){
        if (!clientDAO.insert(client)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El cliente ya existe"
            );
        }
    }
}
