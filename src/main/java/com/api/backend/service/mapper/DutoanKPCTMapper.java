package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DutoanKPCTDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DutoanKPCT} and its DTO {@link DutoanKPCTDTO}.
 */
@Mapper(componentModel = "spring", uses = {DutoanKPMapper.class})
public interface DutoanKPCTMapper extends EntityMapper<DutoanKPCTDTO, DutoanKPCT> {

    @Mapping(source = "dutoanKP.id", target = "dutoanKPId")
    DutoanKPCTDTO toDto(DutoanKPCT dutoanKPCT);

    @Mapping(source = "dutoanKPId", target = "dutoanKP")
    DutoanKPCT toEntity(DutoanKPCTDTO dutoanKPCTDTO);

    default DutoanKPCT fromId(Long id) {
        if (id == null) {
            return null;
        }
        DutoanKPCT dutoanKPCT = new DutoanKPCT();
        dutoanKPCT.setId(id);
        return dutoanKPCT;
    }
}
