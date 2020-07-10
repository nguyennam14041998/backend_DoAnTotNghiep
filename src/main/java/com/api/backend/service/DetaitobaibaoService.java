package com.api.backend.service;

import com.api.backend.domain.Detaitobaibao;
import com.api.backend.service.dto.DetaitobaibaoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Detaitobaibao}.
 */
public interface DetaitobaibaoService {

    /**
     * Save a detaitobaibao.
     *
     * @param detaitobaibaoDTO the entity to save.
     * @return the persisted entity.
     */
    DetaitobaibaoDTO save(DetaitobaibaoDTO detaitobaibaoDTO);

    /**
     * Get all the detaitobaibaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetaitobaibaoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detaitobaibao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Detaitobaibao> findOne(Long id);

    /**
     * Delete the "id" detaitobaibao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
