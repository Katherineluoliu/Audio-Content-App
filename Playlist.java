//Katherine Luo Liu
//Student Id: 501165983
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title; //stores the title of the playlist name
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	/*This method is used to create a playlist object */
	public Playlist(String title)
	{
		this.title = title; //assigns the parameter "title" to the private variable "title"
		contents = new ArrayList<AudioContent>(); //Initialized the contents ArrayList
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{	
		for (int i=0; i<contents.size(); i++){//Loops through the contents by using a for loop and finding the contents.size() 
			System.out.print((i+1)+". "); //Prints out the index followed by a period and a space ". " according to the video/notepad
			contents.get(i).printInfo(); //contents.get(i) gets the content according to the index and .printInfo() prints the information about the selected content
			System.out.println(); //print an extra line in the end according to the format of the video/notepad
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{	
		for (int i=0; i<contents.size(); i++){//Loops through the contents by using a for loop and finding the contents.size() 
			contents.get(i).play(); //contents.get(i) gets the content according to the index and the .play() method is executed according the type of the content. 
			System.out.println();//prints an extra line in the end according to the format of the video/notepad
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{	
		//The index must >=0 because the arrayList of values start at index 0
		//The index must also be less than the contents.size() because the index starts at 0
		if (index>=0 && index<contents.size()){
			//contens.get(index) gets the content (wether a song, audiobook or a podcast) according to the index 
			//then, the .play() goes inside (song, audiobook or podcast) and executes the play method 
			(contents.get(index)).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{	
		//Alternative, replace 
		//Make the object other into an AudioContent because AudioContent is the parent class of audiobook, song and podcast
		//Thus, the object other can have the same characteristic/type as this object. Then, we can compare the two. 
		AudioContent AudioC = (AudioContent) other;
		if (this.getTitle().equals(AudioC.getTitle())){//uses the .equals() method to compare the titles of "this" and the "other", which is called the "AudioC"
			return true; //If everything works and executes, then it will return true
		}

		return false; //If the titles are not the same, then it will return false 
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{	
		//if the arrayList contents doesn't contain the index, don't remove
		if (!contains(index)) return; //if the contents arrayList does not contain the index, nothing happens. 
		contents.remove(index-1); //if the index is valid, then delete the index using index-1 from the contents arrayList. 
		//Use index-1 because the index is a given number from the user. An arraylist starts with the index 0, so we have to minus one. 
	}
	
	
}
