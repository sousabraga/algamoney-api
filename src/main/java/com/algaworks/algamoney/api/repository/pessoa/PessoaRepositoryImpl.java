package com.algaworks.algamoney.api.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = setarFiltros(pessoaFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Pessoa> query = entityManager.createQuery(criteria);

		setarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, getTotal(pessoaFilter));
	}
	
	private Predicate[] setarFiltros(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(pessoaFilter.getNome())) {
			predicates.add(builder.like(
				builder.lower(root.get("nome")), 
				"%" + pessoaFilter.getNome().toLowerCase() + "%"
			));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void setarPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int limiteDeRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * limiteDeRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(limiteDeRegistrosPorPagina);
	}
	
	private Long getTotal(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = setarFiltros(pessoaFilter, builder, root);
		
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return entityManager.createQuery(criteria).getSingleResult();
	}
	
}
