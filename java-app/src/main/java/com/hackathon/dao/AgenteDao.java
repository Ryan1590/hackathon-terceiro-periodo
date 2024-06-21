package com.hackathon.dao;

import com.hackathon.model.Agente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgenteDao {
    private static List<Agente> agentes = new ArrayList<>();
    private static int currentId = 1;

    public void inserir(Agente agente) {
        agente.setId(currentId++);
        agentes.add(agente);
    }

    public void atualizar(Agente agente) {
        Optional<Agente> existingAgenteOpt = agentes.stream()
                .filter(d -> d.getId().equals(agente.getId()))
                .findFirst();
        if (existingAgenteOpt.isPresent()) {
            Agente existingAgente = existingAgenteOpt.get();
            existingAgente.setNome(agente.getNome());
        } else {
            throw new RuntimeException("Agente not found with id: " + agente.getId());
        }
    }

    public List<Agente> listarTodos() {
        return new ArrayList<>(agentes); // Returning a copy to avoid external modifications
    }

    public void deletar(int id) {
        agentes.removeIf(agente -> agente.getId() == id);
    }
}
