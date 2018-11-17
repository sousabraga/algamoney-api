package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		
		if (pessoa == null || !pessoa.isAtivo()) 
			throw new PessoaInexistenteOuInativaException();
		
		return lancamentoRepository.save(lancamento);
	}
	
	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoPorId(id);
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
		
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	private Lancamento buscarLancamentoPorId(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		
		if (lancamentoSalvo == null)
			throw new EmptyResultDataAccessException(1);
		
		return lancamentoSalvo;
	}
	
}
