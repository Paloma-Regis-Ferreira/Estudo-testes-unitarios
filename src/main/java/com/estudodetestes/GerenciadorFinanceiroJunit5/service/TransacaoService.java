package com.estudodetestes.GerenciadorFinanceiroJunit5.service;

import com.estudodetestes.GerenciadorFinanceiroJunit5.barriga.domain.Transacao;
import com.estudodetestes.GerenciadorFinanceiroJunit5.exceptions.ValidationException;
import com.estudodetestes.GerenciadorFinanceiroJunit5.service.external.RelogioService;
import com.estudodetestes.GerenciadorFinanceiroJunit5.service.repositories.TransacaoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class TransacaoService {

    private TransacaoDAO dao;
    private RelogioService relogio;

    public Transacao salvar(Transacao transacao){
        if (LocalDateTime.now().getHour() > 8){
            throw new RuntimeException("Tente novamente amanhã");
        }
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);
    }

    public Transacao salvarDois(Transacao transacao){
        if (relogio.getHoraAtual().getHour() > 8){
            throw new RuntimeException("Tente novamente amanhã");
        }
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);
    }
    public Transacao salvarTres(Transacao transacao){
        if (getTime().getHour() > 8){
            throw new RuntimeException("Tente novamente amanhã");
        }
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);
    }

    public Transacao salvarQuatro(Transacao transacao){

        if (getTime().getHour() > 8){
            throw new RuntimeException("Tente novamente amanhã");
        }
        validarMetodoPrivate(transacao);
        return dao.salvar(transacao);
    }

    protected LocalDateTime getTime(){
        return LocalDateTime.now();
    }

    private void validarMetodoPrivate(Transacao transacao){
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);
    }

        public void doSomethingWithMap(Map<String,Object> map) throws SQLException {
        for (String key : map.keySet()) {  // Noncompliant; for each key the value is retrieved
            Object value = map.get(key);

            Connection conn = DriverManager.getConnection("jdbc:derby:memory:myDB;create=true", "login", ""); // Noncompliant

        }
    }
}
