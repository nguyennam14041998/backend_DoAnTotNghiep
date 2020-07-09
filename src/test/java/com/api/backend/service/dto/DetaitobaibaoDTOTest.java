package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DetaitobaibaoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaitobaibaoDTO.class);
        DetaitobaibaoDTO detaitobaibaoDTO1 = new DetaitobaibaoDTO();
        detaitobaibaoDTO1.setId(1L);
        DetaitobaibaoDTO detaitobaibaoDTO2 = new DetaitobaibaoDTO();
        assertThat(detaitobaibaoDTO1).isNotEqualTo(detaitobaibaoDTO2);
        detaitobaibaoDTO2.setId(detaitobaibaoDTO1.getId());
        assertThat(detaitobaibaoDTO1).isEqualTo(detaitobaibaoDTO2);
        detaitobaibaoDTO2.setId(2L);
        assertThat(detaitobaibaoDTO1).isNotEqualTo(detaitobaibaoDTO2);
        detaitobaibaoDTO1.setId(null);
        assertThat(detaitobaibaoDTO1).isNotEqualTo(detaitobaibaoDTO2);
    }
}
