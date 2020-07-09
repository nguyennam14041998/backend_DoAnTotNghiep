package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Detaitobaibao} entity.
 */
public class DetaitobaibaoDTO implements Serializable {

    private Long id;

    private Integer sudung;


    private Long detaiId;

    private Long danhsachbaibaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getDetaiId() {
        return detaiId;
    }

    public void setDetaiId(Long detaiId) {
        this.detaiId = detaiId;
    }

    public Long getDanhsachbaibaoId() {
        return danhsachbaibaoId;
    }

    public void setDanhsachbaibaoId(Long danhsachbaibaoId) {
        this.danhsachbaibaoId = danhsachbaibaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DetaitobaibaoDTO detaitobaibaoDTO = (DetaitobaibaoDTO) o;
        if (detaitobaibaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detaitobaibaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DetaitobaibaoDTO{" +
            "id=" + getId() +
            ", sudung=" + getSudung() +
            ", detai=" + getDetaiId() +
            ", danhsachbaibao=" + getDanhsachbaibaoId() +
            "}";
    }
}
