import express from 'express';
import { getAllVacinas } from '../controllers/vacinasController';

const router = express.Router();

router.get('/', getAllVacinas);

export default router;
