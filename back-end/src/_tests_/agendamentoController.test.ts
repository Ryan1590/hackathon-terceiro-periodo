import { Request, Response } from 'express';
import { deleteAgendamento } from '../controllers/agendamentoController';
import knex from 'knex';
import config from '../../knexfile';
import path from 'path';

const db = knex(config.test);

const mockRequest = (params: any) => ({ params } as Request);
const mockResponse = () => {
  const res = {} as Response;
  res.status = jest.fn().mockReturnValue(res);
  res.json = jest.fn().mockReturnValue(res);
  return res;
};

// Limpar o banco de dados após os testes
afterAll(async () => {
  await db.migrate.rollback({
    directory: path.resolve(__dirname, '../database/knex/migrations')
  });
  await db.destroy();
});


describe('deleteAgendamento', () => {
    test('deve retornar 404 se o agendamento não for encontrado', async () => {
      const req = mockRequest({ id: 999 });
      const res = mockResponse();
  
      await deleteAgendamento(req, res);
  
      expect(res.status).toHaveBeenCalledWith(404);
      expect(res.json).toHaveBeenCalledWith({ message: 'Agendamento não encontrado' });
    });
  
    test('deve retornar 400 se o agendamento foi aceito pelo agente', async () => {
      const req = mockRequest({ id: 2 });
      const res = mockResponse();
  
      await deleteAgendamento(req, res);
  
      expect(res.status).toHaveBeenCalledWith(400);
      expect(res.json).toHaveBeenCalledWith({ message: 'Não é possível excluir um agendamento que já foi aceito pelo agente.' });
    });

  });
  

export const criarAgendamento = async (dadosAgendamento: any) => {
    const { idoso_id } = dadosAgendamento;
  
    // Verifica se o idoso existe
    const idosoExiste = await db('idoso').where({ id: idoso_id }).first();
    if (!idosoExiste) {
      throw new Error('Idoso não encontrado');
    }
  
    // Se o idoso existe, insere o agendamento
    await db('agendamento').insert(dadosAgendamento);
  };
  
  // Novo teste para verificar se o sistema retorna erro ao não encontrar o idoso
  describe('Inserção de Agendamento', () => {
    test('Deve retornar erro se o idoso não for encontrado', async () => {
      
      const dadosAgendamento = {
        id: 4,
        nome: 'Maria',
        idoso_id: 999, // Idoso inexistente
        data_hora_visita: new Date(),
        nomeVacina: 'Vacina D',
        status: 'Pendente'
      };
  

      await expect(criarAgendamento(dadosAgendamento)).rejects.toThrow('Idoso não encontrado');
    });
  });
  