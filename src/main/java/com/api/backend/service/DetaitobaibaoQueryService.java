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

import com.api.backend.domain.Detaitobaibao;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DetaitobaibaoRepository;
import com.api.backend.service.dto.DetaitobaibaoCriteria;
import com.api.backend.service.dto.DetaitobaibaoDTO;
import com.api.backend.service.mapper.DetaitobaibaoMapper;

/**
 * Service for executing complex queries for {@link Detaitobaibao} entities in the database.
 * The main input is a {@link DetaitobaibaoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DetaitobaibaoDTO} or a {@link Page} of {@link DetaitobaibaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DetaitobaibaoQueryService extends QueryService<Detaitobaibao> {

    private final Logger log = LoggerFactory.getLogger(DetaitobaibaoQueryService.class);

    private final DetaitobaibaoRepository detaitobaibaoRepository;

    private final DetaitobaibaoMapper detaitobaibaoMapper;

    public DetaitobaibaoQueryService(DetaitobaibaoRepository detaitobaibaoRepository, DetaitobaibaoMapper detaitobaibaoMapper) {
        this.detaitobaibaoRepository = detaitobaibaoRepository;
        this.detaitobaibaoMapper = detaitobaibaoMapper;
    }

    /**
     * Return a {@link List} of {@link DetaitobaibaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DetaitobaibaoDTO> findByCriteria(DetaitobaibaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Detaitobaibao> specification = createSpecification(criteria);
        return detaitobaibaoMapper.toDto(detaitobaibaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DetaitobaibaoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DetaitobaibaoDTO> findByCriteria(DetaitobaibaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Detaitobaibao> specification = createSpecification(criteria);
        return detaitobaibaoRepository.findAll(specification, page)
            .map(detaitobaibaoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DetaitobaibaoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Detaitobaibao> specification = createSpecification(criteria);
        return detaitobaibaoRepository.count(specification);
    }

    /**
     * Function to convert {@link DetaitobaibaoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Detaitobaibao> createSpecification(DetaitobaibaoCriteria criteria) {
        Specification<Detaitobaibao> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Detaitobaibao_.id));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), Detaitobaibao_.sudung));
            }
            if (criteria.getDetaiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDetaiId(),
                    root -> root.join(Detaitobaibao_.detai, JoinType.LEFT).get(Detai_.id)));
            }
            if (criteria.getDanhsachbaibaoId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanhsachbaibaoId(),
                    root -> root.join(Detaitobaibao_.danhsachbaibao, JoinType.LEFT).get(Danhsachbaibao_.id)));
            }
        }
        return specification;
    }
}
