//Katherine Luo Liu
//Student Id: 501165983
/*
 * A Podcast is a type of AudioContent. A Podcast has extra fields such as host and season
 */
import java.util.ArrayList; //Import the ArrayList so that we can use it in this class
public class Podcast extends AudioContent //The class name is Podcast and it extends the AudioContent because Podcast IS A AudioContent
{
	public static final String TYPENAME =	"PODCAST"; //the TYPENAME for the Podcast Class should be PODCAST 
    //make private variables so they are not accessible in other classes
    private String hostNames; //stores the names of the hosts
    private ArrayList<Season> seasons; //Stores all the information in the Season class
    
    public Podcast(String title, int year, String id, String type, String audioFile, int length, String hostNames, ArrayList<Season> seasons){
        super(title, year, id, type, audioFile, length);//Put parameters into super so the variables can be initialized in the super method
        this.hostNames = hostNames;//initialize the private hostNames variable in the this class (Podcast class)
        this.seasons = seasons;//initialize the private hostNames variable in the this class (Podcast class)
    }

    public String getType()
	{
        return TYPENAME;//Returns the constant TYPENAME varibale value. In this class, it is always PODCAST. 
	}

    public void printInfo()
	{
		super.printInfo();//Use the printInfo() method in AudioContent to print the original info like title, id, year, type, length
		System.out.println("Host: "+this.hostNames + "\nSeasons: "+this.seasons.size());//Prints out extra characteristics of the podcast class
	}

    /*This method gets all the Episode titles */
    public void getAllEpisodes(int season_index){
        //Loop through all the episode titles by using .get(season_index) to select the correct season, .getEpisodeTitles() in the Season class returns all the titles
        //Then use .size() to get the number of Titles for looping purposes
        for (int i=0; i<this.seasons.get(season_index).getEpisodeTitles().size(); i++){
            //Prints out each episode title in the format according to the video/notepad. 
            //takes the private season varaible in this class, use .get(season_index) to select the season, and use .getEpisodeTitles().get(i) to get the title at the index "i"
            System.out.println("Episode "+ (i+1) +". " + this.seasons.get(season_index).getEpisodeTitles().get(i) + "\n");
        }
        
    }

    /*This method first prints out all the information about Podcast, then it selects the season and the episode, then prints it out */
    public void selectEpisodes(int season_index, int episode_index){
        this.printInfo();//Calls the printInfo() in this class and prints information about this podcast. Ex. title, id, year.... hostNames, Seasons
        //Prints out the name of the episode -> .get(season_index) selects the correct season and .getEpisodeTitles() returns all the episodes of that season as an arrayList
        //Then .get(episode_index) gets the selected episode's title. This is followed by a period and an extra line (according to the format of notepad/video), then...
        //Prints out the information of the episode -> .get.get(season_index) selects the correct season and .getEpisodeFiles() returns all the context of the episode as a String arrayList
        //Then .get(episode_index) selects the correct episode content and prints it out
        System.out.println("\n" + this.seasons.get(season_index).getEpisodeTitles().get(episode_index) +". \n" + this.seasons.get(season_index).getEpisodeFiles().get(episode_index));
    }


}