package pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 7:31 PM
 * @Description
 */
@Entity
public class Diem {
    private String maDiem;
    private double diemGk;
    private double diemCk;
    private double diemKhac;
    private double diemTong;
    private String maLopHoc;
    private LopHoc lopHoc;

    @Id
    @Column(name = "maDiem")
    public String getMaDiem() {
        return maDiem;
    }

    public void setMaDiem(String maDiem) {
        this.maDiem = maDiem;
    }

    @Basic
    @Column(name = "diemGK")
    public double getDiemGk() {
        return diemGk;
    }

    public void setDiemGk(double diemGk) {
        this.diemGk = diemGk;
    }

    @Basic
    @Column(name = "diemCK")
    public double getDiemCk() {
        return diemCk;
    }

    public void setDiemCk(double diemCk) {
        this.diemCk = diemCk;
    }

    @Basic
    @Column(name = "diemKhac")
    public double getDiemKhac() {
        return diemKhac;
    }

    public void setDiemKhac(double diemKhac) {
        this.diemKhac = diemKhac;
    }

    @Basic
    @Column(name = "diemTong")
    public double getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(double diemTong) {
        this.diemTong = diemTong;
    }

    @Basic
    @Column(name = "maLopHoc")
    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diem diem = (Diem) o;
        return maDiem == diem.maDiem &&
                Double.compare(diem.diemGk, diemGk) == 0 &&
                Double.compare(diem.diemCk, diemCk) == 0 &&
                Double.compare(diem.diemKhac, diemKhac) == 0 &&
                Double.compare(diem.diemTong, diemTong) == 0 &&
                Objects.equals(maLopHoc, diem.maLopHoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDiem, diemGk, diemCk, diemKhac, diemTong, maLopHoc);
    }

    @ManyToOne
    @JoinColumn(name = "maLopHoc", referencedColumnName = "maLopHoc", nullable = false, insertable = false, updatable = false)
    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }
}
