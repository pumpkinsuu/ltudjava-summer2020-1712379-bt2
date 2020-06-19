package util;

import dbUtil.*;
import pojo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 4:34 PM
 * @Description
 */
public class ImportCsv {
    public static boolean importSv(File file)  {
        Lop lop = ImportCsv.getLop(file);
        if (lop == null) return false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file.getCanonicalFile()));

            br.readLine();
            String line;
            SinhVien sv = new SinhVien();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue;

                sv.setMssv(data[1]);
                sv.setHoTen(data[2]);
                sv.setGioiTinh(data[3]);
                sv.setCmnd(data[4]);
                sv.setMaLop(lop.getMaLop());
                sv.setPassword(data[4]);

                SinhVienDAO.add(sv);
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean importMon(File file) {
        Lop lop = ImportCsv.getLop(file);
        if (lop == null) return false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file.getCanonicalFile()));

            String line;
            Mon mon = new Mon();
            Tkb tkb = new Tkb();
            LopHoc lopHoc = new LopHoc();
            List<SinhVien> sinhVienList = SinhVienDAO.getList();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4) continue;

                mon.setMaMon(data[1]);
                mon.setTenMon(data[2]);
                mon.setPhong(data[3]);

                tkb.setMaTkb(lop.getMaLop() + '-' + data[1]);
                tkb.setMaLop(lop.getMaLop());
                tkb.setMaMon(data[1]);

                MonDAO.add(mon);

                if (!TkbDAO.add(tkb))
                    continue;

                for (SinhVien sv : sinhVienList) {
                    if (sv.getMaLop().equals(lop.getMaLop())) {
                        lopHoc.setMaLopHoc(tkb.getMaTkb() + '-' + sv.getMssv());
                        lopHoc.setMaTkb(tkb.getMaTkb());
                        lopHoc.setMssv(sv.getMssv());

                        LopHocDAO.add(lopHoc);
                    }
                }
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean importLopHoc(File file) {
        String fileName = getBaseName(file.getName());
        Tkb tkb = TkbDAO.get(fileName);
        if (tkb == null) return false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file.getCanonicalFile()));

            String line;
            String maTkb = tkb.getMaTkb();
            LopHoc lopHoc = new LopHoc();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 2) continue;

                lopHoc.setMaLopHoc(maTkb + '-' + data[1]);
                lopHoc.setMaTkb(maTkb);
                lopHoc.setMssv(data[1]);

                LopHocDAO.add(lopHoc);
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean importDiem(File file) {
        String fileName = getBaseName(file.getName());
        Tkb tkb = TkbDAO.get(fileName);
        if (tkb == null) return false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file.getCanonicalFile()));

            String line;
            Diem diem = new Diem();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) continue;

                String maLopHoc = tkb.getMaTkb() + '-' + data[1];

                diem.setMaDiem(maLopHoc);
                diem.setDiemGk(Double.parseDouble(data[3]));
                diem.setDiemCk(Double.parseDouble(data[4]));
                diem.setDiemKhac(Double.parseDouble(data[5]));
                diem.setDiemTong(Double.parseDouble(data[6]));
                diem.setMaLopHoc(maLopHoc);

                DiemDAO.add(diem);
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static String getBaseName(String fileName) {
        if (fileName != null) {
            int index = fileName.lastIndexOf('.');
            if (index != -1)
                return fileName.substring(0, index);
        }
        return fileName;
    }

    public static Lop getLop(File file) {
        String fileName = getBaseName(file.getName());
        Lop lop = new Lop();

        lop.setMaLop(fileName);
        LopDAO.add(lop);

        return LopDAO.get(fileName);
    }
}
