import knex from 'knex';
import { Request, Response } from 'express';

const knexConfig = require('../../knexfile');

const db = knex(knexConfig.development);

export const getAllVacinas = async (req: Request, res: Response) => {
    try {
        const vacinas = await db('vacina').select('nome');
        return res.status(200).json(vacinas);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar vacinas' });
    }
};
