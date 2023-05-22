//Katherine Luo Liu
//Student Id: 501165983
/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable<Song>// implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
		String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
		super(title, year, id, type, audioFile, length);//Put parameters into super so the variables can be initialized in the super method
		this.artist= artist;//Assigning the given value artist to the private variable artist
		this.composer = composer;//Assigning the given value composer to the private variable composer
		this.genre = genre;//Assigning the given value genre to the private variable genre
		this.lyrics = lyrics;//Assigning the given value lyrics to the private variable lyrics
	}
	
	public String getType()
	{
        return TYPENAME;//Returns the constant TYPENAME varibale value. In this class, it is always SONG. 
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		super.printInfo();//Use the printInfo() method in AudioContent to print the original info like title, id, year, type, length
		System.out.println("Artist: "+this.artist + " Composer: "+this.composer +" Genre: "+this.genre );//Prints out extra characteristics of the song class
            
	}
	// Get information about the song. 
	// By making use of the getInfo() method in superclass AudioContent and then print artist, composer, genre 
	public String getInfo()
	{
		//gets all info of the song
		String info = super.getInfo();//Use the getInfo() method in AudioContent to print the original info like title, id, year, type, length
		info += (this.artist +this.composer+this.genre );//Prints out extra characteristics of the song class
		info += (this.lyrics );//The audioFile is the same as lyrics for a song
        return info;//return the string
	}

	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{   
        super.setAudioFile(this.lyrics);//set the audiofile in the parent class by passing in the lyrics as the parameters
        super.play(); //call the parent class of play to play out the song information anf the lyrics
	}
	
	public String getComposer()
	{
        return composer;
	}

	public void setComposer(String composer)
	{
        this.composer = composer;
	}
	
	public String getArtist()
	{
        return artist;
	}

	public void setArtist(String artist)
	{
        this.artist = artist;
	}
	
	public String getLyrics()
	{
        return lyrics;
	}

	public void setLyrics(String lyrics)
	{
        this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
        return genre;
	}

	public void setGenre(Genre genre)
	{
        this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{   
		//Make an object called song, this makes the parameter other become a song
		//The Song obejct song will have all the attributes needed for comparison
		Song song = (Song) other;

		if (super.equals(song)){//use the .equals() method from the parent class to compare the songs by passing in the paramter song
			//if the .equals() method in the super class is true, then compare the rest of the song characteristics
			return this.composer.equals(song.composer) && this.artist.equals(song.artist); //Compare the composer and the artists
		}
		return false;//If the song doesn't have the same characteristsics in the super class, automatically return false
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other) 
	{
		//The compareTo is a built in method for Strings
		if (this.getTitle().compareTo(other.getTitle()) > 0 ){//It will return >0 when the first title is alpabetically furthur down the alphabet than the second title
			return 1;//thus, we will return one to indicate that the first title is bigger than the second title
		}
		else if (this.getTitle().compareTo(other.getTitle()) < 0){//If the first title is smaller than the second title
			return -1;//it will return -1
		}
		else return 0;//if the titles are the same, then it will return 0
	}
}
