package com.api.backend.web.rest;

import com.api.backend.domain.Detaitobaibao;
import com.api.backend.service.DetaitobaibaoService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DetaitobaibaoDTO;
import com.api.backend.service.dto.DetaitobaibaoCriteria;
import com.api.backend.service.DetaitobaibaoQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Detaitobaibao}.
 */
@RestController
@RequestMapping("/api")
public class DetaitobaibaoResource {

    private final Logger log = LoggerFactory.getLogger(DetaitobaibaoResource.class);

    private static final String ENTITY_NAME = "detaitobaibao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetaitobaibaoService detaitobaibaoService;

    private final DetaitobaibaoQueryService detaitobaibaoQueryService;

    public DetaitobaibaoResource(DetaitobaibaoService detaitobaibaoService, DetaitobaibaoQueryService detaitobaibaoQueryService) {
        this.detaitobaibaoService = detaitobaibaoService;
        this.detaitobaibaoQueryService = detaitobaibaoQueryService;
    }

    /**
     * {@code POST  /detaitobaibaos} : Create a new detaitobaibao.
     *
     * @param detaitobaibaoDTO the detaitobaibaoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detaitobaibaoDTO, or with status {@code 400 (Bad Request)} if the detaitobaibao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detaitobaibaos")
    public ResponseEntity<DetaitobaibaoDTO> createDetaitobaibao(@RequestBody DetaitobaibaoDTO detaitobaibaoDTO) throws URISyntaxException {
        log.debug("REST request to save Detaitobaibao : {}", detaitobaibaoDTO);
        if (detaitobaibaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new detaitobaibao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetaitobaibaoDTO result = detaitobaibaoService.save(detaitobaibaoDTO);
        return ResponseEntity.created(new URI("/api/detaitobaibaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detaitobaibaos} : Updates an existing detaitobaibao.
     *
     * @param detaitobaibaoDTO the detaitobaibaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detaitobaibaoDTO,
     * or with status {@code 400 (Bad Request)} if the detaitobaibaoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detaitobaibaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detaitobaibaos")
    public ResponseEntity<DetaitobaibaoDTO> updateDetaitobaibao(@RequestBody DetaitobaibaoDTO detaitobaibaoDTO) throws URISyntaxException {
        log.debug("REST request to update Detaitobaibao : {}", detaitobaibaoDTO);
        if (detaitobaibaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetaitobaibaoDTO result = detaitobaibaoService.save(detaitobaibaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detaitobaibaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detaitobaibaos} : get all the detaitobaibaos.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detaitobaibaos in body.
     */
    @GetMapping("/detaitobaibaos")
    public ResponseEntity<List<Detaitobaibao>> getAllDetaitobaibaos(DetaitobaibaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Detaitobaibaos by criteria: {}", criteria);
        Page<Detaitobaibao> page = detaitobaibaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /detaitobaibaos/count} : count all the detaitobaibaos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/detaitobaibaos/count")
    public ResponseEntity<Long> countDetaitobaibaos(DetaitobaibaoCriteria criteria) {
        log.debug("REST request to count Detaitobaibaos by criteria: {}", criteria);
        return ResponseEntity.ok().body(detaitobaibaoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /detaitobaibaos/:id} : get the "id" detaitobaibao.
     *
     * @param id the id of the detaitobaibaoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detaitobaibaoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detaitobaibaos/{id}")
    public ResponseEntity<Detaitobaibao> getDetaitobaibao(@PathVariable Long id) {
        log.debug("REST request to get Detaitobaibao : {}", id);
        Optional<Detaitobaibao> detaitobaibaoDTO = detaitobaibaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detaitobaibaoDTO);
    }

    /**
     * {@code DELETE  /detaitobaibaos/:id} : delete the "id" detaitobaibao.
     *
     * @param id the id of the detaitobaibaoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detaitobaibaos/{id}")
    public ResponseEntity<Void> deleteDetaitobaibao(@PathVariable Long id) {
        log.debug("REST request to delete Detaitobaibao : {}", id);
        detaitobaibaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
