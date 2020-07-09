package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DetaitobaibaoMapperTest {

    private DetaitobaibaoMapper detaitobaibaoMapper;

    @BeforeEach
    public void setUp() {
        detaitobaibaoMapper = new DetaitobaibaoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(detaitobaibaoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detaitobaibaoMapper.fromId(null)).isNull();
    }
}
