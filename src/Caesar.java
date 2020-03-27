import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
enum choice
{
    ENCRYPT,DECRYPT;
}
public class Caesar {
    static char[] alphabet={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

   static ArrayList<Character> aL=new ArrayList<>();



    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
for(char l:alphabet){
    aL.add(l);
}






      try
      {
          String currentDirectory = System.getProperty("user.dir");
          File enFile = new File(currentDirectory+"\\src\\enFile.txt");
          if(enFile.createNewFile())
          {
              System.out.println("File is created");
          }
          else
          {
              System.out.println("File exists.");
          }

          System.out.println("Type encrypt or decrypt to encrypt or decrypt a message");
          String c =in.next();
          while (!c.toUpperCase().equals(choice.ENCRYPT.toString()) &&!c.toUpperCase().equals(choice.DECRYPT.toString()))
          {
              System.out.println("Not a valid input");
             c=in.next();
          }
          while (c.toUpperCase().equals(choice.ENCRYPT.toString()) || c.toUpperCase().equals(choice.DECRYPT.toString())||!c.equals("q"))
          {
              if(c.toUpperCase().equals(choice.ENCRYPT.toString()))
              {FileWriter writer = new FileWriter(enFile);
                  System.out.println("Enter sentence to be encrypted: ");String sen="";
                  while(sen.equals("")){
                  sen =in.nextLine();
                 }


                  System.out.println(sen);
                  System.out.println("Enter a number between 1-52");
                  int key=0;
                  try{
                   key=in.nextInt();}
                  catch (Exception e )
                  {
                      do {
                          key=in.nextInt();
                      }while (key>52);
                  }
                  String newSen="";
                  for(int x=0;x<sen.length();x++)
                  {

                      if(aL.contains(sen.charAt(x)))
                      {
                            int index = aL.indexOf(sen.charAt(x));
                            if(index+key<=51){
                             newSen=newSen+aL.get(index+key);}

                              else{
                                  index=index+key-52;
                                newSen=newSen+ aL.get(index);
                              }
                      }
                      else
                      {
                          newSen=newSen+sen.charAt(x);
                      }

                  }
                  System.out.println(newSen);
                  writer.write(newSen);
                  writer.close();


              }
              if(c.toUpperCase().equals(choice.DECRYPT.toString()))
              {
                  System.out.println("Enter your key you want to use to decrypt the message");
                  int key = in.nextInt();
                 String newSen="";
                  String dSen="";
                  try{
                      dSen=Files.readString(enFile.toPath());
                      System.out.println(dSen);}catch (Exception e){e.printStackTrace();}

                  for(int x=0;x<dSen.length();x++)
                  {

                      if(aL.contains(dSen.charAt(x)))
                      {
                          int index = aL.indexOf(dSen.charAt(x));
                          if(index-key>=0){
                              newSen=newSen+aL.get(index-key);
                              }

                          else{
                              index=index-key;
                              newSen=newSen+ aL.get(index+52);
                          }
                      }
                      else
                      {
                          newSen=newSen+dSen.charAt(x);
                      }

                  }

                  System.out.println(newSen);
              }

              System.out.println("Would you like to encrypt or decrypt or type q for quit");

              c =in.next();
          }
      }
      catch(Exception e)
      {e.printStackTrace();}
    }

}
