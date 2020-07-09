package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DetaitobaibaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Detaitobaibao} and its DTO {@link DetaitobaibaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {DetaiMapper.class, DanhsachbaibaoMapper.class})
public interface DetaitobaibaoMapper extends EntityMapper<DetaitobaibaoDTO, Detaitobaibao> {

    @Mapping(source = "detai.id", target = "detaiId")
    @Mapping(source = "danhsachbaibao.id", target = "danhsachbaibaoId")
    DetaitobaibaoDTO toDto(Detaitobaibao detaitobaibao);

    @Mapping(source = "detaiId", target = "detai")
    @Mapping(source = "danhsachbaibaoId", target = "danhsachbaibao")
    Detaitobaibao toEntity(DetaitobaibaoDTO detaitobaibaoDTO);

    default Detaitobaibao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Detaitobaibao detaitobaibao = new Detaitobaibao();
        detaitobaibao.setId(id);
        return detaitobaibao;
    }
}
