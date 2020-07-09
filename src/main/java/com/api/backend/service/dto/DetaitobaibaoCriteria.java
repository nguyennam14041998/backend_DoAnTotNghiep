package com.api.backend.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.api.backend.domain.Detaitobaibao} entity. This class is used
 * in {@link com.api.backend.web.rest.DetaitobaibaoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /detaitobaibaos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DetaitobaibaoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sudung;

    private LongFilter detaiId;

    private LongFilter danhsachbaibaoId;

    public DetaitobaibaoCriteria(){
    }

    public DetaitobaibaoCriteria(DetaitobaibaoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.detaiId = other.detaiId == null ? null : other.detaiId.copy();
        this.danhsachbaibaoId = other.danhsachbaibaoId == null ? null : other.danhsachbaibaoId.copy();
    }

    @Override
    public DetaitobaibaoCriteria copy() {
        return new DetaitobaibaoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDetaiId() {
        return detaiId;
    }

    public void setDetaiId(LongFilter detaiId) {
        this.detaiId = detaiId;
    }

    public LongFilter getDanhsachbaibaoId() {
        return danhsachbaibaoId;
    }

    public void setDanhsachbaibaoId(LongFilter danhsachbaibaoId) {
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
        final DetaitobaibaoCriteria that = (DetaitobaibaoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(detaiId, that.detaiId) &&
            Objects.equals(danhsachbaibaoId, that.danhsachbaibaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sudung,
        detaiId,
        danhsachbaibaoId
        );
    }

    @Override
    public String toString() {
        return "DetaitobaibaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (detaiId != null ? "detaiId=" + detaiId + ", " : "") +
                (danhsachbaibaoId != null ? "danhsachbaibaoId=" + danhsachbaibaoId + ", " : "") +
            "}";
    }

}
