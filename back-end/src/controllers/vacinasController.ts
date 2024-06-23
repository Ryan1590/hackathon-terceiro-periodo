import knex from 'knex';
import { Request, Response } from 'express';

const knexConfig = require('../../knexfile');

const db = knex(knexConfig.development);




















export const getAllVacinas = async (req: Request, res: Response) => {
    const cpf = req.query.cpf;  // Certifique-se de que está lendo o parâmetro corretamente

    if (!cpf) {
        return res.status(400).json({ message: 'CPF é necessário' });
    }

    try {
        // Primeiro, busque o idoso pelo CPF
        const idoso = await db('idoso').where({ cpf }).first();
        if (!idoso) {
            return res.status(404).json({ message: 'Idoso não encontrado' });
        }

        // Em seguida, busque os agendamentos e vacinas associadas ao idoso
        const vacinas = await db('agendamento')
            .join('vacina', 'agendamento.vacina_id', 'vacina.id')
            .select('vacina.nome', 'vacina.descricao', 'agendamento.data_hora_visita')
            .where('agendamento.idoso_id', idoso.id);

        return res.status(200).json(vacinas);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar vacinas' });
    }
};



