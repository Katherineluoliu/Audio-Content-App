/*
 *  Audio Content contains information common to all types of audio (e.g. songs, audiobooks etc)
 */

//Katherine Luo Liu
//Student Id: 501165983
 abstract public class AudioContent
{
	private String title; //title of the content
	private int year; // year published
	private String id; // id
	private String type; //the type of audiocontent wether it is a song, podcast or an audiobook
	private String audioFile; // file containing the audio content - e.g. a song.
	private int length; // minutes
	
	/*This is the constructor of the AudioContent class. 
	When no values are given, we initialize the private variables accordingly so the program doesn't crash when there is no value assigned. */
	public AudioContent()
	{
		this.title = ""; //Title is a string and thus assigned an empty string
		this.year = 0; //Year is an integer and thus assigned 0
		this.id = ""; //Id is a string and thus assigned an empty string
		this.type = "AUDIOCONTENT"; //The type is always AUDIOCONTENT because this is the AudioContent class
		this.audioFile = ""; //Audiofile is a string and thus assigned an empty string
		this.length = 0; //Length is an integer and thus assigned the value of 0
	}
	
	/*This is the parent constructor of podcast, song, and audiobook. 
	 * This assigns the given values to the private variables e
	 */
	public AudioContent(String title, int year, String id, String type, String audioFile, int length)
	{
		this.title = title; //Assigns the given title from the parameter to the private variable title
		this.year = year; //Assigns the given year from the parameter to the private variable year
		this.id = id; //Assigns the given id from the parameter to the private variable id
		this.type = type; //Assigns the type from the paramter to the private variable type
		this.audioFile = audioFile; //Assigns the given audioFile to the private variable audioFile
		this.length = length; //Assigns the given length from the parameters to the private variable length
	}

	// Subclasses define their type (e.g. "Song") 
	abstract public String getType();//automatically returns the type

	// Print Information about the auidocontent
	public void printInfo()
	{	
		System.out.println("Title: " + title + " Id: " + id + " Year: " + year + " Type: " + type + " Length: " + length);	 
	}

	//Returns the info 
	public String getInfo()
	{	
		return(title + id + year + type +length);	 
	}
	
    // Play the content via the audio file
	public void play()
	{
		this.printInfo(); //calls the printInfo() from this method -> prints out the information about the content 
		// Simulate playing of the audio file. For example, for a song this would be printing the lyrics
		System.out.println("\n" + audioFile);
	}
	
	// Two AudioContent objects are equal if they have the same title and id
	public boolean equals(Object other)
	{
		//Make the Object other into a AudioContent so that "this" object can have the same type and compare with the "other" object
		AudioContent otherCon = (AudioContent) other;
		return title.equals(otherCon.title) && id.equals(otherCon.id);//The parent class compares the title and the id
	}
  
	public String getAudioFile()
	{
        return this.audioFile;
	}

	public void setAudioFile(String file)
	{
        this.audioFile = file;
	}

	public int getLength()
	{
        return this.length;
	}

	public void setLength(int length)
	{
        this.length = length;
	}

	public String getTitle()
	{
        return this.title;
	}

	public void setTitle(String title)
	{
        this.title = title;
	}

	public int getYear()
	{
        return this.year;
	}

	public void setYear(int year)
	{
        this.year = year;
	}

	public String getId()
	{
        return this.id;
	}

	public void setId(String id)
	{
        this.id = id;
	}

}
