package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DanhsachbaibaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Danhsachbaibao} and its DTO {@link DanhsachbaibaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhsachbaibaoMapper extends EntityMapper<DanhsachbaibaoDTO, Danhsachbaibao> {


    @Mapping(target = "nhansutobaibaos", ignore = true)
    @Mapping(target = "removeNhansutobaibao", ignore = true)
    @Mapping(target = "detaitobaibaos", ignore = true)
    @Mapping(target = "removeDetaitobaibao", ignore = true)
    Danhsachbaibao toEntity(DanhsachbaibaoDTO danhsachbaibaoDTO);

    default Danhsachbaibao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Danhsachbaibao danhsachbaibao = new Danhsachbaibao();
        danhsachbaibao.setId(id);
        return danhsachbaibao;
    }
}
