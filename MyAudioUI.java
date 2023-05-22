//Katherine Luo Liu
//Student Id: 501165983
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		//Creae a a scanner to get input fom the user
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		
		while (scanner.hasNextLine()){
			try{
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists();
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				//In assignment 2, this download function is modified to download a range of content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					int fromindex = 0;//The index initially start at 0
					int toindex = 0;//The index initially ends at 0
					System.out.print("From Store Content #: ");
					fromindex = scanner.nextInt();//get the input and store it in the variable index
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.print("To Store Content #: ");
					toindex = scanner.nextInt();//get the input and store it in the variable index
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					for (int i=fromindex; i<=toindex; i++){//loop through the range of indexes
						try{
							AudioContent content = store.getContent(i);//using the given index, make it into a AudioContent
							if (content == null){//If the index is invalid, then output "Content Not Found in Store"
								throw new IllegalArgumentException("Content Not Found in Store");
							}
							mylibrary.download(content);//download the content
							System.out.println(content.getType() + " " + content.getTitle() + " Added To Library");//print message saying added to library
						}
						//catch the exceptions that are thrown from library.java
						catch(alreadyDownloadedException e){
							System.out.println(e.getMessage());
						}
						catch(IllegalArgumentException e){
							System.out.println(e.getMessage());
						}
						
					}
					
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{	
					System.out.print("Song Number: ");//prompts the user for an index
					int index = 0;//initializes the index to 0
					index = scanner.nextInt();//gets the user input and store it in the index variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					//Pass in the index as index, instead of index-1, because the method of playSong(int index) already subtracted 1 from the index
					try{
						mylibrary.playSong(index);
					}
					//catch the exceptions that are thrown from library.java
					catch(invalidIndexException e){
						System.out.println(e.getMessage());
					}
					
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{   
					System.out.print("Audio Book Number: ");//prmots the user for an index
					int index = 0;//initializes the index variable to 0
					index = scanner.nextInt();//gets the user input and store it in the index variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					//the index is subtracted by one when passes into .printAudioBookTOC() method because the index starts at 0, but the input from the user start counting at 1
					try{
						mylibrary.printAudioBookTOC(index-1);
					}
					//catch the exceptions that are thrown from library.java
					catch (invalidIndexException e){
						System.out.println(e.getMessage());
					}
							
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					System.out.print("Audio Book Number: ");//promts the user for an index
					int index = 0; //initializes the index to 0
					int chapter =0 ; //initializes the chapter to 0
					index = scanner.nextInt();//get the index from the user and store it in the index variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.print("Chapter: ");//promt the user to enter in a chapter number
					chapter = scanner.nextInt();//get the chapter and store it in the chapter variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					//The parameter index-1 is passed inside .playAudioBook() because the user counts from 1. But the arraylist index starts at 0
					//The chapter isnt subtracted by 1 because in .playAudioBook() which calls and uses .selectChapter() in the AudioBook class and subtract by 1 automatically 
					try{
						mylibrary.playAudioBook(index-1, chapter);
					}
					//catch the exceptions that are thrown from library.java
					catch(invalidIndexException e){
						System.out.println(e.getMessage());
					}
					catch(invalidChapterException e){
						System.out.println(e.getMessage());
					}
				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{	
					int podcastNumber = 0; //initializes the podcastNumber to 0
					int season = 0;//initializes the season to 0
					System.out.print("Podcast Number: "); //Prompts the user to enter in an index called podcast number
					podcastNumber = scanner.nextInt();//get the input from the user and store it in podcastNumber
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					System.out.print("Season: "); //Prompts the user to chose a season 
					season = scanner.nextInt();//get the input and store it in season variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					if (mylibrary.printPodcastEpisodes(podcastNumber-1, season-1)==false)//uses mylibrary to call the printPodcastEpisodes() method from the Library.java
						System.out.println(mylibrary.getErrorMessage());
				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					int podcastNumber = 0;//initializes the podcastNumber to 0
					int season =0;//initializes the season to 0
					int episode = 0;//initializes the episode to 0
					System.out.print("Podcast Number: ");//Prompts the user to enter in an index called podcast number
					podcastNumber = scanner.nextInt();//get the input and store it in podcastNumber variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					System.out.print("Season: ");//Prompts the user to enter in a season
					season = scanner.nextInt();//get the input and store it in season variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					System.out.print("Episode: ");//Prompts the user to enter in an episode
					episode = scanner.nextInt();//get the input and store it in episode variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					if (mylibrary.playPodcast(podcastNumber-1, season-1, episode-1)==false){//calls the .playPodcast from the library class 
						System.out.println(mylibrary.getErrorMessage());//prints out the error message if the .playPodcast() method is false
					}
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					System.out.print("Playlist Title: ");//prompts the user to enter a title
					String title ="";//initializes the title to ""
					title = scanner.nextLine();//get the input and store it in title variable
					
					try{
						mylibrary.playPlaylist(title);
					}
					//catch the exceptions that are thrown from library.java
					catch(noSuchPlaylistException e){
						System.out.println(e.getMessage());
					}
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					System.out.print("Playlist Title: ");//prompts the user to enter a title
					String title ="";//initializes the title to ""
					title = scanner.nextLine();//get the input and store it in title variable
					
					System.out.print("Content Number: ");//prompts the user to enter an index to get the content 
					int index = 0;//initializes 0 to the index
					index = scanner.nextInt();//gets the input and store it in index variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					try{
						mylibrary.playPlaylist(title, index-1);
					}
					//catch the exceptions that are thrown from library.java
					catch(invalidIndexException e){
						System.out.println(e.getMessage());
					}
					catch(noSuchPlaylistException e){
						System.out.println(e.getMessage());
					}
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					System.out.print("Library Song #: ");//prmompts the user to enter an index
					int index = 0;//initialize the index to 0
					index = scanner.nextInt();//get the input from the user and store it in index
					scanner.nextLine(); //"consume" nl character (necessary when mixing nextLine() and nextInt())
					try{
						mylibrary.deleteSong((index-1));
					}
					//catch the exceptions that are thrown from library.java
					catch(invalidIndexException e){
						System.out.println(e.getMessage());
					}
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					System.out.print("Playlist Title: ");//prompts the user to enter a title
					String title ="";//initializes the title to ""
					title = scanner.nextLine();//gets the title and stores it to title
					
					try{
						mylibrary.makePlaylist(title);
					}
					//catch the exceptions that are thrown from library.java
					catch(playlistAlreadyExistsException e){
						System.out.println(e.getMessage());
					}
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					System.out.print("Playlist Title: ");//promts the user for the title
					String title ="";//initializes the title to ""
					title = scanner.nextLine();//gets the title and store it in the title variable
					
					try{
						mylibrary.printPlaylist(title);
					}
					//catch the exceptions that are thrown from library.java
					catch(noSuchPlaylistException e){
						System.out.println(e.getMessage());
					}
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					System.out.print("Playlist Title: ");//promts the user to enter a title
					String title="";//initializes the title to ""
					title = scanner.nextLine();//gets the title and stores it in the title variable
					
					//need to check if the playlist title is there? 
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");//Ask the user for the type of the playlist that they are adding
					String type ="";//initialize the type to ""
					type = scanner.nextLine();//gets the type and store it in type variable
					
					System.out.print("Library Content #: ");//promts the user to enter a library content which is also the index
					int index = 0;//initializes the index to 0
					index = scanner.nextInt();//gets the index and stores it to variable index
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					try{
						mylibrary.addContentToPlaylist(type, index-1, title);
					}
					//catch the exceptions that are thrown from library.java
					catch (invalidTypeException e){
						System.out.println(e.getMessage());
					}
					catch (invalidIndexException e){
						System.out.println(e.getMessage());
					}
					catch (noSuchPlaylistException e){
						System.out.println(e.getMessage());
					}
					catch (alreadyInPlaylistException e){
						System.out.println(e.getMessage());
					}
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					System.out.print("Playlist Title: ");//prmots the user to enter a title
					String pltitle ="";//initializes the playlist title to ""
					pltitle = scanner.nextLine();//gets the title of the playlist and store it in pltitle
					
					System.out.print("Playlist Content #: ");//promts the user to get a playlist content which is also the index
					int index = 0;//initializes the index to be 0
					index = scanner.nextInt();//gets the input and stores it in the index variable
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					try{
						mylibrary.delContentFromPlaylist(index, pltitle);//pass in the parameters to delContentFromPlaylist
					}
					//catch the exceptions that are thrown from library.java
					catch(invalidIndexException e){
						System.out.println(e.getMessage());
					}
					catch(noSuchPlaylistException e){
						System.out.println(e.getMessage());
					}
				}
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();//uses mylibrary to execute the .sortSongsByYear() in the library class
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();//uses mylibrary to execute the .sortSongsByName() in the library class
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();//uses mylibrary to execute the .sortSongsByLength() in the library class
				}
				//Assignment 2 has the extra features below////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//SEARCH: If the audio content with this title is found in the store then print the index of this content and the info for this content. 
				else if (action.equalsIgnoreCase("SEARCH")){
					String title ="";//initializes the  title to ""
					System.out.print("Title: ");//prmots the user to enter a title
					title = scanner.nextLine();//gets the title 
					
					//for (String key: store.getTitleMap().keySet()){
					//	if (key.equalsIgnoreCase(title)){
						if (store.getTitleMap().keySet().contains(title)){
							int index = store.getTitleMap().get(title);//get the index using the .get(key) of the treeMap in AudioContentStore
							System.out.print(index + ". ");
							store.getContent(index).printInfo();//index-1 because the index on display starts at 1
						}
						else{
							System.out.println("No matches for "+title);
						}
					//}

				}
				//SEARCHA: If the audio content with this artist name is found in the store then print the indices and info of all audio content with this artist (use author string for audio books). 
				else if (action.equalsIgnoreCase("SEARCHA")){
					String artist ="";//initializes the  title to ""
					System.out.print("Artist: ");//prmots the user to enter a artist
					artist = scanner.nextLine();//gets the artist
					
					if (store.getArtistAuthorMap().keySet().contains(artist)){
						ArrayList<Integer> index = store.getArtistAuthorMap().get(artist);//get the index using the .get(key) of the treeMap in AudioContentStore
						for (int i=0; i<index.size(); i++){//loop through the index
							System.out.print(index.get(i) + ". ");
							store.getContent(index.get(i)).printInfo();//index-1 because the index on display starts at 1
							System.out.println();//Output an extra line for spacing
						}
					}
					else{
						System.out.println("No matches for "+artist);
					}
				}
				//SEARCHG: Searches the store for a song with the specified genre (“POP” “ROCK” etc). That is, the user types SEARCHG then is prompted to enter a genre string. 
				//If the song with this genre is found in the store then print the indices and info of all songs with this genre. 
				else if (action.equalsIgnoreCase("SEARCHG")){
					String genre ="";//initializes the  title to ""
					System.out.print("Genre [POP, ROCK, JAZZ, HIPOP, RAP, CLASSICAL]: ");//prmots the user to enter a genre
					genre= scanner.nextLine();//gets the genre
					
					if (store.getGenreMap().keySet().contains(genre)){//check if the genre type is valid
						ArrayList<Integer> index = store.getGenreMap().get(genre);//gets all indexes of this genre
						for (int i=0; i<index.size(); i++){//loop through all indexes to print all songs of this genre
							System.out.print(index.get(i) + ". ");
							store.getContent(index.get(i)).printInfo();//index-1 because the index on display starts at 1
							System.out.println();//Output an extra line for spacing
						}
					}
					else{
						System.out.println("No matches for "+genre);
					}
				}
				//Create a new download action that takes an artist string as parameter and downloads all audio content with this artist name 
				//(use author for audio books) from the store. 
				else if (action.equalsIgnoreCase("DOWNLOADA")) {
					String artist ="";//initializes the  title to ""
					System.out.print("Artist Name: ");//prmots the user to enter a artist
					artist = scanner.nextLine();//gets the artist
					

					if (store.getArtistAuthorMap().keySet().contains(artist)){//Search the keys of artistAuthorMap and see if it contains artist
						ArrayList<Integer> index = store.getArtistAuthorMap().get(artist);//get the index using the .get(key) of the treeMap in AudioContentStore
						for (int i=0; i<index.size(); i++){//loop through the index
							try{
								AudioContent content = store.getContent(index.get(i));//using the given index, make it into a AudioContent
								if (content == null){//If the index is invalid, then output "Content Not Found in Store"
									throw new IllegalArgumentException("Content Not Found in Store");
								}
								mylibrary.download(content);
							}
							//catch the exceptions that are thrown from library.java
							catch(alreadyDownloadedException e){
								System.out.println(e.getMessage());
							}
							catch(IllegalArgumentException e){
								System.out.println(e.getMessage());
							}
						}
					}
					else{
						System.out.println("No matches for " + artist);
					}
				}
				//Create a new download action that takes a genre string as parameter and downloads all songs in this genre from the store. Make use of the genre map. 
				else if (action.equalsIgnoreCase("DOWNLOADG")){
					String genre ="";//initializes the  title to ""
					System.out.print("Genre: ");//prmots the user to enter a title
					genre = scanner.nextLine();//gets the title of the playlist and store it in pltitle
					

					if (store.getGenreMap().keySet().contains(genre)){//Find all the keys to the returned "genreMap", and see if it contains the target "genre"
						ArrayList<Integer> index = store.getGenreMap().get(genre);//get the index using the .get(key) of the treeMap in AudioContentStore
						for (int i=0; i<index.size(); i++){//loop through the index
							try{
								AudioContent content = store.getContent(index.get(i));//using the given index, make it into a AudioContent
								if (content == null){//If the index is invalid, then output "Content Not Found in Store"
									throw new IllegalArgumentException("Content Not Found in Store");
								}
								mylibrary.download(content);//download the content
							}
							//catch the exceptions that are thrown from library.java
							catch(alreadyDownloadedException e){
								System.out.println(e.getMessage());
							}
							catch(IllegalArgumentException e){
								System.out.println(e.getMessage());
							}
						}
					}
					else{
						System.out.println("No matches for "+ genre);
					}
				}
				//The target string could appear anywhere in the audio content (in the title, artist, lyrics, chapter etc etc). For example, the target string could be “Ale” and 
				//the search would match all the songs by Alessia Cara. If the target string is “Ishmael” then the search would match the book Moby Dick.  
				else if (action.equalsIgnoreCase("SEARCHP")){
					String partialString ="";//initializes the  title to ""
					System.out.print("Target String: ");//prmots the user to enter a part of the string
					partialString= scanner.nextLine();//gets the partialString from the user
					
					boolean found = false;//Used to check if the partialString is found.
					ArrayList<AudioContent> contents = store.getContents();//transfers the contents from AudioContentStore to MyAudioUI
					for (int i=0; i<contents.size(); i++){//loop through all indexes to print all songs of this genre
						if (contents.get(i).getInfo().contains(partialString)){
							contents.get(i).printInfo();//only prints the info of the partial String, doesn't print lyrics or chapterTitles or chapters
							System.out.println();//print empty line 
							found=true;
						}
					}
					if (found==false){//if the partialstring is not found, e.g. not in the notepad, print no matches for partialString.
						System.out.println("No matches for "+partialString);}
					
					
				}
				System.out.print("\n>");//prints an extra line and a ">" sign. For organizing inputs. 
			}//end of try
			//Catch all exceptions thrown from library again 
			//Catch all input mismatch exceptions. If asking for an integer but given a string, this exception will be caught and will print "Please enter a valid type\n"
			catch(InputMismatchException e){
				System.out.println("Please enter a valid type.");
			}

		}//End of while loop
		
		
	}
}
