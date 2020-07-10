package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Nhansutobaibao.
 */
@Entity
@Table(name = "nhansutobaibao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Nhansutobaibao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stt")
    private Integer stt;

    @Column(name = "nam")
    private Integer nam;

    @Column(name = "sudung")
    private Integer sudung;

    @ManyToOne
    @JsonIgnoreProperties("nhansutobaibaos")
    private Danhsachbaibao danhsachbaibao;

    @ManyToOne
    @JsonIgnoreProperties(value={"nhansutobaibaos","donvi","chucdanh","hocham"})
    private Nhansu nhansu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStt() {
        return stt;
    }

    public Nhansutobaibao stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getNam() {
        return nam;
    }

    public Nhansutobaibao nam(Integer nam) {
        this.nam = nam;
        return this;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getSudung() {
        return sudung;
    }

    public Nhansutobaibao sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Danhsachbaibao getDanhsachbaibao() {
        return danhsachbaibao;
    }

    public Nhansutobaibao danhsachbaibao(Danhsachbaibao danhsachbaibao) {
        this.danhsachbaibao = danhsachbaibao;
        return this;
    }

    public void setDanhsachbaibao(Danhsachbaibao danhsachbaibao) {
        this.danhsachbaibao = danhsachbaibao;
    }

    public Nhansu getNhansu() {
        return nhansu;
    }

    public Nhansutobaibao nhansu(Nhansu nhansu) {
        this.nhansu = nhansu;
        return this;
    }

    public void setNhansu(Nhansu nhansu) {
        this.nhansu = nhansu;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nhansutobaibao)) {
            return false;
        }
        return id != null && id.equals(((Nhansutobaibao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Nhansutobaibao{" +
            "id=" + getId() +
            ", stt=" + getStt() +
            ", nam=" + getNam() +
            ", sudung=" + getSudung() +
            "}";
    }
}
