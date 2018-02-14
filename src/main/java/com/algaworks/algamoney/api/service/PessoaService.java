package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPorId(id);
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		
		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPorId(id);
		pessoaSalva.setAtivo(ativo);
		
		pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPorId(Long id) {
		Pessoa pessoaSalva = pessoaRepository.findOne(id);
		
		if (pessoaSalva == null)
			throw new EmptyResultDataAccessException(1);
		return pessoaSalva;
	}
	
}
