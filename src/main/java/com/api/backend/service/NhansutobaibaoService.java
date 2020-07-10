package com.api.backend.service;

import com.api.backend.domain.Nhansutobaibao;
import com.api.backend.service.dto.NhansutobaibaoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Nhansutobaibao}.
 */
public interface NhansutobaibaoService {

    /**
     * Save a nhansutobaibao.
     *
     * @param nhansutobaibaoDTO the entity to save.
     * @return the persisted entity.
     */
    NhansutobaibaoDTO save(NhansutobaibaoDTO nhansutobaibaoDTO);

    /**
     * Get all the nhansutobaibaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NhansutobaibaoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhansutobaibao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Nhansutobaibao> findOne(Long id);

    /**
     * Delete the "id" nhansutobaibao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
