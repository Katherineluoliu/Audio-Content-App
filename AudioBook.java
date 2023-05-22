//Katherine Luo Liu
//Student Id: 501165983
import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	/* This is the constructor of the AudioBook class */
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
		String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{  
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 
		super(title, year, id, type, audioFile, length);//pass in the parameter of title, year, id, type, audioFile, and length to the parent constructor
		this.author = author; //set the private variable "author" in this class to the given paramter "author"
		this.narrator = narrator; //set the private variable "narrator" in this class to the given paramter "narrator"
		this.chapterTitles = chapterTitles; //set the private variable "chapterTitles" in this class to the given paramter "chapterTitles"
		this.chapters = chapters; //set the private variable "chapters" in this class to the given paramter "chapters"
	}
	
	public String getType()
	{
		return TYPENAME;//In this class, it always returns AUDIOBOOK
	}

    // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		super.printInfo();//Use the printInfo() method in AudioContent to print the original info like title, id, year, type, length
		System.out.println("Author: "+this.author + " Narrated by: "+this.narrator);//Prints out extra characteristics of the audiobook class
	}

	// Get information about the song.
	// By making use of the getInfo() method in superclass AudioContent and then print artist, composer, genre 
	public String getInfo()
	{
		String info = super.getInfo();//Use the getInfo() method in AudioContent to get the original info like title, id, year, type, length
		info += (this.author + this.narrator);//gets extra characteristics of the audiobook class
		for (int i=0; i<chapterTitles.size(); i++){//Add all the chapter Titles
			info += chapterTitles.get(i);
		}
		for (int i=0; i<chapters.size(); i++){//Add all the chapters in accordance with the chapter
			info += chapters.get(i);//add the chapter contents to string info
		}
        return info;
	}
	
    // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		super.setAudioFile(this.chapterTitles.get(currentChapter) +".\n"+ this.chapters.get(currentChapter));//set the audiofile in the parent class by passing in the
		super.play(); //call the parent class of play to play out the song information anf the lyrics
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		for (int i=0; i<chapterTitles.size();i++){//loop through the chapterTitles because the chapterTitles is the Table Of Contents 
			System.out.println("Chapters "+(i+1) + ". " + chapterTitles.get(i)+"\n"); //Print out each chapterTitle with the correct format in the notepad/video
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{   
		//Make an object called AudioBook, this makes the parameter other become an AudioBook
		//The Audiobook obejct book will have all the attributes needed for comparison
		AudioBook book = (AudioBook) other;
		if (super.equals(book)){ //compares the basic attributes of book in the parent .equals() method
			return this.author.equals(book.author) && this.narrator.equals(book.narrator); //Compares the rest of the characteristics of the AudioBook class
		}
		return false;//If the "this" book is not the same as the "other" book, return false
	}
	
	public int getNumberOfChapters()
	{
        return chapters.size();
	}

	public String getAuthor()
	{
        return author;
	}

	public void setAuthor(String author)
	{
        this.author = author;
	}

	public String getNarrator()
	{
        return narrator;
	}

	public void setNarrator(String narrator)
	{
        this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
        return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
        this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
        return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
        this.chapters = chapters;
	}

}
