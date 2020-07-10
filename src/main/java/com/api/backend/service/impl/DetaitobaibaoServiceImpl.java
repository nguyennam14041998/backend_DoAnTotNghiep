package com.api.backend.service.impl;

import com.api.backend.service.DetaitobaibaoService;
import com.api.backend.domain.Detaitobaibao;
import com.api.backend.repository.DetaitobaibaoRepository;
import com.api.backend.service.dto.DetaitobaibaoDTO;
import com.api.backend.service.mapper.DetaitobaibaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Detaitobaibao}.
 */
@Service
@Transactional
public class DetaitobaibaoServiceImpl implements DetaitobaibaoService {

    private final Logger log = LoggerFactory.getLogger(DetaitobaibaoServiceImpl.class);

    private final DetaitobaibaoRepository detaitobaibaoRepository;

    private final DetaitobaibaoMapper detaitobaibaoMapper;

    public DetaitobaibaoServiceImpl(DetaitobaibaoRepository detaitobaibaoRepository, DetaitobaibaoMapper detaitobaibaoMapper) {
        this.detaitobaibaoRepository = detaitobaibaoRepository;
        this.detaitobaibaoMapper = detaitobaibaoMapper;
    }

    /**
     * Save a detaitobaibao.
     *
     * @param detaitobaibaoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DetaitobaibaoDTO save(DetaitobaibaoDTO detaitobaibaoDTO) {
        log.debug("Request to save Detaitobaibao : {}", detaitobaibaoDTO);
        Detaitobaibao detaitobaibao = detaitobaibaoMapper.toEntity(detaitobaibaoDTO);
        detaitobaibao = detaitobaibaoRepository.save(detaitobaibao);
        return detaitobaibaoMapper.toDto(detaitobaibao);
    }

    /**
     * Get all the detaitobaibaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetaitobaibaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Detaitobaibaos");
        return detaitobaibaoRepository.findAll(pageable)
            .map(detaitobaibaoMapper::toDto);
    }


    /**
     * Get one detaitobaibao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Detaitobaibao> findOne(Long id) {
        log.debug("Request to get Detaitobaibao : {}", id);
        return detaitobaibaoRepository.findById(id);
//            .map(detaitobaibaoMapper::toDto);
    }

    /**
     * Delete the detaitobaibao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Detaitobaibao : {}", id);
        detaitobaibaoRepository.deleteById(id);
    }
}
