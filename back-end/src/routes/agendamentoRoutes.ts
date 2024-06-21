import express from 'express';
const router = express.Router();
import { createAgendamento,
         deleteAgendamento,
         getAgendamentosByCpf,
         getAgendamentosByCpfAndVacina,
         getAgendamentosByVacina,
         updateAgendamento,
       }
from '../controllers/agendamentoController';

router.post('/', createAgendamento);
router.delete('/:id', deleteAgendamento);
router.get('/cpf/:cpf', getAgendamentosByCpf);
router.get('/cpf/:cpf/vacina/:nomeVacina', getAgendamentosByCpfAndVacina);
router.put('/:id', updateAgendamento);
router.get('/vacina/:nomeVacina', getAgendamentosByVacina);

export default router;
