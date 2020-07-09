package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class NhansutobaibaoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhansutobaibaoDTO.class);
        NhansutobaibaoDTO nhansutobaibaoDTO1 = new NhansutobaibaoDTO();
        nhansutobaibaoDTO1.setId(1L);
        NhansutobaibaoDTO nhansutobaibaoDTO2 = new NhansutobaibaoDTO();
        assertThat(nhansutobaibaoDTO1).isNotEqualTo(nhansutobaibaoDTO2);
        nhansutobaibaoDTO2.setId(nhansutobaibaoDTO1.getId());
        assertThat(nhansutobaibaoDTO1).isEqualTo(nhansutobaibaoDTO2);
        nhansutobaibaoDTO2.setId(2L);
        assertThat(nhansutobaibaoDTO1).isNotEqualTo(nhansutobaibaoDTO2);
        nhansutobaibaoDTO1.setId(null);
        assertThat(nhansutobaibaoDTO1).isNotEqualTo(nhansutobaibaoDTO2);
    }
}
