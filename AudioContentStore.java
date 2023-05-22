//Katherine Luo Liu
//Student Id: 501165983
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore //The Assignment 2's AudioContentStore is completly different from Assignment 1
{
    //Assignment 2 has extra maps feature////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<AudioContent> contents; //Stores the new contents from the store.txt file. 
    //Initialize maps, there are treemaps and hashmaps, but I chose to use a treemap because only the treemap is imported 
    private Map<String, Integer> titleMap; //The key is the title of the content and the value is the coresponding index
    private Map<String, ArrayList<Integer>> artist_author_Map; //Initialize a map to store artists as the key. The values is a integer arraylist of indexes of the artist's songs
    private Map<String, ArrayList<Integer>> genreMap;//Initialize a map to store the genres as the key and index as the value. 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public AudioContentStore()
    {
        //Assignment 2 has a different store than Assignment 1. 
        contents = new ArrayList<AudioContent>();

        audioContentFileReading();//call the private method to read the files
        
        //Assignment 2 has the features below//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // Given a title string, search the store to find a song/audiobook by that title.
        //The "store" is the contents variable that stores all songs, audiobooks and podcasts
        titleMap = new TreeMap<String, Integer>();//Initialize a map, there are treemaps and hashmaps, only treempas are imported at the top, so I'll use treemaps
        for (int i=0; i<contents.size(); i++){//Uses a for loop to loop over the contents variable
            titleMap.put(contents.get(i).getTitle(), (i+1));//Add the title as the key and add the index as the value. i+1 because store counts form 1
        }

        //Create another map that uses the artist (string) as a key and maps to an integer array list(rather than a single integer). 
        //The integers in the array list represent indices into the contents array list. 
        artist_author_Map = new TreeMap<String, ArrayList<Integer>>();
        for (int i=0; i<contents.size(); i++){//Uses a for loop to loop over the contents variable
            if (contents.get(i).getType().equalsIgnoreCase(Song.TYPENAME)){//If it's a song, get the artist
                Song newsong = (Song) contents.get(i);//Make the content into a song for ease of access to information in the Song.java class
                if (artist_author_Map.containsKey(newsong.getArtist())){//Check to see if the artist_author_Map contains the artist
                    ArrayList<Integer> index = artist_author_Map.get(newsong.getArtist());//the key is newsong.getArtist() and so artist_author_Map.get(newsong.getArtist()) will return an int
                    index.add(i+1);//looping through the contents, add the current index of the newly found song to the index ArrayList
                }
                else{//Else, the artist is not in the artist_author_Map yet
                    ArrayList<Integer> index = new ArrayList<Integer>(); //Make a new index arrayList for a new artist
                    index.add(i+1);//Add the current index of the found song to the index arrayList
                    artist_author_Map.put(newsong.getArtist(), index);//put the new artist name and the new index into the artist_author_Map
                }
            }
            else if(contents.get(i).getType().equalsIgnoreCase(AudioBook.TYPENAME)){//If it's a audio book, get the author
                AudioBook newbook = (AudioBook) contents.get(i);//Make the content into a audiobook for ease of access to information in the AudioBook.java class
                if (artist_author_Map.containsKey(newbook.getAuthor())){//Check to see if the artist_author_Map contains the author
                    ArrayList<Integer> index = artist_author_Map.get(newbook.getAuthor());//the key is newbook.getArtist() and so artist_author_Map.get(newsong.getArtist()) will return an int
                    index.add(i+1);//looping through the contents, add the current index of the newly found song to the index ArrayList
                }
                else{//Else, the artist is not in the artist_author_Map yet
                    ArrayList<Integer> index = new ArrayList<Integer>(); //Make a new index arrayList for a new author
                    index.add(i+1);//Add the current index of the found song to the index arrayList
                    artist_author_Map.put(newbook.getAuthor(), index);//put the new author name and the new index into the artist_author_Map
                }
            }
        }

        //Create a third map that uses the genre (string) of a song as a key and maps to an integer array list(rather than a single integer). 
        //The integers in the array list represent indices into the contents array list.
        genreMap = new TreeMap<String, ArrayList<Integer>>();
        for (int i=0; i<contents.size(); i++){
            if (contents.get(i).getType().equalsIgnoreCase(Song.TYPENAME)){//only songs have genres
                Song newsong = (Song) contents.get(i);//Make the content into a song for ease of access to information in the Song.java class
                if (genreMap.containsKey(newsong.getGenre().toString())){//If genreMap already has this genre, then add to the index
                    ArrayList<Integer> index = genreMap.get(newsong.getGenre().toString());//get the index of the songs
                    index.add(i+1);//Add the index of the song to an arraylist of integers
                }
                else{//if genreMap doesn't have this genre, make a new key and add a new arrayList
                    ArrayList<Integer> index = new ArrayList<Integer>();//Make a new arrayist of integer to store the indexes
                    index.add(i+1);//i+1 because the index on display starts at 1, but the for loop index starts at 0
                    genreMap.put(newsong.getGenre().toString(), index);//make a new genre by putting the genre's toString() as the key and the index as the value of the map
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }//End of constructor method

    private ArrayList<AudioContent> audioContentFileReading(){
        //Opens and reads from the file
        try{//Opens the file using a try
            File file = new File("store.txt"); //Create a new File called "file" to open store.txt
            Scanner in = new Scanner(file); //Use a scanner to read from "file" created from store.txt
            //Reads each line with a while loop 
            while (in.hasNextLine()){
                //Read using the scanner in, and using the method .nextLine() to read each line from store.txt
                String type = in.nextLine();//gets the type, Song or AudioBook, there are no podcasts in assignment 2
                String id = in.nextLine();
                String title = in.nextLine();
                int year = Integer.parseInt(in.nextLine());//Use Integer.pareInt to change the value of the string into an integer.
                int length = Integer.parseInt(in.nextLine());//Use Integer.pareInt to change the value of the string into an integer.

                if (type.equalsIgnoreCase("SONG")){
                    //Songs have these unique characteristics: artist, composer, genre, numberOfLinesOfLyrics, lyrics
                    String artist = in.nextLine();
                    String composer = in.nextLine();
                    Song.Genre genre = Song.Genre.valueOf(in.nextLine());//Go into Song's class, call upon the static variable Genre. Use .valueOf() to convert the string into type Genre
                    int numberOfLinesOfLyrics = Integer.parseInt(in.nextLine());//Use Integer.pareInt to change the value of the string into an integer.
                    String lyrics ="";//initiate lyrics
                    for (int i=0; i<numberOfLinesOfLyrics; i++){//loop to get all lines of lyrics
                        lyrics+= in.nextLine() + "\n";//get the lyrics but also add next line so the format is correct
                    }
                    //Make it into a song and add it to the contents arrayList. 
                    contents.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, genre, lyrics)); //the audiofile for song is the same as lyrics
                }
                else if (type.equalsIgnoreCase("AUDIOBOOK")){
                    //AudioBooks have these unique characteristics: author, narrator, numberOfChapters, chapterTitles, chapterLines and numberOfLinesInChapter
                    String author = in.nextLine();
                    String narrator = in.nextLine();
                    int numberOfChapters = Integer.parseInt(in.nextLine());//Use Integer.pareInt to change the value of the string into an integer.
                    ArrayList<String> chapterTitles = new ArrayList<String>();//make String arraylist to store chapter titles
                    for (int i=0; i<numberOfChapters; i++){//loop through all chapters to get their titles
                        chapterTitles.add(in.nextLine());
                    }
                    ArrayList<String> allChapterLines = new ArrayList<String>();//initializes chapterLines
                   
                    for (int i=0; i<numberOfChapters; i++){//loop through all chapters to get all chapter lines
                        int numberOfLinesInChapter = Integer.parseInt(in.nextLine());
                        String partialChapterLines ="";//gets ALL the lines of ONE chapter
                        for (int j=0; j<numberOfLinesInChapter; j++){//loop through all chapter lines to get the lines in a chapter
                            partialChapterLines+=(in.nextLine()+"\n");
                        }
                        allChapterLines.add(partialChapterLines);//Adds each CHAPTER BY CHAPTER into allChapters
                    }
                    //Use the informaiton gotten to make a new Audiobook
                    contents.add(new AudioBook(title, year, id, AudioBook.TYPENAME,  "", length, author, narrator,chapterTitles, allChapterLines));
                }
            }
            in.close();//Close the scanner so it stops reading from the file
        }
        //A catch is needed when opening and reading a file
        catch (IOException e){//Catch any IO exceptions so the program doesn't crash
            System.out.println(e);//Print out the IO error 
        }

        return contents;
    }


    //Assignment 2 has the following getter  feature///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Map<String, Integer> getTitleMap(){//getter method for titleMap
        return this.titleMap;
    } 

    public Map<String, ArrayList<Integer>> getArtistAuthorMap(){//getter method for titleMap
        return this.artist_author_Map;
    } 

    public Map<String, ArrayList<Integer>> getGenreMap(){//getter method for titleMap
        return this.genreMap;
    } 

    public ArrayList<AudioContent> getContents(){//returns all the info that was read from store.txt
        return this.contents;//returns all the contents arraylist
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Below are the methods from Assignment 1
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public AudioContent getContent(int index)
    {
        if (index < 1 || index > contents.size())
        {
            return null;
        }
        return contents.get(index-1);
    }

    public void listAll()
    {
        for (int i = 0; i < contents.size(); i++)
        {
            int index = i + 1;
            System.out.print("" + index + ". ");
            contents.get(i).printInfo();
            System.out.println();
        }
    }

    
}
