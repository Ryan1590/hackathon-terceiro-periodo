import { Request, Response } from 'express';
import db from '../database/db';

export const getAllAlertas = async (req: Request, res: Response) => {
    try {
        const alertas = await db('alertas as al')
            .leftJoin('agendamento as a', 'al.agendamento_id', 'a.id')
            .leftJoin('idoso as i', 'a.idoso_id', 'i.id')
            .leftJoin('vacina as v', 'a.vacina_id', 'v.id')
            .where('al.tipo', '=', 'web')
            .select('i.nome as nomeIdoso', 'v.nome as nomeVacina', 'al.mensagem');

        return res.status(200).json(alertas);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar alertas' });
    }
};
