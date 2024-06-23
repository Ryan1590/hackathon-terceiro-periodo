import express from 'express';
import { getAllVacinas, getAllVacinasTodas } from '../controllers/vacinasController';

const router = express.Router();

router.get('/', getAllVacinas);

router.get('/todasvacinas', getAllVacinasTodas);

export default router;
