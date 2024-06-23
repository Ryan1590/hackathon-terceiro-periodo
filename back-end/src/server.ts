// server.ts
import express from 'express';
import cors from 'cors'; // Importando o pacote cors
import bodyParser from 'body-parser';
import agendamentoRoutes from './routes/agendamentoRoutes';
import alertaRoutes from './routes/alertaRoutes';
import vacinasRoutes from './routes/vacinasRoutes';
import { getAllVacinasTodas } from './controllers/vacinasController';

const app = express();
app.use(bodyParser.json());

const port = process.env.PORT || 3000;

app.use(cors()); 

app.use('/agendamentos', agendamentoRoutes);
app.use('/vacinas', vacinasRoutes);
app.use('/alertas', alertaRoutes);
app.use('/todasvacinas', getAllVacinasTodas);

app.listen(port, () => {
    console.log(`Servidor rodando na porta ${port}`);
});
