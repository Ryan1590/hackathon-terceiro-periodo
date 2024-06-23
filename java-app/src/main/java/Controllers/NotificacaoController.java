package Controllers;

import Models.NotificacaoModel;
import java.util.List;

public class NotificacaoController {

    public List<String[]> getInformacoesCompletas() {
        return NotificacaoModel.getInformacoesCompletas();
    }
}
