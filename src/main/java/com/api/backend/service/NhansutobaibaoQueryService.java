package com.api.backend.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.api.backend.domain.Nhansutobaibao;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.NhansutobaibaoRepository;
import com.api.backend.service.dto.NhansutobaibaoCriteria;
import com.api.backend.service.dto.NhansutobaibaoDTO;
import com.api.backend.service.mapper.NhansutobaibaoMapper;

/**
 * Service for executing complex queries for {@link Nhansutobaibao} entities in the database.
 * The main input is a {@link NhansutobaibaoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NhansutobaibaoDTO} or a {@link Page} of {@link NhansutobaibaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NhansutobaibaoQueryService extends QueryService<Nhansutobaibao> {

    private final Logger log = LoggerFactory.getLogger(NhansutobaibaoQueryService.class);

    private final NhansutobaibaoRepository nhansutobaibaoRepository;

    private final NhansutobaibaoMapper nhansutobaibaoMapper;

    public NhansutobaibaoQueryService(NhansutobaibaoRepository nhansutobaibaoRepository, NhansutobaibaoMapper nhansutobaibaoMapper) {
        this.nhansutobaibaoRepository = nhansutobaibaoRepository;
        this.nhansutobaibaoMapper = nhansutobaibaoMapper;
    }

    /**
     * Return a {@link List} of {@link NhansutobaibaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NhansutobaibaoDTO> findByCriteria(NhansutobaibaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Nhansutobaibao> specification = createSpecification(criteria);
        return nhansutobaibaoMapper.toDto(nhansutobaibaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NhansutobaibaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NhansutobaibaoDTO> findByCriteria(NhansutobaibaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Nhansutobaibao> specification = createSpecification(criteria);
        return nhansutobaibaoRepository.findAll(specification, page)
            .map(nhansutobaibaoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NhansutobaibaoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Nhansutobaibao> specification = createSpecification(criteria);
        return nhansutobaibaoRepository.count(specification);
    }

    /**
     * Function to convert {@link NhansutobaibaoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Nhansutobaibao> createSpecification(NhansutobaibaoCriteria criteria) {
        Specification<Nhansutobaibao> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Nhansutobaibao_.id));
            }
            if (criteria.getStt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStt(), Nhansutobaibao_.stt));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), Nhansutobaibao_.sudung));
            }
            if (criteria.getDanhsachbaibaoId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanhsachbaibaoId(),
                    root -> root.join(Nhansutobaibao_.danhsachbaibao, JoinType.LEFT).get(Danhsachbaibao_.id)));
            }
            if (criteria.getNhansuId() != null) {
                specification = specification.and(buildSpecification(criteria.getNhansuId(),
                    root -> root.join(Nhansutobaibao_.nhansu, JoinType.LEFT).get(Nhansu_.id)));
            }
        }
        return specification;
    }
}
