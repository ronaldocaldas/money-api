package com.example.money.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.money.api.model.Lancamento;
import com.example.money.api.model.Pessoa;
import com.example.money.api.repository.LancamentoRepository;
import com.example.money.api.repository.PessoaRepository;
import com.example.money.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscaLancamentoExistente(codigo);
		
		if(!lancamentoSalvo.getPessoa().equals(lancamento.getPessoa())) {
			validarPessoa(lancamento);
		}
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo"); 
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Lancamento buscaLancamentoExistente(Long codigo) {
		Lancamento  lancamento =  lancamentoRepository.findOne(codigo); 
		if(lancamento == null) {
			throw new IllegalArgumentException();
		}
		return lancamento;
	}
}
