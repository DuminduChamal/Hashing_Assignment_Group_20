/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 *
 * @author 2017/is/056
 */
public class Hashing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, BiffException
    {
        Scanner input = new Scanner(System.in);
//        System.out.print("Enter HashTable Size : ");
//        int tableSize = input.nextInt();

        HashTable hashtable = new HashTable();
        
        File file = new File("./Word_dictionary_For Q2.xls");
        Workbook wb = Workbook.getWorkbook(file);
        Sheet s = wb.getSheet(0);
        String englishWord,frenchWord,spanishWord;
        int row = s.getRows();
        int col = s.getColumns();
//        System.out.println(row);
//        System.out.println(col);
        for(int i=1; i<row; i++)
        {
            String english="";
            String french="";
            String spanish="";
//            for(int j=0; j<col; j++)
//            {
                Cell c = s.getCell(2, i);
                //System.out.print(c.getContents()+"\t\t");
                english = c.getContents();
                englishWord=english.toLowerCase();
                //System.out.println(english);
                Cell d = s.getCell(3, i);
                french = d.getContents();
                frenchWord=french.toLowerCase();
                Cell e = s.getCell(4, i);
                spanish = e.getContents();
                spanishWord=spanish.toLowerCase();
                //System.out.println(french);
                hashtable.insert(englishWord,frenchWord,spanishWord);
//                break;
//            }
            //System.out.println("");
        }
        
        System.out.println("Function No.\tFunction Name");
        System.out.println("1\tInsert a new word(English,French,Spanish)");
        System.out.println("2\tTranslate a phase in Englsih to French");
        System.out.println("3\tTranslate a phase in Englsih to Spanish");
        System.out.println("4\tWord Count of dictionary");
        System.out.println("5\tWords List");
        System.out.println("6\tTest this program with sentences in test.txt");
        
        while(true) 
        {
            System.out.print("Functio No. (1-6, else exit) : ");
            int val = input.nextInt();
            switch(val)
            {
                case 1:
                {
                    System.out.println("Enter the new English word :");
                    String engWord = input.next();
                    System.out.println("Enter the French word :");
                    String freWord = input.next();
                    System.out.println("Enter the Spanish word :");
                    String spaWord = input.next();
                    hashtable.insert(engWord,freWord,spaWord);
                    System.out.println();
                }
                break;
                case 2:
                {
                    System.out.print("Enter the phase in English :");
                    input.nextLine();
                    String engPhase = input.nextLine();
                    hashtable.search(engPhase);
                    System.out.println();
                }
                break;
                case 3:
                {
                    System.out.print("Enter the phase in English :");
                    input.nextLine();
                    String engPhase = input.nextLine();
                    hashtable.searchforSpanish(engPhase);
                    System.out.println();
                }
                break;
                case 4:
                {
                    System.out.println("Currently word count is "+hashtable.countWords());
                    System.out.println();
                }
                break;
                case 5:
                {
                    hashtable.printAll();
                    System.out.println();
                }
                break;
                case 6:
                {
                    File myObj = new File("./test.txt");
                    Scanner myReader = new Scanner(myObj);
                    int exampleNo=1;
                    while (myReader.hasNextLine()) 
                    {
                        System.out.println("Example No: "+exampleNo);
                        String data = myReader.nextLine();
                        System.out.println(data);
                        //System.out.println();
                        hashtable.search(data);
                        hashtable.searchforSpanish(data);
                        System.out.println();
                        exampleNo++;
                    }
                    myReader.close();
                }
                break;
                default:
                    System.exit(0);
            }
        }
    }
}
