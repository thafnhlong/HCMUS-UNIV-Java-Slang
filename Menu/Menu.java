package Menu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import Slang.Manager;

public class Menu {
    public static final String DbFile = "origin.bin";
    public static final String DbWorkFile = "work.bin";
    public static final String DbRawFileDefault = "slang.txt";
    
    public Menu(){
        Manager.DbWorkFile = DbWorkFile;
        if(!File.isExist(DbFile)){
            showMenu(-1);
            String inp = readStringInput(new String[]{"c","k"});
            process(-1, inp);
        }
    }

    public void showMenu(int mid) {
        System.out.println("> ĐỒ ÁN #1 - SLANG WORD");
        System.out.println("> 20424051 - Nguyễn Thành Long");

        if (mid == -1) {
            System.out.println("> Khởi tạo");
            System.out.println("> Hệ thống lần đầu chạy, vui lòng chỉ ra đường dẫn file data");
            System.out.println("> File mặc định: " + DbRawFileDefault);
            System.out.print("> Bạn có muốn thay đổi không : Có (c) và Không (k): ");

        }
        else if (mid == 0) {
            System.out.println("> Danh sách chức năng");
            System.out.println("1.Tìm kiếm theo slang word");
            System.out.println("2.Tìm kiếm theo definition");
            System.out.println("3.Lịch sử tìm kiếm");
            System.out.println("4.Thêm slang word");
            System.out.println("5.Chỉnh sửa slang word");
            System.out.println("6.Xóa slang word");
            System.out.println("7.Khôi phục slang word");
            System.out.println("8.Tìm ngẫu nhiên 1 slang word");
            System.out.println("9.Đố vui");
            System.out.println("0.Thoát chương trình");
        }
        else if (mid == 1){
            System.out.println("> Tìm kiếm theo slang word");
            System.out.print("> Nhập slang word: ");
        }
        else if (mid == 2){
            System.out.println("> Tìm kiếm theo definition");
            System.out.print("> Nhập definition: ");
        }
        else if (mid==7) {
            System.out.println("> Khôi phục slang word");
            System.out.println("> Bạn có chắc khôi phục từ điển về ban đầu không?");
            System.out.println("1.Có");
            System.out.println("0.Quay lại");
        }
        else if (mid==9) {
            System.out.println("> Đố vui");
            System.out.println("1.Tìm kiếm theo slang word");
            System.out.println("2.Tìm kiếm theo definition");
            System.out.println("0.Quay lại");
        }
    }
    public int readNumberInput(int min,int max){
        while(true){
            System.out.print("Bạn chọn: ");
            try {
                int inp= Integer.parseInt(Screen.br.readLine());
                if(inp >= min && inp <= max)
                    return inp;
            } catch (NumberFormatException | IOException e) {
            }
            System.out.println("Số bạn nhập không hợp lệ");
        }
    }
    public String readStringInput(String[] vl){
        var valid = Arrays.asList(vl);
        while(true){
            try {
                String inp = Screen.br.readLine();
                if (valid.contains(inp))
                    return inp;
            } catch (IOException e) {
            }
            System.out.print("Không hợp lệ, vui lòng nhập lại: ");
        }
    }
    public String readStringInput(){
        try {
            return Screen.br.readLine();
        } catch (IOException e) {
        }
        return "";
    }
    public String readFileExist(){
        while(true){
            System.out.print("Vui lòng nhập đường dẫn file: ");
            try {
                String inp = Screen.br.readLine();
                if (File.isExist(inp))
                    return inp;
            } catch (IOException e) {
            }
            System.out.println("File không hợp lệ");
        }
    }

    public StringBuilder ListToString(List<String> inp){
        StringBuilder sb = new StringBuilder();
        for(String s : inp){
            sb.append("-").append(s).append("\n");
        }
        return sb;
    }

    public <T extends Comparable<T>> boolean process(int mid, T value){
        if(mid==-1){
            String inp = null;
            if (value.equals("k")){
                if(File.isExist(DbRawFileDefault)){
                    inp=DbRawFileDefault;
                } else {
                    System.out.println("File " + DbRawFileDefault + " không tồn tại");
                }
            }
            if (inp == null)
                inp = readFileExist();
            var mgi = Manager.getInstance();
            mgi.parseFromFile(inp);
            mgi.saveToOriginDB(DbFile);
        }
        else if(mid==0){
            if(value.equals(0))
                return false;
            else if (value.equals(1)){
                showMenu(1);
                String kw = readStringInput();
                var ret = Manager.getInstance().getDefinitions(kw);
                if(ret == null){
                    System.out.println("> Không tìm thấy Slang word này");
                }
                else{
                    System.out.println("> Ý nghĩa:");
                    System.out.println(ListToString(ret).toString());
                }
            }
            else if (value.equals(2)){
                showMenu(2);
                String defi = readStringInput();
                var ret = Manager.getInstance().getSlangWord(defi);
                if(ret.size()==0){
                    System.out.println("> Không tìm thấy definition này");
                }
                else{
                    System.out.println("> Slang word:");
                    System.out.println(ListToString(ret).toString());
                }
            }
            else if (value.equals(7)){
                showMenu(7);
                int inp = readNumberInput(0, 1);
                process(7, inp);
            }
            else if (value.equals(8)){
                var ret = Manager.getInstance().getRandomWord();
                System.out.println("> Từ ngẫu nhiên:" +ret.getKeyword());
                System.out.println("> Ý nghĩa:");
                System.out.println(ListToString(ret.getDefinitions()).toString());
            }
            System.out.print("Bấm enter để tiếp tục");
            try {
                Screen.br.readLine();
            } catch (IOException e) {
            }
        }
        else if(mid==7){
            if(value.equals(1)){
                File.copyFile(DbFile,DbWorkFile);
            }
        }
        return true;
    }
}
