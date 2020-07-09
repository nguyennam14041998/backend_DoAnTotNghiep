package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.NhansutobaibaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nhansutobaibao} and its DTO {@link NhansutobaibaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {DanhsachbaibaoMapper.class, NhansuMapper.class})
public interface NhansutobaibaoMapper extends EntityMapper<NhansutobaibaoDTO, Nhansutobaibao> {

    @Mapping(source = "danhsachbaibao.id", target = "danhsachbaibaoId")
    @Mapping(source = "nhansu.id", target = "nhansuId")
    NhansutobaibaoDTO toDto(Nhansutobaibao nhansutobaibao);

    @Mapping(source = "danhsachbaibaoId", target = "danhsachbaibao")
    @Mapping(source = "nhansuId", target = "nhansu")
    Nhansutobaibao toEntity(NhansutobaibaoDTO nhansutobaibaoDTO);

    default Nhansutobaibao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nhansutobaibao nhansutobaibao = new Nhansutobaibao();
        nhansutobaibao.setId(id);
        return nhansutobaibao;
    }
}
