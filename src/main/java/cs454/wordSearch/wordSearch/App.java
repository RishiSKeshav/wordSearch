package cs454.wordSearch.wordSearch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
	
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" + args[1]);
        String word="apple";
    	
    	
    	String indexPath = "C:\\Users\\RishiSuresh\\workspace\\webCrawler\\target\\data\\Crawler\\index.json";    	
    	
    	readIndexer(indexPath,word);
    	
    	
        //privateRank(word);
    	
    	print();
    	
        
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
    	System.out.println(word);
    }
    
    
}
