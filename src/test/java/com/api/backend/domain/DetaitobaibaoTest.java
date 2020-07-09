package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DetaitobaibaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Detaitobaibao.class);
        Detaitobaibao detaitobaibao1 = new Detaitobaibao();
        detaitobaibao1.setId(1L);
        Detaitobaibao detaitobaibao2 = new Detaitobaibao();
        detaitobaibao2.setId(detaitobaibao1.getId());
        assertThat(detaitobaibao1).isEqualTo(detaitobaibao2);
        detaitobaibao2.setId(2L);
        assertThat(detaitobaibao1).isNotEqualTo(detaitobaibao2);
        detaitobaibao1.setId(null);
        assertThat(detaitobaibao1).isNotEqualTo(detaitobaibao2);
    }
}
