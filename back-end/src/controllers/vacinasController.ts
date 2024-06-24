import knex from 'knex';
import { Request, Response } from 'express';

const knexConfig = require('../../knexfile');

const db = knex(knexConfig.development);

export const getAllVacinas = async (req: Request, res: Response) => {
    const cpf = req.query.cpf as string;

    if (!cpf) {
        return res.status(400).json({ message: 'CPF é necessário' });
    }

    try {
        const idoso = await db('idoso').where({ cpf }).first();
        if (!idoso) {
            return res.status(404).json({ message: 'Idoso não encontrado' });
        }

        const currentTime = new Date();

        const vacinas = await db('agendamento')
            .join('vacina', 'agendamento.vacina_id', 'vacina.id')
            .select('vacina.nome', 'vacina.descricao', 'agendamento.data_hora_visita')
            .where('agendamento.idoso_id', idoso.id)
            .where('agendamento.data_hora_visita', '>', currentTime)
            .orderBy('agendamento.data_hora_visita', 'desc');

        if (vacinas.length === 0) {
            return res.status(200).json({ message: 'O idoso não tem vacinas agendadas' });
        }

        return res.status(200).json(vacinas);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar vacinas' });
    }
};


export const getAllVacinasTodas = async (req: Request, res: Response) => {
    try {
        const vacinas = await db('vacina').select('nome', 'descricao');

        return res.status(200).json(vacinas);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar vacinas' });
    }
};

