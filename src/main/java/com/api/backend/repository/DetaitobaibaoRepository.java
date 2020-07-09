package com.api.backend.repository;
import com.api.backend.domain.Detaitobaibao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Detaitobaibao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetaitobaibaoRepository extends JpaRepository<Detaitobaibao, Long>, JpaSpecificationExecutor<Detaitobaibao> {

}
