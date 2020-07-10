package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Nhansutobaibao} entity.
 */
public class NhansutobaibaoDTO implements Serializable {

    private Long id;

    private Integer stt;

    private Integer nam;

    private Integer sudung;


    private Long danhsachbaibaoId;

    private Long nhansuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getDanhsachbaibaoId() {
        return danhsachbaibaoId;
    }

    public void setDanhsachbaibaoId(Long danhsachbaibaoId) {
        this.danhsachbaibaoId = danhsachbaibaoId;
    }

    public Long getNhansuId() {
        return nhansuId;
    }

    public void setNhansuId(Long nhansuId) {
        this.nhansuId = nhansuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NhansutobaibaoDTO nhansutobaibaoDTO = (NhansutobaibaoDTO) o;
        if (nhansutobaibaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhansutobaibaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhansutobaibaoDTO{" +
            "id=" + getId() +
            ", stt=" + getStt() +
            ", nam=" + getNam() +
            ", sudung=" + getSudung() +
            ", danhsachbaibao=" + getDanhsachbaibaoId() +
            ", nhansu=" + getNhansuId() +
            "}";
    }
}
