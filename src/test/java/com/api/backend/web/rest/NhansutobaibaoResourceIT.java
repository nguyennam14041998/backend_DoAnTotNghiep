package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Nhansutobaibao;
import com.api.backend.domain.Danhsachbaibao;
import com.api.backend.domain.Nhansu;
import com.api.backend.repository.NhansutobaibaoRepository;
import com.api.backend.service.NhansutobaibaoService;
import com.api.backend.service.dto.NhansutobaibaoDTO;
import com.api.backend.service.mapper.NhansutobaibaoMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.NhansutobaibaoCriteria;
import com.api.backend.service.NhansutobaibaoQueryService;

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
 * Integration tests for the {@link NhansutobaibaoResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class NhansutobaibaoResourceIT {

    private static final Integer DEFAULT_STT = 1;
    private static final Integer UPDATED_STT = 2;
    private static final Integer SMALLER_STT = 1 - 1;

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private NhansutobaibaoRepository nhansutobaibaoRepository;

    @Autowired
    private NhansutobaibaoMapper nhansutobaibaoMapper;

    @Autowired
    private NhansutobaibaoService nhansutobaibaoService;

    @Autowired
    private NhansutobaibaoQueryService nhansutobaibaoQueryService;

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

    private MockMvc restNhansutobaibaoMockMvc;

    private Nhansutobaibao nhansutobaibao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhansutobaibaoResource nhansutobaibaoResource = new NhansutobaibaoResource(nhansutobaibaoService, nhansutobaibaoQueryService);
        this.restNhansutobaibaoMockMvc = MockMvcBuilders.standaloneSetup(nhansutobaibaoResource)
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
    public static Nhansutobaibao createEntity(EntityManager em) {
        Nhansutobaibao nhansutobaibao = new Nhansutobaibao()
            .stt(DEFAULT_STT)
            .sudung(DEFAULT_SUDUNG);
        return nhansutobaibao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nhansutobaibao createUpdatedEntity(EntityManager em) {
        Nhansutobaibao nhansutobaibao = new Nhansutobaibao()
            .stt(UPDATED_STT)
            .sudung(UPDATED_SUDUNG);
        return nhansutobaibao;
    }

    @BeforeEach
    public void initTest() {
        nhansutobaibao = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhansutobaibao() throws Exception {
        int databaseSizeBeforeCreate = nhansutobaibaoRepository.findAll().size();

        // Create the Nhansutobaibao
        NhansutobaibaoDTO nhansutobaibaoDTO = nhansutobaibaoMapper.toDto(nhansutobaibao);
        restNhansutobaibaoMockMvc.perform(post("/api/nhansutobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhansutobaibaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Nhansutobaibao in the database
        List<Nhansutobaibao> nhansutobaibaoList = nhansutobaibaoRepository.findAll();
        assertThat(nhansutobaibaoList).hasSize(databaseSizeBeforeCreate + 1);
        Nhansutobaibao testNhansutobaibao = nhansutobaibaoList.get(nhansutobaibaoList.size() - 1);
        assertThat(testNhansutobaibao.getStt()).isEqualTo(DEFAULT_STT);
        assertThat(testNhansutobaibao.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createNhansutobaibaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhansutobaibaoRepository.findAll().size();

        // Create the Nhansutobaibao with an existing ID
        nhansutobaibao.setId(1L);
        NhansutobaibaoDTO nhansutobaibaoDTO = nhansutobaibaoMapper.toDto(nhansutobaibao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhansutobaibaoMockMvc.perform(post("/api/nhansutobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhansutobaibaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nhansutobaibao in the database
        List<Nhansutobaibao> nhansutobaibaoList = nhansutobaibaoRepository.findAll();
        assertThat(nhansutobaibaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNhansutobaibaos() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhansutobaibao.getId().intValue())))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getNhansutobaibao() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get the nhansutobaibao
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos/{id}", nhansutobaibao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhansutobaibao.getId().intValue()))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getNhansutobaibaosByIdFiltering() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        Long id = nhansutobaibao.getId();

        defaultNhansutobaibaoShouldBeFound("id.equals=" + id);
        defaultNhansutobaibaoShouldNotBeFound("id.notEquals=" + id);

        defaultNhansutobaibaoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNhansutobaibaoShouldNotBeFound("id.greaterThan=" + id);

        defaultNhansutobaibaoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNhansutobaibaoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt equals to DEFAULT_STT
        defaultNhansutobaibaoShouldBeFound("stt.equals=" + DEFAULT_STT);

        // Get all the nhansutobaibaoList where stt equals to UPDATED_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.equals=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt not equals to DEFAULT_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.notEquals=" + DEFAULT_STT);

        // Get all the nhansutobaibaoList where stt not equals to UPDATED_STT
        defaultNhansutobaibaoShouldBeFound("stt.notEquals=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsInShouldWork() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt in DEFAULT_STT or UPDATED_STT
        defaultNhansutobaibaoShouldBeFound("stt.in=" + DEFAULT_STT + "," + UPDATED_STT);

        // Get all the nhansutobaibaoList where stt equals to UPDATED_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.in=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt is not null
        defaultNhansutobaibaoShouldBeFound("stt.specified=true");

        // Get all the nhansutobaibaoList where stt is null
        defaultNhansutobaibaoShouldNotBeFound("stt.specified=false");
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt is greater than or equal to DEFAULT_STT
        defaultNhansutobaibaoShouldBeFound("stt.greaterThanOrEqual=" + DEFAULT_STT);

        // Get all the nhansutobaibaoList where stt is greater than or equal to UPDATED_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.greaterThanOrEqual=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt is less than or equal to DEFAULT_STT
        defaultNhansutobaibaoShouldBeFound("stt.lessThanOrEqual=" + DEFAULT_STT);

        // Get all the nhansutobaibaoList where stt is less than or equal to SMALLER_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.lessThanOrEqual=" + SMALLER_STT);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsLessThanSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt is less than DEFAULT_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.lessThan=" + DEFAULT_STT);

        // Get all the nhansutobaibaoList where stt is less than UPDATED_STT
        defaultNhansutobaibaoShouldBeFound("stt.lessThan=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySttIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where stt is greater than DEFAULT_STT
        defaultNhansutobaibaoShouldNotBeFound("stt.greaterThan=" + DEFAULT_STT);

        // Get all the nhansutobaibaoList where stt is greater than SMALLER_STT
        defaultNhansutobaibaoShouldBeFound("stt.greaterThan=" + SMALLER_STT);
    }


    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung equals to DEFAULT_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the nhansutobaibaoList where sudung equals to UPDATED_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung not equals to DEFAULT_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the nhansutobaibaoList where sudung not equals to UPDATED_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the nhansutobaibaoList where sudung equals to UPDATED_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung is not null
        defaultNhansutobaibaoShouldBeFound("sudung.specified=true");

        // Get all the nhansutobaibaoList where sudung is null
        defaultNhansutobaibaoShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the nhansutobaibaoList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the nhansutobaibaoList where sudung is less than or equal to SMALLER_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung is less than DEFAULT_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the nhansutobaibaoList where sudung is less than UPDATED_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNhansutobaibaosBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        // Get all the nhansutobaibaoList where sudung is greater than DEFAULT_SUDUNG
        defaultNhansutobaibaoShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the nhansutobaibaoList where sudung is greater than SMALLER_SUDUNG
        defaultNhansutobaibaoShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllNhansutobaibaosByDanhsachbaibaoIsEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);
        Danhsachbaibao danhsachbaibao = DanhsachbaibaoResourceIT.createEntity(em);
        em.persist(danhsachbaibao);
        em.flush();
        nhansutobaibao.setDanhsachbaibao(danhsachbaibao);
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);
        Long danhsachbaibaoId = danhsachbaibao.getId();

        // Get all the nhansutobaibaoList where danhsachbaibao equals to danhsachbaibaoId
        defaultNhansutobaibaoShouldBeFound("danhsachbaibaoId.equals=" + danhsachbaibaoId);

        // Get all the nhansutobaibaoList where danhsachbaibao equals to danhsachbaibaoId + 1
        defaultNhansutobaibaoShouldNotBeFound("danhsachbaibaoId.equals=" + (danhsachbaibaoId + 1));
    }


    @Test
    @Transactional
    public void getAllNhansutobaibaosByNhansuIsEqualToSomething() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);
        Nhansu nhansu = NhansuResourceIT.createEntity(em);
        em.persist(nhansu);
        em.flush();
        nhansutobaibao.setNhansu(nhansu);
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);
        Long nhansuId = nhansu.getId();

        // Get all the nhansutobaibaoList where nhansu equals to nhansuId
        defaultNhansutobaibaoShouldBeFound("nhansuId.equals=" + nhansuId);

        // Get all the nhansutobaibaoList where nhansu equals to nhansuId + 1
        defaultNhansutobaibaoShouldNotBeFound("nhansuId.equals=" + (nhansuId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNhansutobaibaoShouldBeFound(String filter) throws Exception {
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhansutobaibao.getId().intValue())))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNhansutobaibaoShouldNotBeFound(String filter) throws Exception {
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNhansutobaibao() throws Exception {
        // Get the nhansutobaibao
        restNhansutobaibaoMockMvc.perform(get("/api/nhansutobaibaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhansutobaibao() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        int databaseSizeBeforeUpdate = nhansutobaibaoRepository.findAll().size();

        // Update the nhansutobaibao
        Nhansutobaibao updatedNhansutobaibao = nhansutobaibaoRepository.findById(nhansutobaibao.getId()).get();
        // Disconnect from session so that the updates on updatedNhansutobaibao are not directly saved in db
        em.detach(updatedNhansutobaibao);
        updatedNhansutobaibao
            .stt(UPDATED_STT)
            .sudung(UPDATED_SUDUNG);
        NhansutobaibaoDTO nhansutobaibaoDTO = nhansutobaibaoMapper.toDto(updatedNhansutobaibao);

        restNhansutobaibaoMockMvc.perform(put("/api/nhansutobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhansutobaibaoDTO)))
            .andExpect(status().isOk());

        // Validate the Nhansutobaibao in the database
        List<Nhansutobaibao> nhansutobaibaoList = nhansutobaibaoRepository.findAll();
        assertThat(nhansutobaibaoList).hasSize(databaseSizeBeforeUpdate);
        Nhansutobaibao testNhansutobaibao = nhansutobaibaoList.get(nhansutobaibaoList.size() - 1);
        assertThat(testNhansutobaibao.getStt()).isEqualTo(UPDATED_STT);
        assertThat(testNhansutobaibao.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingNhansutobaibao() throws Exception {
        int databaseSizeBeforeUpdate = nhansutobaibaoRepository.findAll().size();

        // Create the Nhansutobaibao
        NhansutobaibaoDTO nhansutobaibaoDTO = nhansutobaibaoMapper.toDto(nhansutobaibao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhansutobaibaoMockMvc.perform(put("/api/nhansutobaibaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhansutobaibaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nhansutobaibao in the database
        List<Nhansutobaibao> nhansutobaibaoList = nhansutobaibaoRepository.findAll();
        assertThat(nhansutobaibaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNhansutobaibao() throws Exception {
        // Initialize the database
        nhansutobaibaoRepository.saveAndFlush(nhansutobaibao);

        int databaseSizeBeforeDelete = nhansutobaibaoRepository.findAll().size();

        // Delete the nhansutobaibao
        restNhansutobaibaoMockMvc.perform(delete("/api/nhansutobaibaos/{id}", nhansutobaibao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nhansutobaibao> nhansutobaibaoList = nhansutobaibaoRepository.findAll();
        assertThat(nhansutobaibaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
