//Katherine Luo Liu
//Student Id: 501165983
import java.util.ArrayList;
public class Season{
    public ArrayList<String> episodeTitles;//private String arraylist to store episode titles
    public ArrayList<String> episodeFiles;//private String arraylist to store episode files
    public ArrayList<Integer> episodeLengths;///private Integer arraylist to store size/length of the episode

    public Season(){//The constructor of Season
        this.episodeTitles = new ArrayList<String>();//initialized the arraylist called episodeTitles
        this.episodeFiles = new ArrayList<String>();//initialized the arraylist called episodeFiles
        this.episodeLengths = new ArrayList<Integer>();//initialized the arraylist called episodeLengths
    }

    public void episodeTitles(String title){//this method is called in AudioContentStore 
        this.episodeTitles.add(title);//the title of a podcast is added to the episodeTitles arraylist
    }

    public void episodeFiles(String file){//this method is called in AudioContentStore 
        this.episodeFiles.add(file);//eventually all episodes are added to the episodeFiles arraylist
    }

    public void episodeLengths(int length){//this method is called in AudioContentStore 
        this.episodeLengths.add(length);//episode lengths are added to the episodeLengths arraylist
    }

    public ArrayList<String> getEpisodeTitles(){//returns the episodeTitles arraylist
        return this.episodeTitles;
    }
    
    public ArrayList<String> getEpisodeFiles(){//returns the episodeFiles arraylist
        return this.episodeFiles;
    }


}