import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class Homework3 {
    public static void main(String[] args) {
        getWord();
        alwaysFile();
        readBook();
    }



    public static void getWord(){
        try(FileInputStream fis =new FileInputStream("hw3.txt")){
            int count;
            byte[] arr = new byte[50];
            while ((count = fis.read(arr))>0){
                for (int i = 0; i <count ; i++) {
                    System.out.print((char)arr[i]);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void alwaysFile(){
        File file = new File("FileHW");
        String[] str = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("h");
            }
        });

        try(FileOutputStream fis = new FileOutputStream("hwalways.txt")){
            ArrayList<InputStream> ai = new ArrayList<>();
            for (int i = 0; i<str.length; i++) {
                ai.add(new FileInputStream("FileHW/"+str[i]));
            }
            Enumeration<InputStream> e = Collections.enumeration(ai);

            SequenceInputStream se = new SequenceInputStream(e);

            int a;

            while(true){
                if((a=se.read())!=-1){
                    fis.write(a);
                }
                else{
                    break;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readBook(){
        int page = 1800;
        RandomAccessFile readBook = null;
        try {
            readBook = new RandomAccessFile("hwalways.txt", "r");
            Scanner enterPage = new Scanner(System.in);
            System.out.println("Введите страницу: ");
            int p = enterPage.nextInt() - 1;
            readBook.seek(p * page);
            byte[] barr = new byte[1800];
            readBook.read(barr);
            System.out.println(new String(barr));
            readBook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
