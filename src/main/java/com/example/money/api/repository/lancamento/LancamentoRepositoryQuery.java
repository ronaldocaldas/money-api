package com.example.money.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.money.api.model.Lancamento;
import com.example.money.api.repository.filter.LancamentoFilter;
import com.example.money.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}