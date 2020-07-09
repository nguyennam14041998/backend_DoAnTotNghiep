package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Detaitobaibao;
import com.api.backend.domain.Detai;
import com.api.backend.domain.Danhsachbaibao;
import com.api.backend.repository.DetaitobaibaoRepository;
import com.api.backend.service.DetaitobaibaoService;
import com.api.backend.service.dto.DetaitobaibaoDTO;
import com.api.backend.service.mapper.DetaitobaibaoMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DetaitobaibaoCriteria;
import com.api.backend.service.DetaitobaibaoQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.api.backend.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DetaitobaibaoResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DetaitobaibaoResourceIT {

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private DetaitobaibaoRepository detaitobaibaoRepository;

    @Autowired
    private DetaitobaibaoMapper detaitobaibaoMapper;

    @Autowired
    private DetaitobaibaoService detaitobaibaoService;

    @Autowired
    private DetaitobaibaoQueryService detaitobaibaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDetaitobaibaoMockMvc;

    private Detaitobaibao detaitobaibao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DetaitobaibaoResource detaitobaibaoResource = new DetaitobaibaoResource(detaitobaibaoService, detaitobaibaoQueryService);
        this.restDetaitobaibaoMockMvc = MockMvcBuilders.standaloneSetup(detaitobaibaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Detaitobaibao createEntity(EntityManager em) {
        Detaitobaibao detaitobaibao = new Detaitobaibao()
            .sudung(DEFAULT_SUDUNG);
        return detaitobaibao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Detaitobaibao createUpdatedEntity(EntityManager em) {
        Detaitobaibao detaitobaibao = new Detaitobaibao()
            .sudung(UPDATED_SUDUNG);
        return detaitobaibao;
    }

    @BeforeEach
    public void initTest() {
        detaitobaibao = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetaitobaibao() throws Exception {
        int databaseSizeBeforeCreate = detaitobaibaoRepository.findAll().size();

        // Create the Detaitobaibao
        DetaitobaibaoDTO detaitobaibaoDTO = detaitobaibaoMapper.toDto(detaitobaibao);
        restDetaitobaibaoMockMvc.perform(post("/api/detaitobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detaitobaibaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Detaitobaibao in the database
        List<Detaitobaibao> detaitobaibaoList = detaitobaibaoRepository.findAll();
        assertThat(detaitobaibaoList).hasSize(databaseSizeBeforeCreate + 1);
        Detaitobaibao testDetaitobaibao = detaitobaibaoList.get(detaitobaibaoList.size() - 1);
        assertThat(testDetaitobaibao.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createDetaitobaibaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detaitobaibaoRepository.findAll().size();

        // Create the Detaitobaibao with an existing ID
        detaitobaibao.setId(1L);
        DetaitobaibaoDTO detaitobaibaoDTO = detaitobaibaoMapper.toDto(detaitobaibao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetaitobaibaoMockMvc.perform(post("/api/detaitobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detaitobaibaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Detaitobaibao in the database
        List<Detaitobaibao> detaitobaibaoList = detaitobaibaoRepository.findAll();
        assertThat(detaitobaibaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDetaitobaibaos() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detaitobaibao.getId().intValue())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getDetaitobaibao() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get the detaitobaibao
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos/{id}", detaitobaibao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(detaitobaibao.getId().intValue()))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getDetaitobaibaosByIdFiltering() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        Long id = detaitobaibao.getId();

        defaultDetaitobaibaoShouldBeFound("id.equals=" + id);
        defaultDetaitobaibaoShouldNotBeFound("id.notEquals=" + id);

        defaultDetaitobaibaoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDetaitobaibaoShouldNotBeFound("id.greaterThan=" + id);

        defaultDetaitobaibaoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDetaitobaibaoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung equals to DEFAULT_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the detaitobaibaoList where sudung equals to UPDATED_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung not equals to DEFAULT_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the detaitobaibaoList where sudung not equals to UPDATED_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the detaitobaibaoList where sudung equals to UPDATED_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung is not null
        defaultDetaitobaibaoShouldBeFound("sudung.specified=true");

        // Get all the detaitobaibaoList where sudung is null
        defaultDetaitobaibaoShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the detaitobaibaoList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the detaitobaibaoList where sudung is less than or equal to SMALLER_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung is less than DEFAULT_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the detaitobaibaoList where sudung is less than UPDATED_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDetaitobaibaosBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        // Get all the detaitobaibaoList where sudung is greater than DEFAULT_SUDUNG
        defaultDetaitobaibaoShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the detaitobaibaoList where sudung is greater than SMALLER_SUDUNG
        defaultDetaitobaibaoShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllDetaitobaibaosByDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);
        Detai detai = DetaiResourceIT.createEntity(em);
        em.persist(detai);
        em.flush();
        detaitobaibao.setDetai(detai);
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);
        Long detaiId = detai.getId();

        // Get all the detaitobaibaoList where detai equals to detaiId
        defaultDetaitobaibaoShouldBeFound("detaiId.equals=" + detaiId);

        // Get all the detaitobaibaoList where detai equals to detaiId + 1
        defaultDetaitobaibaoShouldNotBeFound("detaiId.equals=" + (detaiId + 1));
    }


    @Test
    @Transactional
    public void getAllDetaitobaibaosByDanhsachbaibaoIsEqualToSomething() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);
        Danhsachbaibao danhsachbaibao = DanhsachbaibaoResourceIT.createEntity(em);
        em.persist(danhsachbaibao);
        em.flush();
        detaitobaibao.setDanhsachbaibao(danhsachbaibao);
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);
        Long danhsachbaibaoId = danhsachbaibao.getId();

        // Get all the detaitobaibaoList where danhsachbaibao equals to danhsachbaibaoId
        defaultDetaitobaibaoShouldBeFound("danhsachbaibaoId.equals=" + danhsachbaibaoId);

        // Get all the detaitobaibaoList where danhsachbaibao equals to danhsachbaibaoId + 1
        defaultDetaitobaibaoShouldNotBeFound("danhsachbaibaoId.equals=" + (danhsachbaibaoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDetaitobaibaoShouldBeFound(String filter) throws Exception {
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detaitobaibao.getId().intValue())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDetaitobaibaoShouldNotBeFound(String filter) throws Exception {
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDetaitobaibao() throws Exception {
        // Get the detaitobaibao
        restDetaitobaibaoMockMvc.perform(get("/api/detaitobaibaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetaitobaibao() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        int databaseSizeBeforeUpdate = detaitobaibaoRepository.findAll().size();

        // Update the detaitobaibao
        Detaitobaibao updatedDetaitobaibao = detaitobaibaoRepository.findById(detaitobaibao.getId()).get();
        // Disconnect from session so that the updates on updatedDetaitobaibao are not directly saved in db
        em.detach(updatedDetaitobaibao);
        updatedDetaitobaibao
            .sudung(UPDATED_SUDUNG);
        DetaitobaibaoDTO detaitobaibaoDTO = detaitobaibaoMapper.toDto(updatedDetaitobaibao);

        restDetaitobaibaoMockMvc.perform(put("/api/detaitobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detaitobaibaoDTO)))
            .andExpect(status().isOk());

        // Validate the Detaitobaibao in the database
        List<Detaitobaibao> detaitobaibaoList = detaitobaibaoRepository.findAll();
        assertThat(detaitobaibaoList).hasSize(databaseSizeBeforeUpdate);
        Detaitobaibao testDetaitobaibao = detaitobaibaoList.get(detaitobaibaoList.size() - 1);
        assertThat(testDetaitobaibao.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingDetaitobaibao() throws Exception {
        int databaseSizeBeforeUpdate = detaitobaibaoRepository.findAll().size();

        // Create the Detaitobaibao
        DetaitobaibaoDTO detaitobaibaoDTO = detaitobaibaoMapper.toDto(detaitobaibao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetaitobaibaoMockMvc.perform(put("/api/detaitobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detaitobaibaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Detaitobaibao in the database
        List<Detaitobaibao> detaitobaibaoList = detaitobaibaoRepository.findAll();
        assertThat(detaitobaibaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetaitobaibao() throws Exception {
        // Initialize the database
        detaitobaibaoRepository.saveAndFlush(detaitobaibao);

        int databaseSizeBeforeDelete = detaitobaibaoRepository.findAll().size();

        // Delete the detaitobaibao
        restDetaitobaibaoMockMvc.perform(delete("/api/detaitobaibaos/{id}", detaitobaibao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Detaitobaibao> detaitobaibaoList = detaitobaibaoRepository.findAll();
        assertThat(detaitobaibaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
