package com.api.backend.service.mapper;

import com.api.backend.domain.Detai;
import com.api.backend.repository.CapdetaiRepository;
import com.api.backend.repository.DanhgiaRepository;
import com.api.backend.repository.HoidongdanhgiaRepository;
import com.api.backend.repository.LinhvucRepository;
import com.api.backend.service.dto.DetaiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDeTaiMapper {

    @Autowired private CapdetaiRepository capdetaiRepository;
    @Autowired private LinhvucRepository linhvucRepository;
    @Autowired private HoidongdanhgiaRepository hoidongdanhgiaRepository;

    public List<Detai> toEntity(List<DetaiDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Detai> list = new ArrayList<Detai>( dtoList.size() );
        for ( DetaiDTO detaiDTO : dtoList ) {
            list.add( toEntity( detaiDTO ) );
        }

        return list;
    }


    public List<DetaiDTO> toDto(List<Detai> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DetaiDTO> list = new ArrayList<DetaiDTO>( entityList.size() );
        for ( Detai detai : entityList ) {
            list.add( toDto( detai ) );
        }

        return list;
    }


    public DetaiDTO toDto(Detai detai) {
        if ( detai == null ) {
            return null;
        }

        DetaiDTO detaiDTO = new DetaiDTO();

        if(detai.getCapdetai()!= null) detaiDTO.setCapdetaiId( detai.getCapdetai().getId() );
        if(detai.getLinhvuc() != null) detaiDTO.setLinhvucId( detai.getLinhvuc().getId() );
        if(detai.getHoidongdanhgia()!= null) detaiDTO.setHoidongdanhgiaId( detai.getHoidongdanhgia().getId() );
        detaiDTO.setId( detai.getId() );
        detaiDTO.setMa( detai.getMa() );
        detaiDTO.setTen( detai.getTen() );
        detaiDTO.setThoigiantao( detai.getThoigiantao() );
        detaiDTO.setThoigianbatdau( detai.getThoigianbatdau() );
        detaiDTO.setThoigianketthuc( detai.getThoigianketthuc() );
        detaiDTO.setNam( detai.getNam() );
        detaiDTO.setMuctieu( detai.getMuctieu() );
        detaiDTO.setNoidung( detai.getNoidung() );
        detaiDTO.setTinhcapthiet( detai.getTinhcapthiet() );
        detaiDTO.setKetqua( detai.getKetqua() );
        detaiDTO.setXeploai( detai.getXeploai() );
        detaiDTO.setTrangthai( detai.getTrangthai() );
        detaiDTO.setTenchunhiem( detai.getTenchunhiem() );
        detaiDTO.setNhansu( detai.getNhansu() );
        detaiDTO.setSudung( detai.getSudung() );
        detaiDTO.setNguoiHuongDan(detai.getNguoiHuongDan());
        detaiDTO.setPhanLoai(detai.getPhanLoai());
        return detaiDTO;
    }


    public Detai toEntity(DetaiDTO detaiDTO) {
        if ( detaiDTO == null ) {
            return null;
        }

        Detai detai = new Detai();

        if(detaiDTO.getLinhvucId()!= null) detai.setLinhvuc( linhvucRepository.getOne(detaiDTO.getLinhvucId()) );
        if(detaiDTO.getCapdetaiId()!= null) detai.setCapdetai( capdetaiRepository.getOne(detaiDTO.getCapdetaiId()) );
        if(detaiDTO.getHoidongdanhgiaId()!= null) detai.setHoidongdanhgia( hoidongdanhgiaRepository.getOne(detaiDTO.getHoidongdanhgiaId()) );
        detai.setId( detaiDTO.getId() );
        detai.setMa( detaiDTO.getMa() );
        detai.setTen( detaiDTO.getTen() );
        detai.setThoigiantao( detaiDTO.getThoigiantao() );
        detai.setThoigianbatdau( detaiDTO.getThoigianbatdau() );
        detai.setThoigianketthuc( detaiDTO.getThoigianketthuc() );
        detai.setNam( detaiDTO.getNam() );
        detai.setMuctieu( detaiDTO.getMuctieu() );
        detai.setNoidung( detaiDTO.getNoidung() );
        detai.setTinhcapthiet( detaiDTO.getTinhcapthiet() );
        detai.setKetqua( detaiDTO.getKetqua() );
        detai.setXeploai( detaiDTO.getXeploai() );
        detai.setTrangthai( detaiDTO.getTrangthai() );
        detai.setTenchunhiem( detaiDTO.getTenchunhiem() );
        detai.setNhansu( detaiDTO.getNhansu() );
        detai.setSudung( detaiDTO.getSudung() );
        detai.setPhanLoai(detaiDTO.getPhanLoai());
        detai.setNguoiHuongDan(detaiDTO.getNguoiHuongDan());
        return detai;
    }
}
