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
 * Criteria class for the {@link com.api.backend.domain.Nhansutobaibao} entity. This class is used
 * in {@link com.api.backend.web.rest.NhansutobaibaoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nhansutobaibaos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NhansutobaibaoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter stt;

    private IntegerFilter sudung;

    private LongFilter danhsachbaibaoId;

    private LongFilter nhansuId;

    public NhansutobaibaoCriteria(){
    }

    public NhansutobaibaoCriteria(NhansutobaibaoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.stt = other.stt == null ? null : other.stt.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
        this.danhsachbaibaoId = other.danhsachbaibaoId == null ? null : other.danhsachbaibaoId.copy();
        this.nhansuId = other.nhansuId == null ? null : other.nhansuId.copy();
    }

    @Override
    public NhansutobaibaoCriteria copy() {
        return new NhansutobaibaoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getStt() {
        return stt;
    }

    public void setStt(IntegerFilter stt) {
        this.stt = stt;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }

    public LongFilter getDanhsachbaibaoId() {
        return danhsachbaibaoId;
    }

    public void setDanhsachbaibaoId(LongFilter danhsachbaibaoId) {
        this.danhsachbaibaoId = danhsachbaibaoId;
    }

    public LongFilter getNhansuId() {
        return nhansuId;
    }

    public void setNhansuId(LongFilter nhansuId) {
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
        final NhansutobaibaoCriteria that = (NhansutobaibaoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stt, that.stt) &&
            Objects.equals(sudung, that.sudung) &&
            Objects.equals(danhsachbaibaoId, that.danhsachbaibaoId) &&
            Objects.equals(nhansuId, that.nhansuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stt,
        sudung,
        danhsachbaibaoId,
        nhansuId
        );
    }

    @Override
    public String toString() {
        return "NhansutobaibaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stt != null ? "stt=" + stt + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
                (danhsachbaibaoId != null ? "danhsachbaibaoId=" + danhsachbaibaoId + ", " : "") +
                (nhansuId != null ? "nhansuId=" + nhansuId + ", " : "") +
            "}";
    }

}
