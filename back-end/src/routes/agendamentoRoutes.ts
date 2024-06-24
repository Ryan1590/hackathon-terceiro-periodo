import express from 'express';
const router = express.Router();
import { createAgendamento,
         deleteAgendamento,
         getAgendamentosByCpf,
         getAgendamentosByCpfAndVacina,
         updateAgendamento,
         getAgendamentoById,
         cancelAgendamento
       }
from '../controllers/agendamentoController';

router.post('/', createAgendamento);
router.delete('/excluir/:id', deleteAgendamento);
router.get('/cpf/:cpf', getAgendamentosByCpf);
router.get('/cpf/:cpf/vacina/:nomeVacina', getAgendamentosByCpfAndVacina);
router.put('/:id', updateAgendamento);
router.get('/id/:id', getAgendamentoById);
router.put('/cancelar/:id', cancelAgendamento);

export default router;
