//Katherine Luo Liu
//Student Id: 501165983
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.management.RuntimeErrorException;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library 
{
    private ArrayList<Song> songs; //Stores the arralist of songs that are downloaded
    private ArrayList<AudioBook> audiobooks; //Stores the arraylist of audiobooks that are downloaded
    private ArrayList<Playlist> playlists;  //Stores the arraylist of playlists from the user input
    private ArrayList<Podcast> 	podcasts; //Stores the arraylist of podcasts that are downloaded
	
    // Public methods in this class set errorMesg string 
    // Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
    // In assignment 2 we will replace this with Java Exceptions
    String errorMsg = "";

    public String getErrorMessage()
    {//returns the errorMsg so that the user have an idea about the problem
        return errorMsg;
    }

	/*This is the constructor class of the Library. It is used to initialize the arraylists */
    public Library()
    {
        songs 	= new ArrayList<Song>(); 
        audiobooks = new ArrayList<AudioBook>(); ;
        playlists  = new ArrayList<Playlist>();
      	podcasts = new ArrayList<Podcast>(); ;
    }
    /*
     * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
     * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
     * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
     * to determine which list it belongs to above
     * 
     * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
     * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
     * See the video
     */
    public void download(AudioContent content)
    {	
		//Check to see if the audio content by getting the content type using the getType() method
		//The content can be either a song, audiobook, or a podcast. The getType() method will correspond to the type of the content. 
		//For example, if the content type is a song, then the .getType() method in the Song.java will be called
		//The static variable TYPENAME corresponds to the content type. For example, a song's TYPENAME variable has the value SONG
		//Static variable means that we can access this variable outside of the class that it was initiated. It means that the variable is shared. 
		if ( (content.getType().equals(Song.TYPENAME))){ //getting the content type and comparing it with the static variable TYPENAME 
			Song song_content = (Song) content; //Since the type is song, we can convert the content into a song for conparison
			if (songs.contains(song_content)==false ){ //Check to see if the song_content is already downloaded into the songs arraylist 
				songs.add(song_content); //If the song is not already in the songs arraylist, add it to the songs arraylist
			}
			else
				throw new alreadyDownloadedException("Song " + content.getTitle() + " already downloaded");
		}
		else if ( (content.getType().equals(AudioBook.TYPENAME))){ //getting the content type and comparing it with the static variable TYPENAME
			AudioBook audiobook_content = (AudioBook) content;//From the previous line of code, we know that it is an Audiobook, thus turn the content into an audiobook for comparison
			if (audiobooks.contains(audiobook_content)==false ){//Check to see if the audiobooks arraylist already contains the audiobook_content
				audiobooks.add(audiobook_content);//If not, add to the audiobooks arraylist
			}
			else
				throw new alreadyDownloadedException("AudioBook "+content.getTitle() + " already downloaded");//If the audiobook arraylist already contains the audiobook, don't add it to the audiobooks arraylist and return the error message 
		}
		else if ( (content.getType().equals(Podcast.TYPENAME))){//getting the content type and comparing it with the static variable TYPENAME
			Podcast podcast_content = (Podcast) content; //If the "type" is a podcast, then make the content into podcast for conpairson purposes. (you must only compare the same type)
			if (podcasts.contains(podcast_content)==false ){//Check to see if the podcast arraylist already contains the podcast
				podcasts.add(podcast_content);//If podcasts arraylist don't aready have the podcast_content, add it to the podcasts arraylist
			}
			else
				throw new alreadyDownloadedException("Podcast " + content.getTitle() + " already downloaded");//If the podcasts arraylist already contains podcast_content, then don't add it to podcasts and return the error message
		}
    }
	
    // Print Information (printInfo()) about all songs in the array list
    public void listAllSongs()
    {
        for (int i = 0; i < songs.size(); i++)//loop through the songs arraylist using songs.size() to find the length of songs
        {
            int index = i + 1;//The index always start at 0, so we must add one for the output format purposes
            System.out.print("" + index + ". "); //Output the index with a period and a space
            songs.get(i).printInfo(); //get the song using songs.get(i) where .get(i) gets the index of the songs arraylist
            System.out.println();//print out an extra line for format purposes
        }
    }
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i=0; i<audiobooks.size(); i++){//loop through the audiobook arraylist using audiobooks.size() to find the length of audiobooks
			System.out.print("" + (i+1) + ". "); //Prints out the index+1 followed by a period and a space
			audiobooks.get(i).printInfo();//get the audiobook using audiobooks.get(i) where .get(i) gets the index of the audiobooks arraylist
			System.out.println();//print out an extra line for format purposes
		}
	}
	
    // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
        for (int i=0; i<podcasts.size(); i++){//loop through the podcasts arraylist using podcasts.size() to find the length of podcasts
			System.out.print("" + (i+1) + ". ");//Prints out the index+1 followed by a period and a space
			podcasts.get(i).printInfo();//get the podcast using podcasts.get(i) where .get(i) gets the index of the podcasts arraylist
			System.out.println();//print out an extra line for format purposes
		}
	}
	
    // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i=0; i<playlists.size(); i++){//loop through the playlist using playlist.size() to find the length of the arraylist
			//playlists.get(i) means to get the playlist corresponding to the playlists arraylist, it returns the playlist
			//Then .getTitle() means to get the title of that specific arraylist. 
			System.out.println((i+1) + ". " + playlists.get(i).getTitle()); //Prints out the index followed by a period and a space, followed by each playlist's title
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names
		ArrayList <String> artist = new ArrayList <String>(); //Made an arraylist of strings to store the artist name information
 		for (int i=0; i<songs.size(); i++){ //loop through the songs arraylist to find each artist 
			if (artist.contains(songs.get(i).getArtist())==false){//check to see if the artist arraylist already contains this song's artist(songs.get(i).getArtist())
				artist.add(songs.get(i).getArtist());//If the artists is not already in the artist arraylist, add it to the artist arraylist
			}
		}

		//After getting all the artists and storing it all in the artist arraylist, print them out using the below for loop
		for (int i=0; i<artist.size(); i++){//loop though the artist arraylist to print each artist
			System.out.println(i+1 +". " + artist.get(i)); //print the index followed by a period and a space followed bby the artist name using artist.get(i)
		}

	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		if (index<0 || index>=songs.size()){//if the index is invalid, automoatically return false
			throw new invalidIndexException("Invalid index");
		}

		Song s_content =  songs.get(index);
		for (int i=0; i<playlists.size(); i++){
			//Since we are deleting a song, the type must also be a song. 
			//We can use songs.get(index) to get the song from the songs arraylist
			//Since playlist can consiste of either a song, audiobook or a podcast, we must compare using the "AudioContent" type
			
			//First use .get(i) to get a/one playlist from the playlists arraylist
			//Then use .getContent() to get the content of the playlist. This method is in Playlist.java
			//The .contains( songs.get(index) ) method checks to see if the playlist contains the song that needs to be deleted
			//If it does contain, then .playlists.get(i).getContent().remove( s_content ); removes that song from the playlist
			for (int j=0; j<playlists.get(i).getContent().size(); j++){
				if (playlists.get(i).getContent().get(j).getType().equals(Song.TYPENAME)){
					if ( playlists.get(i).getContent().get(j).equals(s_content) ){ //check if the playlist contains the song
						playlists.get(i).deleteContent( j+1 );//remove the song from the playlist
					}
				}
			}
		}
		//remove the song last because if we removed the song, we can't get the song anymore, then we can't use it to see if the song is still in the playlist
		songs.remove(index);//remove the song from the songs arraylist
		
		
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{	
		// Use Collections.sort() 
		//Uses Collections.sort() and pass in the songs arraylist for comparison in the SongYearComparator() class
		Collections.sort( this.songs, new SongYearComparator() );
	}
  	
	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{	
		//java interface. 
		public int compare(Song a, Song b) //Takes in the Song a and Song b as parameters for comparison
		{
			if (a.getYear() > (b.getYear())){//If the first song has a greater year, return 1
				return 1;
			}
			else if (a.getYear() < (b.getYear())){//if the second song has a greater year, return -1
				return -1;
			}
			else return 0;//If both the songs are from the same year, return 0
		}
		
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 	// Use Collections.sort() 
		//Uses Collections.sort() and pass in the songs arraylist for comparison in the SongLengthComparator() class
		Collections.sort( this.songs, new SongLengthComparator() );
	}

  	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator <Song>
	{
		//java interface
		public int compare(Song a, Song b) //compares song a and song b
		{
			if (a.getLength() > (b.getLength())){//if the first song's lyrics are longer than the second song, return 1
				return 1;
			}
			else if (a.getLength() < (b.getLength())){//If the second song's lyrics are longer than the firts song, return -1
				return -1;
			}
			else return 0;//If both the songs have the same length, return 0
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  	// Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		//Since the song implements the comparable interface, we can use collections.sort(songs) to call upon the song class
		Collections.sort(songs); //Collections.sort() is a built in method to sort arrayLists to sort the songs alphabetically
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{//If the index is not valid, return song not found
			throw new invalidIndexException("Invalid index");
		}
		songs.get(index-1).play();//If the song is found, get the song using .get(index-1) and play the song using .play() method in the song class
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		//the index for index, season nd episode all start at 0, so we have to minus one from the user input for the correct index
		//The minus one is done in MyAudioUI.java when the parameters are passed into playPodcast
		if (index>=0 && index<podcasts.size() && season>=0 && episode>=0){//Check to see if the index, season and episode is acceptable
			podcasts.get(index).selectEpisodes(season, episode);//Get the podcast using .get(index) and select and print the episode by calling selectEpisodes in the podcast class
			return true;//return true when the selected episode is outputed
		}
		errorMsg = "Podcast Not Found";//if the podcast is not found in the podcasts arraylist, then the error message is "Podcast Not Found"
		return false;//return false
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		//The index and the season both have index starting at 0. In MyAudioUI, the parameters are subtracted by one when passing in the parameters
		if (index>=0 && index<podcasts.size() && season>=0){//Check to see if the index and seaon variables are acceptable/reasonable
			podcasts.get(index).getAllEpisodes(season);//use podcasts.get(index) to get the specific index of the podcasts arraylist, then use .getAllEpisodes to print out all episodes
			return true;//return true after printing all episodes
		}
		errorMsg = "There Podcast Number or the Season is invalid.";
		return false;//return false if the episodes are not printed
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		//the index and the chapter starts at 0, but when using .selectChapter(chapter), the chapter variable is automatically subtracted by one
		//Thus, in MyAudioUI, the parameters only subtracted one from the index, and not the chapter
		if (index<0 || index>=audiobooks.size()){
			throw new invalidIndexException("AudioBook Not Found: Invalid Index");
		}
		else if (chapter<=0 || chapter>audiobooks.get(index).getNumberOfChapters()){
			throw new invalidChapterException("AudioBook Not Found: Invalid Chapter");
		}
		else{
			audiobooks.get(index).selectChapter(chapter);//Select the chapter by getting the audiobook at the index and calling .selectchapter(chapter) in the AudioBook class
			audiobooks.get(index).play();//After selecting the chapter, play it using .play() in the AudioBook class
		}
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		if (index>=0 && index<audiobooks.size()){//check to see if the index is acceptable
			audiobooks.get(index).printTOC();//get the audiobook in the arraylist of audiobooks and executes the printTOC() method from the AudioBook class
		}
		else 
			throw new invalidIndexException("Index Out Of Bounds");
	}
	
	/*
	* Playlist Related Methods
	*/
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		//make a new playlist and pass in the varible title 
		Playlist playlist = new Playlist(title); //calls the constructor in the playlist class that accepts a string for its parameter
		
		for (int i=0; i<playlists.size(); i++){//loop through the playlists to find the correct playlist corresponding to the title
			if (playlists.get(i).getTitle().equals(playlist.getTitle())){//compare the titles using getTitle() method from the playlist class
				throw new playlistAlreadyExistsException("Playlist " + title + " Already Exists");
			}
		}
		playlists.add(playlist);//if the playlist is not in the playlists arraylist, add it to playlists
		
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		boolean found = false;
		for (int i=0; i<playlists.size(); i++){//loop through the playlists to get each playlist title
			if (playlists.get(i).getTitle().equals(title)){//get the playlist title using .getTitle() to compare the title
				playlists.get(i).printContents();////print the contents of the playlist using printContents() from the playlist class 
				found=true;
			}
		}
		if (found==false){
			throw new noSuchPlaylistException("The playlist is not found. Please check for spelling, the program is case sensitive.");
		}
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		boolean found = false;
		for (int i=0; i<playlists.size(); i++){//loop through the playlists to get each playlist title
			if (playlistTitle.equals(playlists.get(i).getTitle())){//if title is the same
				playlists.get(i).playAll();//Get the playlist and play all the content inside the playlist. This can be the episodes, the lyrics or the chapters
				found = true;//return true after the playlist is played
			}
		}
		if (found==false){
			throw new noSuchPlaylistException("The playlist is not found. Please check for spelling, the program is case sensitive.");
		}
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		boolean found = false;
		System.out.println(playlistTitle);//Print the title of the playlist
		for (int i=0; i<playlists.size(); i++){ //loop through the playlists arraylist to find the playlist with the same title
			if (playlistTitle.equals(playlists.get(i).getTitle())){//Compares the title to see if it's the same 

				if (indexInPL<0 || indexInPL>= playlists.get(i).getContent().size()){//check the index of the playlist
					throw new invalidIndexException("Index Out Of Bounds");
				}

				playlists.get(i).play(indexInPL); //Get the playlist and play the playlist at the given index
				found=true;
			}
		}
		if (found==false){
			throw new noSuchPlaylistException("The playlist is not found. Please check for spelling, the program is case sensitive.");
		}
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{	
		boolean found = false;
		//Check for valid type first
		if (!type.equalsIgnoreCase(Song.TYPENAME) && !type.equalsIgnoreCase(AudioBook.TYPENAME) && !type.equalsIgnoreCase(Podcast.TYPENAME)){//check the type
			throw new invalidTypeException("Invalid type. The type must be either a \"song\", \"audiobook\", or a \"podcast\"");
		}

		for (int i=0; i<playlists.size(); i++){
            if (playlistTitle.equals(playlists.get(i).getTitle())){
                if (type.equalsIgnoreCase(Song.TYPENAME)){
					if (index<0 || index>=songs.size()){//check the index
						throw new invalidIndexException("Invalid index.");
					}
                    AudioContent songContent = (AudioContent) songs.get(index);
					//if (playlists.get(i).getContent().contains(songContent)){//checks if the playlist already contains the new content
					//	throw new alreadyInPlaylistException("This song is already in this playlist");
					//}
                    playlists.get(i).addContent(songContent);
					found=true;
                }
                else if (type.equalsIgnoreCase(AudioBook.TYPENAME)){
					if (index<0 || index>=audiobooks.size()){//check the index
						throw new invalidIndexException("Invalid index.");
					}
                    AudioContent audioBookContent = (AudioContent) audiobooks.get(index);
					//if (playlists.get(i).getContent().contains(audioBookContent)){//checks if the playlist already contains the new content
					//	throw new alreadyInPlaylistException("This audiobook is already in this playlist");
					//}
                    playlists.get(i).addContent(audioBookContent);
                    found=true;
                }
				else if (type.equalsIgnoreCase(Podcast.TYPENAME)){//Compare the type to see it's a podcast
					if (index<0 || index>=podcasts.size()){//check the index
						throw new invalidIndexException("Invalid index.");
					}
					Podcast podCastContent = (Podcast) podcasts.get(index);//Since the type is Podcast, make it into a audiocontent for comparison
					//if (playlists.get(i).getContent().contains(podCastContent)){//checks if the playlist already contains the new content
					//	throw new alreadyInPlaylistException("The podcast is already in this playlist");
					//} 
					playlists.get(i).addContent(podCastContent);//Since podCastContent is an AudioContent, it is the same type as playlist and so add it to the playlist
					found = true;
				}
            }
        }

		if (found == false){//since both the index and type is checked, the only problem left is the playlist not found
			throw new noSuchPlaylistException("The playlist is not found. Please check for spelling, the program is case sensitive.");
		}
	}

  	// Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		boolean found = false;
		for (int i=0; i<playlists.size(); i++){//loop through the playlist to find the title
			if (title.equalsIgnoreCase(playlists.get(i).getTitle())){//Uses .getTitle() to get the title and compare the title

				if (index<0 || index> playlists.get(i).getContent().size()){//index can be equal to playlist.get(i).getContent().size() because the line below uses .contains to find the index
					throw new invalidIndexException("Index Out Of Bounds");
				}

				if(playlists.get(i).contains(index)){//check to see if the playlist contains the content that we want to delete
					//We need to add index by 1 when passing in the parameter to .deleteContent() because the .deleteContent() method in playlists will
					//delete the index-1. Note: we have already subtracted one from index in MyAudioUI when calling this method
					playlists.get(i).deleteContent(index);//get the playlist and delete the content by passing in the index
					found = true;
				}
			}
		}

		if (found == false){//since both the index and type is checked, the only problem left is the playlist not found
			throw new noSuchPlaylistException("The playlist is not found. Please check for spelling, the program is case sensitive.");
		}
	}
}

//Assignment 2 has exception types instead of returning the boolean true or false
class alreadyDownloadedException extends RuntimeException {
	public alreadyDownloadedException(String message){
		super(message);
	}
}

class invalidIndexException extends RuntimeException {
	public invalidIndexException(String message){
		super(message);
	}
}

class invalidChapterException extends RuntimeException{
	public invalidChapterException(String message){
		super(message);
	}
}

class playlistAlreadyExistsException extends RuntimeException{
	public playlistAlreadyExistsException(String message){
		super(message);
	}
}

class noSuchPlaylistException extends RuntimeException{
	public noSuchPlaylistException(String message){
		super(message);
	}
}

class invalidTypeException extends RuntimeException{
	public invalidTypeException(String message){
		super(message);
	}
}

class alreadyInPlaylistException extends RuntimeException{
	public alreadyInPlaylistException(String message){
		super(message);
	}
}