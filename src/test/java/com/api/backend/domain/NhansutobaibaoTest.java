package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class NhansutobaibaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nhansutobaibao.class);
        Nhansutobaibao nhansutobaibao1 = new Nhansutobaibao();
        nhansutobaibao1.setId(1L);
        Nhansutobaibao nhansutobaibao2 = new Nhansutobaibao();
        nhansutobaibao2.setId(nhansutobaibao1.getId());
        assertThat(nhansutobaibao1).isEqualTo(nhansutobaibao2);
        nhansutobaibao2.setId(2L);
        assertThat(nhansutobaibao1).isNotEqualTo(nhansutobaibao2);
        nhansutobaibao1.setId(null);
        assertThat(nhansutobaibao1).isNotEqualTo(nhansutobaibao2);
    }
}
