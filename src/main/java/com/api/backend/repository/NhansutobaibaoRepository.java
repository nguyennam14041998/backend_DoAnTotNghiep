package com.api.backend.repository;
import com.api.backend.domain.Nhansutobaibao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Nhansutobaibao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhansutobaibaoRepository extends JpaRepository<Nhansutobaibao, Long>, JpaSpecificationExecutor<Nhansutobaibao> {

}
