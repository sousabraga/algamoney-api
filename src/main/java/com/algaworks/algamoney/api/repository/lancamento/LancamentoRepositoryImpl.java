package com.algaworks.algamoney.api.repository.lancamento;

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

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.api.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = setarFiltros(lancamentoFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = entityManager.createQuery(criteria);

		setarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, getTotal(lancamentoFilter));
	}
	
	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		criteria.select(builder.construct(ResumoLancamento.class, 
				root.get("id"), 
				root.get("descricao"), 
				root.get("dataVencimento"), 
				root.get("dataPagamento"), 
				root.get("valor"), 
				root.get("tipo"), 
				root.get("categoria").get("nome"), 
				root.get("pessoa").get("nome")));
		
		Predicate[] predicates = setarFiltros(lancamentoFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteria);
		
		setarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, getTotal(lancamentoFilter));
	}

	private Predicate[] setarFiltros(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(
				builder.lower(root.get("descricao")), 
				"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"
			));
		}
		
		if (lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(
				builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe())
			);
		}
		
        if (lancamentoFilter.getDataVencimentoAte() != null) {
        	predicates.add(
				builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte())
			);
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
	
	private Long getTotal(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = setarFiltros(lancamentoFilter, builder, root);
		
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return entityManager.createQuery(criteria).getSingleResult();
	}
	
}
