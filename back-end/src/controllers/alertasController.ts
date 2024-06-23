import { Request, Response } from 'express';
import db from '../database/db';

export const getAllAlertas = async (req: Request, res: Response) => {
    const cpf = req.query.cpf;

    if (!cpf) {
        return res.status(400).json({ message: 'CPF é necessário' });
    }

    try {
        const idoso = await db('idoso').where({ cpf }).first();
        if (!idoso) {
            return res.status(404).json({ message: 'Idoso não encontrado' });
        }

        const alertas = await db('alertas as al')
            .leftJoin('agendamento as a', 'al.agendamento_id', 'a.id')
            .leftJoin('idoso as i', 'a.idoso_id', 'i.id')
            .leftJoin('vacina as v', 'a.vacina_id', 'v.id')
            .select('i.nome as nomeIdoso', 'v.nome as nomeVacina', 'al.mensagem')
            .where('i.id', idoso.id);

        if (alertas.length === 0) {
            return res.status(404).json({ message: 'O idoso não tem alertas agendados' });
        }

        return res.status(200).json(alertas);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Erro ao buscar alertas' });
    }
};
