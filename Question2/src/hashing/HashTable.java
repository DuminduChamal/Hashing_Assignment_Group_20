/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author 2017/is/056
 */
public class HashTable 
{
    private int tablesize=199;
    String[][] hashtable;

    public HashTable()
    {
        hashtable = new String[tablesize][3];
    }

    public void insert(String englishWord, String frenchWord, String spanishWord)
    {
        int index = (int)hash(englishWord);
        while(index<hashtable.length)
        {
            if(hashtable[index][0]!=null && hashtable[index][0].equals(englishWord))
            {
                System.out.println(englishWord+" is already exists at index "+index);
                return;
            }
            else if(hashtable[index][0]==null ||hashtable[index][0].isEmpty())
            {
                hashtable[index][0]=englishWord;
                hashtable[index][1]=frenchWord;
                hashtable[index][2]=spanishWord;
                System.out.println(englishWord+" inserted to dictionary at index "+index);
                return;
            }
            index++;
            index%=tablesize;
        }
        System.out.println("Hashtable Overloaded!!!");
    }

    public long hash(String englishWord)
    {
        long val = 0;
        long hashVal=0;
        String engLower=englishWord.toLowerCase();
        for (int i = 0; i < englishWord.length(); i++)
        {
            //System.out.println(val);
            val=val+(int)(engLower.charAt(i)*Math.pow(33, i));
        }
        hashVal=val%tablesize;
        //System.out.println(hashVal);
        return hashVal;
    }

    public void search(String englishPhase) throws FileNotFoundException, IOException
    {
        int flag;
        int ignoreWord;
        String sen="";
        String[] splited = englishPhase.split("\\s+");
        File myObj = new File("./ignore.txt");
        System.out.print("Translated in French :\t");
        for(int i=0; i<splited.length; i++)
        {
            flag=0;
            ignoreWord=0;
            String check =splited[i];
            String checkLower=check.toLowerCase();
            //System.out.println(splited[i]);
//            if(check.equals("is")||check.equals("are")||check.equals("did")||check.equals("does")||check.equals("do")||check.equals("it"))
//            {
//                //System.out.print(" ");
//                continue;
//            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
                String data = myReader.nextLine();
                String dataLower = data.toLowerCase();
                //System.out.println(data);
                if(checkLower.equals(dataLower))
                {
                    ignoreWord=1;
                    //System.out.println("TEST");
                    break;
                }
                
            }
            myReader.close();
            if(ignoreWord==1)
                continue;
            int hashIndex =(int)hash(check);
            sen=sen+check+" ";
            // System.out.println(hashIndex);
            while(hashIndex<hashtable.length) 
            {
            // System.out.println("Test");
                if(hashtable[hashIndex][0]!=null && hashtable[hashIndex][0].equals(checkLower)) 
                {
                    System.out.print(hashtable[hashIndex][1]+" ");
                    flag=1;
                    break;
                }        
                hashIndex++;
            }
            if(flag==0)
            {
                System.out.print("["+check+"] ");
            }  
        }
//        myReader.close();
        System.out.println();
        System.out.println("processed sentence : \t"+sen);
    }
    
    public void searchforSpanish(String englishPhase) throws FileNotFoundException, IOException
    {
        int flag;
        int ignoreWord;
        String sen="";
        String[] splited = englishPhase.split("\\s+");
        File myObj = new File("./ignore.txt");
        System.out.print("Translated in Spanish :\t");
        for(int i=0; i<splited.length; i++)
        {
            flag=0;
            ignoreWord=0;
            String check =splited[i];
            String checkLower=check.toLowerCase();
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
                String data = myReader.nextLine();
                String dataLower = data.toLowerCase();
                if(checkLower.equals(dataLower))
                {
                    ignoreWord=1;
                    break;
                }
                
            }
            myReader.close();
            if(ignoreWord==1)
                continue;
            int hashIndex =(int)hash(check);
            sen=sen+check+" ";
            while(hashIndex<hashtable.length) 
            {
                if(hashtable[hashIndex][0]!=null && hashtable[hashIndex][0].equals(checkLower)) 
                {
                    System.out.print(hashtable[hashIndex][2]+" ");
                    flag=1;
                    break;
                }        
                hashIndex++;
            }
            if(flag==0)
            {
                System.out.print("["+check+"] ");
            }  
        }
        System.out.println();
        System.out.println("processed sentence : \t"+sen);
    }
    
    
    public void printAll()
    {
        System.out.println("No\tEnglish\tFrench\tSpanish");
        for(int i=0; i<hashtable.length; i++) {
            if(hashtable[i][0] == null || hashtable[i][0].isEmpty()) {
                continue;
            }
            
            System.out.println((i) + "\t" + hashtable[i][0] + "\t" + hashtable[i][1]+ "\t" + hashtable[i][2]);
            
        }
    }
    
    public int countWords()
    {
        int count = 0;
        for(int i=0; i<hashtable.length; i++) {
            if(!(hashtable[i][0] == null || hashtable[i][0].isEmpty())) {
                count++;
            }
        }
        return count;
    }
}
