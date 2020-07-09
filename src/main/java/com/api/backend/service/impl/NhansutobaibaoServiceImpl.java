package com.api.backend.service.impl;

import com.api.backend.service.NhansutobaibaoService;
import com.api.backend.domain.Nhansutobaibao;
import com.api.backend.repository.NhansutobaibaoRepository;
import com.api.backend.service.dto.NhansutobaibaoDTO;
import com.api.backend.service.mapper.NhansutobaibaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Nhansutobaibao}.
 */
@Service
@Transactional
public class NhansutobaibaoServiceImpl implements NhansutobaibaoService {

    private final Logger log = LoggerFactory.getLogger(NhansutobaibaoServiceImpl.class);

    private final NhansutobaibaoRepository nhansutobaibaoRepository;

    private final NhansutobaibaoMapper nhansutobaibaoMapper;

    public NhansutobaibaoServiceImpl(NhansutobaibaoRepository nhansutobaibaoRepository, NhansutobaibaoMapper nhansutobaibaoMapper) {
        this.nhansutobaibaoRepository = nhansutobaibaoRepository;
        this.nhansutobaibaoMapper = nhansutobaibaoMapper;
    }

    /**
     * Save a nhansutobaibao.
     *
     * @param nhansutobaibaoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NhansutobaibaoDTO save(NhansutobaibaoDTO nhansutobaibaoDTO) {
        log.debug("Request to save Nhansutobaibao : {}", nhansutobaibaoDTO);
        Nhansutobaibao nhansutobaibao = nhansutobaibaoMapper.toEntity(nhansutobaibaoDTO);
        nhansutobaibao = nhansutobaibaoRepository.save(nhansutobaibao);
        return nhansutobaibaoMapper.toDto(nhansutobaibao);
    }

    /**
     * Get all the nhansutobaibaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NhansutobaibaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nhansutobaibaos");
        return nhansutobaibaoRepository.findAll(pageable)
            .map(nhansutobaibaoMapper::toDto);
    }


    /**
     * Get one nhansutobaibao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NhansutobaibaoDTO> findOne(Long id) {
        log.debug("Request to get Nhansutobaibao : {}", id);
        return nhansutobaibaoRepository.findById(id)
            .map(nhansutobaibaoMapper::toDto);
    }

    /**
     * Delete the nhansutobaibao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nhansutobaibao : {}", id);
        nhansutobaibaoRepository.deleteById(id);
    }
}
