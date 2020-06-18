package util;

import dbUtil.LopDAO;
import dbUtil.MonDAO;
import dbUtil.SinhVienDAO;
import pojo.Lop;
import pojo.Mon;
import pojo.SinhVien;

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
        if (lop == null)
            return false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file.getCanonicalFile()));

            br.readLine();
            String line;
            SinhVien sv = new SinhVien();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5)
                    break;

                sv.setMssv(data[1]);
                sv.setHoTen(data[2]);
                sv.setGioiTinh(data[3]);
                sv.setCmnd(data[4]);
                sv.setMaLop(lop.getMaLop());
                sv.setPassword(data[4]);
                if (!SinhVienDAO.add(sv)) {
                    LopDAO.remove(lop.getMaLop());

                    return false;
                }
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
        if (lop == null)
            return false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file.getCanonicalFile()));

            br.readLine();
            String line;
            Mon mon = new Mon();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4)
                    break;

                mon.setMaMon(data[1]);
                mon.setTenMon(data[2]);
                mon.setPhong(data[3]);
                if (!MonDAO.add(mon)) {
                    LopDAO.remove(lop.getMaLop());

                    return false;
                }
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
