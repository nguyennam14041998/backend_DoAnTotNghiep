package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Detaitobaibao.
 */
@Entity
@Table(name = "detaitobaibao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Detaitobaibao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sudung")
    private Integer sudung;

    @ManyToOne
    @JsonIgnoreProperties("detaitobaibaos")
    private Detai detai;

    @ManyToOne
    @JsonIgnoreProperties("detaitobaibaos")
    private Danhsachbaibao danhsachbaibao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSudung() {
        return sudung;
    }

    public Detaitobaibao sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Detai getDetai() {
        return detai;
    }

    public Detaitobaibao detai(Detai detai) {
        this.detai = detai;
        return this;
    }

    public void setDetai(Detai detai) {
        this.detai = detai;
    }

    public Danhsachbaibao getDanhsachbaibao() {
        return danhsachbaibao;
    }

    public Detaitobaibao danhsachbaibao(Danhsachbaibao danhsachbaibao) {
        this.danhsachbaibao = danhsachbaibao;
        return this;
    }

    public void setDanhsachbaibao(Danhsachbaibao danhsachbaibao) {
        this.danhsachbaibao = danhsachbaibao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Detaitobaibao)) {
            return false;
        }
        return id != null && id.equals(((Detaitobaibao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Detaitobaibao{" +
            "id=" + getId() +
            ", sudung=" + getSudung() +
            "}";
    }
}
