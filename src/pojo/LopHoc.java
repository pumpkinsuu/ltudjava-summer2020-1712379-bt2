package pojo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 7:14 PM
 * @Description
 */
@Entity
public class LopHoc {
    private int maLopHoc;
    private String maLop;
    private String maMon;
    private String MSSV;
    private Collection<Diem> diems;
    private Lop lop;
    private Mon mon;
    private SinhVien sinhvien;

    @Id
    @Column(name = "maLopHoc")
    public int getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(int maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    @Basic
    @Column(name = "maLop")
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Basic
    @Column(name = "maMon")
    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    @Basic
    @Column(name = "MSSV")
    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LopHoc lopHoc = (LopHoc) o;
        return maLopHoc == lopHoc.maLopHoc &&
                Objects.equals(maLop, lopHoc.maLop) &&
                Objects.equals(maMon, lopHoc.maMon) &&
                Objects.equals(MSSV, lopHoc.MSSV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLopHoc, maLop, maMon, MSSV);
    }

    @OneToMany(mappedBy = "lopHoc")
    public Collection<Diem> getDiems() {
        return diems;
    }

    public void setDiems(Collection<Diem> diems) {
        this.diems = diems;
    }

    @ManyToOne
    @JoinColumn(name = "maLop", referencedColumnName = "maLop", nullable = false)
    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    @ManyToOne
    @JoinColumn(name = "maMon", referencedColumnName = "maMon", nullable = false)
    public Mon getMon() {
        return mon;
    }

    public void setMon(Mon mon) {
        this.mon = mon;
    }

    @ManyToOne
    @JoinColumn(name = "MSSV", referencedColumnName = "MSSV", nullable = false)
    public SinhVien getSinhvien() {
        return sinhvien;
    }

    public void setSinhvien(SinhVien sinhvien) {
        this.sinhvien = sinhvien;
    }
}
