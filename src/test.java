import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class test {

    test() throws FileNotFoundException {
        System.out.println(" \033[(前缀),m(后缀),格式:\033[XX;XX;XXm");
        System.out.println("------ 字体颜色30~37 ------");
        System.out.println("\033[30m" + "就是酱紫的");
        System.out.println("\033[31m" + "就是酱紫的");
        System.out.println("\033[32m" + "就是酱紫的");
        System.out.println("\033[37m" + "就是酱紫的");
        System.out.println("------ 背景颜色40~47 -------");
        System.out.println("\033[43m" + "就是酱紫的");
        System.out.println("\033[44m" + "就是酱紫的"+"\033[m");
        System.out.println("\033[45m" + "就是酱紫的");
        System.out.println("\033[46m" + "就是酱紫的"+"\033[m");
        System.out.println("--- 1:加粗,;:隔开,90~97字体颜色变亮 ---");
        System.out.println("\033[1;90m" + "就是酱紫的");
        System.out.println("\033[1;94m" + "就是酱紫的");
        System.out.println("\033[1;95m" + "就是酱紫的");
        System.out.println("\033[1;97m" + "就是酱紫的");
        System.out.println("\033[1;93;45m" + "就是酱紫的"+"\033[m");

        System.out.format("\033[33m%-20s%-6d%-3d%-5d%%-5d\n", "name", 20, 3, 1000, 200);

        System.out.println(0%2);
    }

    public static void main(String [] args) throws FileNotFoundException {
        test t = new test();
    }

}
