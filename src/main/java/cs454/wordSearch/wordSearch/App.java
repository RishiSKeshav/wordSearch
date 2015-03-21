package cs454.wordSearch.wordSearch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 * Hello world!
 *
 */
public class App 
{
	public static List<Pagerank> pages = new ArrayList<Pagerank>();
	public static List<String> url = new ArrayList<String>();
	public static Map<String,Double> urlValues = new HashMap<String, Double>();
	public static List<Pagerank> tempList = new ArrayList<Pagerank>();
	
	
	public static List<Pagerank> tempFinalList = new ArrayList<Pagerank>();
	public static List<Pagerank> finalList = new ArrayList<Pagerank>();
	
	
	
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" + args[1]);
        String word="mac";
    	
    	
    	String indexPath = "C:\\Users\\RishiSuresh\\workspace\\webCrawler\\target\\data\\Crawler\\index.json";    	
    	String rankPath = "C:\\Users\\RishiSuresh\\workspace\\webCrawler\\target\\data\\Crawler\\rank.json";  
    	
    	readIndexer(indexPath,word);
    	
    	rankReader(rankPath);
        privateRank(word);
    	
        sort();
        
    	print();
    	
        
    }
    
    private static void sort() 
    {
    	tempList = pages;  	
    	
    	sortBasedOnTfid(tempList);
    	
    	sortBasedOnWordCount(tempList);
    	
    	sortBasedOnPrivateValue(tempList);
    	
    	sortBasedOnPageRankValue(tempList);
    	
    	System.out.println(finalList.size());
    	for(Pagerank p: finalList)
		{

    		System.out.println("url "+p.getUrl());
    		System.out.println("desc "+p.getDescription());
    		System.out.println("tfid "+p.getTfid());
    		System.out.println("title"+p.getTitle());
    		System.out.println("localpath "+p.getLocalPath());
    		System.out.println("count "+p.getCount());
    		System.out.println("author "+p.getAuthor());
    		System.out.println("pageAnalysis "+ p.getPageAnalysisValue());
    		System.out.println("private "+ p.getPrivateRankValue());
    		System.out.println("---------------------------------------------------");
		}
    	
	}
    


	private static void sortBasedOnPageRankValue(List<Pagerank> tempList2) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Pagerank p1 = tempList2.get(j);
				Pagerank p2 = tempList2.get(j+1);
				
				if(p1.getPageAnalysisValue()<p2.getPageAnalysisValue())
				{
					Pagerank temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		/*System.out.println(tempList2.size());
    	for(Pagerank p: tempList2)
		{

    		System.out.println("url "+p.getUrl());
    		System.out.println("desc "+p.getDescription());
    		System.out.println("tfid "+p.getTfid());
    		System.out.println("title"+p.getTitle());
    		System.out.println("localpath "+p.getLocalPath());
    		System.out.println("count "+p.getCount());
    		System.out.println("author "+p.getAuthor());
    		System.out.println("pageAnalysis "+ p.getPageAnalysisValue());
    		System.out.println("private "+ p.getPrivateRankValue());
    		System.out.println("---------------------------------------------------");
		}*/
		
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				finalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				finalList.add(tempList2.get(i));
			}
		}
		
	}

	private static void sortBasedOnPrivateValue(List<Pagerank> tempList2)
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Pagerank p1 = tempList2.get(j);
				Pagerank p2 = tempList2.get(j+1);
				
				if(p1.getPrivateRankValue()<p2.getPrivateRankValue())
				{
					Pagerank temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}		
		
	}

	private static void sortBasedOnWordCount(List<Pagerank> tempList2) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Pagerank p1 = tempList2.get(j);
				Pagerank p2 = tempList2.get(j+1);
				
				if(p1.getCount()<p2.getCount())
				{
					Pagerank temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}		
		
		
	}

	private static void sortBasedOnTfid(List<Pagerank> tempList2) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Pagerank p1 = tempList2.get(j);
				Pagerank p2 = tempList2.get(j+1);
				
				if(p1.getTfid()<p2.getTfid())
				{
					Pagerank temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		if(tempList!=null)
			
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}		
	}

	private static void rankReader(String rankPath) 
    {
    		JSONParser parser = new JSONParser();
    		Object object;    		

    		try
    		{
    			object = parser.parse(new FileReader(rankPath));
    			JSONArray jsonArray = (JSONArray) object;   			
    			
    			System.out.println(jsonArray.size());
    			
    			
    			for(Object o : jsonArray)
    			{    				
    				JSONObject jsonObj = (JSONObject) o;
    				String url = jsonObj.get("url").toString();
    				Double value =Double.parseDouble(jsonObj.get("value").toString());
    				
    				for(Pagerank p: pages)
    				{
    					if(p.getUrl().equals(url))
    					{
    						p.setPageAnalysisValue(value);
    						break;
    					}
    				}
    							
    			}
    			
    			/*for(Object o : jsonArray)
    			{
    				
    				JSONObject jsonObj = (JSONObject) o;
    				
    				Set<String> keys = jsonObj.keySet();
    				
    				for(String s: keys)
    				{
    					url.add(s);
    				} 				
    			}
    			
    			for(Object o : jsonArray)
    			{
    				
    				JSONObject jsonObj = (JSONObject) o;
    				
    				Double value = jsonObj.get(key)		
    			}
    			
    			
    			for(Pagerank p: pages)
    			{
    				double pageAnalysis;
    				if(jsonArray.contains(p.getUrl()))
    				{	
    					int index = jsonArray.indexOf(p.getUrl());
    					pageAnalysis =Double.parseDouble(jsonArray.get(index).toString());
    					p.setPageAnalysisValue(pageAnalysis);
    				}
    			}*/
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error occured");
    		}
		
	}

	private static void print()
    {
    	for(Pagerank p: pages)
    	{
    		System.out.println("url "+p.getUrl());
    		System.out.println("desc "+p.getDescription());
    		System.out.println("tfid "+p.getTfid());
    		System.out.println("title"+p.getTitle());
    		System.out.println("localpath "+p.getLocalPath());
    		System.out.println("count "+p.getCount());
    		System.out.println("author "+p.getAuthor());
    		System.out.println("pageAnalysis "+ p.getPageAnalysisValue());
    		System.out.println("private "+ p.getPrivateRankValue());
    		System.out.println("---------------------------------------------------");
    	}
    }
    
    private static void readIndexer(String indexPath,String word)
    {
    	JSONParser parser = new JSONParser();
		Object object;	
		JSONArray innerJsonArray = new JSONArray();

		try
		{
			object = parser.parse(new FileReader(indexPath));
			JSONArray jsonArray = (JSONArray) object;

			System.out.println(jsonArray.size());
			for (Object o : jsonArray)
			{
				
				
				JSONObject jsonObject = (JSONObject) o;
				innerJsonArray = new JSONArray();
				innerJsonArray= (JSONArray)jsonObject.get(word);				
			}
			
			for(Object innerObject:innerJsonArray)
			{
				Pagerank page = new Pagerank();
				
				JSONObject obj = (JSONObject) innerObject;				
				int count =Integer.parseInt(obj.get("Count").toString());
				String url = obj.get("url").toString();
				double tfid =Double.parseDouble(obj.get("tfid").toString());
				String fileName = obj.get("fileName").toString();
				String title = obj.get("author").toString();
				String author = obj.get("description").toString();
				String description = obj.get("fileName").toString();
				
				page.setCount(count);
				page.setLocalPath(fileName);
				page.setUrl(url);
				page.setTfid(tfid);
				page.setTitle(title);
				page.setAuthor(author);
				page.setDescription(description);
				
				pages.add(page);
			}
			
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			
			e.printStackTrace();
		}
    	
	}

	/*private static void readExtractor(String extractorPath) {
		
		JSONParser parser = new JSONParser();
		Object object;	

		try
		{
			object = parser.parse(new FileReader(extractorPath));
			JSONArray jsonArray = (JSONArray) object;

			System.out.println(jsonArray.size());			
			
			for (Object o : jsonArray)
			{
				
				JSONObject jsonObject = (JSONObject) o;
				String localpath = jsonObject.get("localpath").toString();
				String url =  jsonObject.get("url").toString();
				String metadata = jsonObject.get("metadata").toString();
				
				JSONObject jsonObject1 =(JSONObject) jsonObject.get("metadata");
				JSONObject jsonObject2 =(JSONObject) jsonObject1.get("metadata");
				
				Pagerank page = new Pagerank();
				
				for(Pagerank p: pages)
				{
					if(p.getLocalPath().equals(localpath))
					{
						page =p;
						break;
					}						
				}		
				
				Set<String> keys = jsonObject2.keySet();
				
				if(keys.contains("Description"))
				{
					String innerMetadata = jsonObject2.get("Description").toString() ;
					page.setDescription(innerMetadata);
					
				}
				
				if(keys.contains("description"))
				{
					String innerMetadata = jsonObject2.get("description").toString() ;
					page.setDescription(innerMetadata);
					
				}
				
				if(keys.contains("Author"))
				{
					String innerMetadata = jsonObject2.get("Author").toString() ;
					page.setDescription(innerMetadata);					
				}
				
				if(keys.contains("title"))
				{
					String innerMetadata = jsonObject2.get("title").toString() ;
					page.setDescription(innerMetadata);					
				}
			}
		}

		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			
			e.printStackTrace();
		}
    	
		
	}*/

	public static void privateRank(String word)
    {
		
		// default private rank value
    	for(Pagerank p: pages)
    	{
    		p.setPrivateRankValue(1);
    	}
    	
    	for(Pagerank p: pages)
    	{
    		if(p.getTitle().toLowerCase().contains(word.toLowerCase()))
    			p.setPrivateRankValue(p.getPrivateRankValue()+1);
    		
    		if(p.getDescription().toLowerCase().contains(word.toLowerCase()))
    			p.setPrivateRankValue(p.getPrivateRankValue()+1);
    		
    		if(p.getAuthor().toLowerCase().contains(word.toLowerCase()))
    			p.setPrivateRankValue(p.getPrivateRankValue()+1); 		
    		
    	}
    			
    	
    }
    
    
}
