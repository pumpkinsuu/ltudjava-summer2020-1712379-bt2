package pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 7:14 PM
 * @Description
 */
@Entity
public class Diem {
    private int maDiem;
    private Object diemGk;
    private Object diemCk;
    private Object diemKhac;
    private Object diemTong;
    private Object maLopHoc;
    private LopHoc lopHoc;

    @Id
    @Column(name = "maDiem")
    public int getMaDiem() {
        return maDiem;
    }

    public void setMaDiem(int maDiem) {
        this.maDiem = maDiem;
    }

    @Basic
    @Column(name = "diemGK")
    public Object getDiemGk() {
        return diemGk;
    }

    public void setDiemGk(Object diemGk) {
        this.diemGk = diemGk;
    }

    @Basic
    @Column(name = "diemCK")
    public Object getDiemCk() {
        return diemCk;
    }

    public void setDiemCk(Object diemCk) {
        this.diemCk = diemCk;
    }

    @Basic
    @Column(name = "diemKhac")
    public Object getDiemKhac() {
        return diemKhac;
    }

    public void setDiemKhac(Object diemKhac) {
        this.diemKhac = diemKhac;
    }

    @Basic
    @Column(name = "diemTong")
    public Object getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(Object diemTong) {
        this.diemTong = diemTong;
    }

    @Basic
    @Column(name = "maLopHoc")
    public Object getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(Object maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diem diem = (Diem) o;
        return maDiem == diem.maDiem &&
                Objects.equals(diemGk, diem.diemGk) &&
                Objects.equals(diemCk, diem.diemCk) &&
                Objects.equals(diemKhac, diem.diemKhac) &&
                Objects.equals(diemTong, diem.diemTong) &&
                Objects.equals(maLopHoc, diem.maLopHoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDiem, diemGk, diemCk, diemKhac, diemTong, maLopHoc);
    }

    @ManyToOne
    @JoinColumn(name = "maLopHoc", referencedColumnName = "maLopHoc", nullable = false)
    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }
}
