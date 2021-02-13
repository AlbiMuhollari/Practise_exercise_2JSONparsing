package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws JSONException {



        String fileName = "src\\com\\company\\music_csv.txt";
        Scanner inFile = null;

        //String[][] values = new String[5][4];
        //ArrayList<ArrayList<String[][]>> matrix = new ArrayList<>(); //know the meaning of this one, this one has 4 dimensions, 2 are arraylist and 2 are arrays right?
        ArrayList<String[]> matrixArraylist = new ArrayList<>();

        //alternatively
        //ArrayList<ArrayList<String>> matrixArraylist = new ArrayList<>();



        try {
            inFile = new Scanner(new File(fileName));
            while (inFile.hasNextLine()) {
                String str = inFile.nextLine();

                matrixArraylist.add(str.split(","));

                //alternatively  //matrixArraylist.add(new ArrayList<>(Arrays.asList(str.split(","))));
                //System.out.print("\n str: " + str);

            }
        }
        catch(FileNotFoundException ex)
            {
                System.out.print("Error opening file: " + fileName);

            }



        //just for printing purposes
        for(int i=0;i<matrixArraylist.size();i++)
        {
            System.out.print("\nObject [" + i + "]: ");

            for(int j=0;j<matrixArraylist.get(i).length;j++)

            {
                System.out.print(matrixArraylist.get(i)[j]); //rows are arraylist and columns are strings, carefullll
                System.out.print(" ");
            }
        }



        JSONArray myJson = new JSONArray();

        for(int i=0;i<matrixArraylist.size();i++)
        {
            JSONObject oneSong = new JSONObject(); //do it inside the array, remember the reference thing with the circles on assignment 3
            oneSong.put("author",matrixArraylist.get(i)[0]); //so the end one should be an [] because our columns of matrixArraylist are arrays and not arraylist
            oneSong.put("title", matrixArraylist.get(i)[1]);
            oneSong.put("year", matrixArraylist.get(i)[2]);
            oneSong.put("genre", matrixArraylist.get(i)[3]);

            myJson.put(i,oneSong); //we can also do only myJson.put(oneSong) no need for i

        }





        System.out.println("\n\n");
        System.out.println(myJson);   //while printing the objects are not in the order like author, song name, year but that one does not matter


        //here to save the json file

        String fileName2 = "Albums.json";
        PrintWriter outFile = null;

        try
        {
            outFile = new PrintWriter(new File(fileName2));

            outFile.print(myJson);

            outFile.flush();
            outFile.close();

        }
        catch (FileNotFoundException ex)
        {
            System.out.print("\n Error opening file: " + fileName2);
        }

        //up to now we have an array of arrays,
        //we want to do an array of objects



        //now lets try to extract the title of the third song

        JSONObject thirdSong = myJson.getJSONObject(2); //up to here we got the third object of the myJson array,
        //in line 116 we do not also need to allocate space with the new operator as teh method getJSONObject does the allocation for ourselves

        String thirdStringTitle = thirdSong.getString("title");

        System.out.println("The title of the 3rd song is: " + thirdStringTitle);
        
        }
}
