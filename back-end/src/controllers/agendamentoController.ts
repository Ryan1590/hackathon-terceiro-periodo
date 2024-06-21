import knex from 'knex';
import { Request, Response } from 'express';

const knexConfig = require('../../knexfile');

const db = knex(knexConfig.development);

export const createAgendamento = async (req: Request, res: Response) => {
    try {
        const { cpf, nomeVacina, data_hora_visita } = req.body;

        const idoso = await db('idoso').where({ cpf }).first();
        if (!idoso) {
            return res.status(404).json({ message: 'Idoso não encontrado' });
        }

        const vacina = await db('vacina').where({ nome: nomeVacina }).first();
        if (!vacina) {
            return res.status(404).json({ message: 'Vacina não encontrada' });
        }

        const [agendamentoId] = await db('agendamento').insert({
            idoso_id: idoso.id,
            vacina_id: vacina.id,
            data_hora_visita,
        }).returning('id');

        return res.status(201).json({ message: 'Agendamento criado com sucesso', agendamentoId });
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao criar agendamento' });
    }
};

export const deleteAgendamento = async (req: Request, res: Response) => {
    try {
        const { id } = req.params;

        const rowsDeleted = await db('agendamento').where({ id }).del();

        if (rowsDeleted) {
            return res.status(200).json({ message: 'Agendamento excluído com sucesso' });
        } else {
            return res.status(404).json({ message: 'Agendamento não encontrado' });
        }
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao excluir agendamento' });
    }
};

export const getAgendamentosByCpf = async (req: Request, res: Response) => {
    try {
        const { cpf } = req.params;

        const idoso = await db('idoso').where({ cpf }).first();
        if (!idoso) {
            return res.status(404).json({ message: 'Idoso não encontrado' });
        }

        const agendamentos = await db('agendamento as a')
            .where('a.idoso_id', idoso.id)
            .leftJoin('vacina as v', 'a.vacina_id', 'v.id')
            .leftJoin('agente_saude as ag', 'a.agenteSaude_id', 'ag.id')
            .leftJoin('idoso as i', 'a.idoso_id', 'i.id')
            .select('a.id', 'a.data_hora_visita', 'v.nome as nomeVacina', 'ag.nome as nomeAgente', 'i.nome as nomeIdoso');

        return res.status(200).json(agendamentos);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar agendamentos' });
    }
};

export const getAgendamentosByVacina = async (req: Request, res: Response) => {
    try {
        const { nomeVacina } = req.params;

        const vacina = await db('vacina').where({ nome: nomeVacina }).first();
        if (!vacina) {
            return res.status(404).json({ message: 'Vacina não encontrada' });
        }

        const agendamentos = await db('agendamento as a')
            .where('a.vacina_id', vacina.id)
            .leftJoin('idoso as i', 'a.idoso_id', 'i.id')
            .leftJoin('agente_saude as ag', 'a.agenteSaude_id', 'ag.id')
            .leftJoin('vacina as v', 'a.vacina_id', 'v.id')
            .select('a.id', 'a.data_hora_visita', 'i.nome as nomeIdoso', 'ag.nome as nomeAgente', 'v.nome as nomeVacina');

        return res.status(200).json(agendamentos);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar agendamentos' });
    }
};

export const getAgendamentosByCpfAndVacina = async (req: Request, res: Response) => {
    try {
        const { cpf, nomeVacina } = req.params;

        const idoso = await db('idoso').where({ cpf }).first();
        if (!idoso) {
            return res.status(404).json({ message: 'Idoso não encontrado' });
        }

        const vacina = await db('vacina').where({ nome: nomeVacina }).first();
        if (!vacina) {
            return res.status(404).json({ message: 'Vacina não encontrada' });
        }

        const agendamentos = await db('agendamento as a')
            .where('a.idoso_id', idoso.id)
            .andWhere('a.vacina_id', vacina.id)
            .leftJoin('idoso as i', 'a.idoso_id', 'i.id')
            .leftJoin('agente_saude as ag', 'a.agenteSaude_id', 'ag.id')
            .leftJoin('vacina as v', 'a.vacina_id', 'v.id')
            .select('a.id', 'a.data_hora_visita', 'i.nome as nomeIdoso', 'ag.nome as nomeAgente', 'v.nome as nomeVacina');

        return res.status(200).json(agendamentos);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar agendamentos' });
    }
};

export const updateAgendamento = async (req: Request, res: Response) => {
    try {
        const { id } = req.params;
        const { nomeVacina, data_hora_visita } = req.body;

        const agendamento = await db('agendamento').where({ id }).first();
        if (!agendamento) {
            return res.status(404).json({ message: 'Agendamento não encontrado' });
        }

        let vacina_id = agendamento.vacina_id;
        if (nomeVacina) {
            const vacina = await db('vacina').where({ nome: nomeVacina }).first();
            if (!vacina) {
                return res.status(404).json({ message: 'Vacina não encontrada' });
            }
            vacina_id = vacina.id;
        }

        const rowsUpdated = await db('agendamento').where({ id }).update({ vacina_id, data_hora_visita });

        if (rowsUpdated) {
            return res.status(200).json({ message: 'Agendamento atualizado com sucesso' });
        } else {
            return res.status(404).json({ message: 'Agendamento não encontrado' });
        }
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao atualizar agendamento' });
    }
};


