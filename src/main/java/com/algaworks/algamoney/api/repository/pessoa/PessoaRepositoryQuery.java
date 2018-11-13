package com.algaworks.algamoney.api.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}
