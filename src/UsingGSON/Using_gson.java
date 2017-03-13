package UsingGSON;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Using_gson {

	public static void main(String args[]){
		List<Task> list=new ArrayList<Task>();
		for(int i=0;i<20;i++){
			
			list.add(new Task(i,"Test1","Test2",Task.Status
					.ASSIGNED,10));
		}
		
		Gson gson=new Gson();
		Type type=new TypeToken<List<Task>>() {}.getType();//I dont know what is the use of this
		
		String json=gson.toJson(list,type);
		
		System.out.println(json);
		
		//ArrayList got converted to json
		
		//Convert json to ArrayList
		
		List<Task> fromJson=gson.fromJson(json, type);
		
		for(Task task:fromJson){
			System.out.println(task);//WE have the toString method 
		}
	}
}
