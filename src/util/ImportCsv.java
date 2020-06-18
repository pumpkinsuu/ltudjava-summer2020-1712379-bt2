package util;

import dbUtil.*;
import pojo.*;

import java.io.*;

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
                if (data.length < 5) break;

                sv.setMssv(data[1]);
                sv.setHoTen(data[2]);
                sv.setGioiTinh(data[3]);
                sv.setCmnd(data[4]);
                sv.setMaLop(lop.getMaLop());
                sv.setPassword(data[4]);

                if (!SinhVienDAO.add(sv)) return false;
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

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4) break;

                mon.setMaMon(data[1]);
                mon.setTenMon(data[2]);
                mon.setPhong(data[3]);

                tkb.setMaTkb(lop.getMaLop() + '-' + mon.getMaMon());
                tkb.setMaLop(lop.getMaLop());
                tkb.setMaMon(mon.getMaMon());

                if (!MonDAO.add(mon)) return false;
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
            LopHoc lopHoc = new LopHoc();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 2) break;

                lopHoc.setMssv(data[1]);
                lopHoc.setMaTkb(tkb.getMaTkb());
                lopHoc.setMaLopHoc(lopHoc.getMaTkb() + '-' + lopHoc.getMssv());

                if (!LopHocDAO.add(lopHoc)) return false;
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
                if (data.length < 7) break;

                String maLopHoc = tkb.getMaTkb() + '-' + data[1];

                diem.setMaDiem(maLopHoc);
                diem.setDiemGk(Double.parseDouble(data[3]));
                diem.setDiemCk(Double.parseDouble(data[4]));
                diem.setDiemKhac(Double.parseDouble(data[5]));
                diem.setDiemTong(Double.parseDouble(data[6]));
                diem.setMaLopHoc(maLopHoc);

                if (!DiemDAO.add(diem)) return false;
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
