import express from 'express';
import { getAllAlertas } from '../controllers/alertasController';

const router = express.Router();

router.get('/', getAllAlertas);

export default router;
