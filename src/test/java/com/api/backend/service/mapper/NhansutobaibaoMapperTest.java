package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NhansutobaibaoMapperTest {

    private NhansutobaibaoMapper nhansutobaibaoMapper;

    @BeforeEach
    public void setUp() {
        nhansutobaibaoMapper = new NhansutobaibaoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nhansutobaibaoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nhansutobaibaoMapper.fromId(null)).isNull();
    }
}
