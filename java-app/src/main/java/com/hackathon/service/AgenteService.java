package com.hackathon.service;

import com.hackathon.dao.AgenteDao;
import com.hackathon.model.Agente;

import java.util.Collections;
import java.util.List;

public class AgenteService {

    public void salvar(Agente agente) {
        try {
            var dao = new AgenteDao();
            if (agente.getId() == null) {
                dao.inserir(agente);
            } else {
                dao.atualizar(agente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Agente> listarAgentes() {
        try {
            var dao = new AgenteDao();
            return dao.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deletar(int id) {
        try {
            var dao = new AgenteDao();
            dao.deletar(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
