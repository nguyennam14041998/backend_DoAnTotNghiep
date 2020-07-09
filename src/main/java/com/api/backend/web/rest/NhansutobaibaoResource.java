package com.api.backend.web.rest;

import com.api.backend.service.NhansutobaibaoService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.NhansutobaibaoDTO;
import com.api.backend.service.dto.NhansutobaibaoCriteria;
import com.api.backend.service.NhansutobaibaoQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.api.backend.domain.Nhansutobaibao}.
 */
@RestController
@RequestMapping("/api")
public class NhansutobaibaoResource {

    private final Logger log = LoggerFactory.getLogger(NhansutobaibaoResource.class);

    private static final String ENTITY_NAME = "nhansutobaibao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhansutobaibaoService nhansutobaibaoService;

    private final NhansutobaibaoQueryService nhansutobaibaoQueryService;

    public NhansutobaibaoResource(NhansutobaibaoService nhansutobaibaoService, NhansutobaibaoQueryService nhansutobaibaoQueryService) {
        this.nhansutobaibaoService = nhansutobaibaoService;
        this.nhansutobaibaoQueryService = nhansutobaibaoQueryService;
    }

    /**
     * {@code POST  /nhansutobaibaos} : Create a new nhansutobaibao.
     *
     * @param nhansutobaibaoDTO the nhansutobaibaoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhansutobaibaoDTO, or with status {@code 400 (Bad Request)} if the nhansutobaibao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nhansutobaibaos")
    public ResponseEntity<NhansutobaibaoDTO> createNhansutobaibao(@RequestBody NhansutobaibaoDTO nhansutobaibaoDTO) throws URISyntaxException {
        log.debug("REST request to save Nhansutobaibao : {}", nhansutobaibaoDTO);
        if (nhansutobaibaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhansutobaibao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhansutobaibaoDTO result = nhansutobaibaoService.save(nhansutobaibaoDTO);
        return ResponseEntity.created(new URI("/api/nhansutobaibaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nhansutobaibaos} : Updates an existing nhansutobaibao.
     *
     * @param nhansutobaibaoDTO the nhansutobaibaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhansutobaibaoDTO,
     * or with status {@code 400 (Bad Request)} if the nhansutobaibaoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhansutobaibaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nhansutobaibaos")
    public ResponseEntity<NhansutobaibaoDTO> updateNhansutobaibao(@RequestBody NhansutobaibaoDTO nhansutobaibaoDTO) throws URISyntaxException {
        log.debug("REST request to update Nhansutobaibao : {}", nhansutobaibaoDTO);
        if (nhansutobaibaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhansutobaibaoDTO result = nhansutobaibaoService.save(nhansutobaibaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhansutobaibaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nhansutobaibaos} : get all the nhansutobaibaos.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhansutobaibaos in body.
     */
    @GetMapping("/nhansutobaibaos")
    public ResponseEntity<List<NhansutobaibaoDTO>> getAllNhansutobaibaos(NhansutobaibaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Nhansutobaibaos by criteria: {}", criteria);
        Page<NhansutobaibaoDTO> page = nhansutobaibaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nhansutobaibaos/count} : count all the nhansutobaibaos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nhansutobaibaos/count")
    public ResponseEntity<Long> countNhansutobaibaos(NhansutobaibaoCriteria criteria) {
        log.debug("REST request to count Nhansutobaibaos by criteria: {}", criteria);
        return ResponseEntity.ok().body(nhansutobaibaoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nhansutobaibaos/:id} : get the "id" nhansutobaibao.
     *
     * @param id the id of the nhansutobaibaoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhansutobaibaoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nhansutobaibaos/{id}")
    public ResponseEntity<NhansutobaibaoDTO> getNhansutobaibao(@PathVariable Long id) {
        log.debug("REST request to get Nhansutobaibao : {}", id);
        Optional<NhansutobaibaoDTO> nhansutobaibaoDTO = nhansutobaibaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhansutobaibaoDTO);
    }

    /**
     * {@code DELETE  /nhansutobaibaos/:id} : delete the "id" nhansutobaibao.
     *
     * @param id the id of the nhansutobaibaoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nhansutobaibaos/{id}")
    public ResponseEntity<Void> deleteNhansutobaibao(@PathVariable Long id) {
        log.debug("REST request to delete Nhansutobaibao : {}", id);
        nhansutobaibaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
