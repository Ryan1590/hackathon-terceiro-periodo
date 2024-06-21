// server.ts
import express from 'express';
import bodyParser from 'body-parser';
import agendamentoRoutes from './routes/agendamentoRoutes';
import alertaRoutes from './routes/alertaRoutes';
import vacinasRoutes from './routes/vacinasRoutes';

const app = express();
app.use(bodyParser.json());

const port = process.env.PORT || 3000;

app.use('/agendamentos', agendamentoRoutes);
app.use('/vacinas', vacinasRoutes);
app.use('/alertas', alertaRoutes);

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
