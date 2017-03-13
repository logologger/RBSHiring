import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
 * Max. Marks 100.00
RBS Stock Indexing

[Problem Statement]

Develop a pseudo Full Stack Web Application for Stock Indexing.

[Ideal Behaviour]

On Back End :

— Write a backend in Java which exports APIs with all stock related data.

— Get all Companies related data from the provided xlsx file and store it in the database. (You are free to use any database in which you are comfortable in. But do submit the db dump file along with everything. )

— You have to fetch real time stocks data from the third party API provided using tickers which you have stored in the database.

— Cache all the frequently viewed stocks data in the system cache, and render it. But do make sure for their current prices you make a third party API call. For example, if you are only interested in Apple and Google’s stock then store their static data in cache, but do make sure for their real time stock prices you fetch real time data from the provided third party API.

— We expect you to be wise enough to decide when to use cache and when not to.

— Use Design patterns for your solution (like MVC, MVP etc). And we also expect you to implement Java Design Patterns like Structural Design Pattern, Behavioural Design Pattern etc.

— All the real time individual stock price data also has to be stored in db with timestamp to calculate progression/regression.

Progression: Demonstrating percentage increase in stock prices over time.

Regression: Demonstrating percentage decrease in stock prices over time.

On Front End:

The UI can be very minimal which displays all the APIs data.

[Minimum Requirement]

— Write a backend in java which exposes some APIs to render data. And makes an API call from backend to get their real time prices.

API details provided below is a third party API, which you have to use to get real time stock data.

Stock API Details

This is a REST based API. Here is the basic syntax:

http://finance.google.com/finance/infoclient=ig&q=NASDAQ%3A[STOCK TICKERS]

Document on how to call this API is provided in the guide below.

On Front End:

— Display few stock details by default on first page load.

— Provide functionality to call stock details by name or ticker(symbol).

— Make an API call and demonstrate details of stocks.

On Back end:

— Store all the data provided in the Xlsx file in your database.

— Render all the requested data over network via REST call.

— Zip all your source code, executables(war file), deployment instruction, db dump file, screenshots and upload them.

[Extra Work]

Along with everything from the above level :

— Implement functionality to index top performing stocks based on the provided data on the UI.

— We expect you to perform Junit tests and submit reports.

— Provide beautiful line graphs demonstrating stocks individual performance and progression/regression.

— Use your imagination and add features which would make things easier for end users.

— Zip all your source code, executables(war file), deployment instruction, db dump file, screenshots and upload them.

[Guide]

Stocks API: http://finance.google.com/finance/info?client=ig&q=NASDAQ%3A

How to call this API: http://www.jarloo.com/real-time-google-stock-api/

Stocks info Xlsx files: http://hck.re/oUVSlU

[Ideal Stack]

Java, Spring, Junit.

— Include Content Headers in your backend :

HTTP Header

    access-control-allow-headers:Origin, X-Requested-With, Content-Type, Accept

    access-control-allow-methods:GET, POST, PUT

    access-control-allow-origin: *

    server: cloudflare-nginx


 * 
 * 
 * 
 */



public class ExcelReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBStore db=new DBStore();
		
		
		
		try {
			FileInputStream fs=new FileInputStream(new File("stocksf081a85.xlsx"));
			
			XSSFWorkbook wb=new XSSFWorkbook(fs);
			
			XSSFSheet sheet=wb.getSheetAt(0);
			 
			FormulaEvaluator forlulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
			
			String table_name="create table if not exists table_xcel(";
			String insert_query="";
			
			for(Row row:sheet){
				insert_query="insert into table_xcel values(";
				for(Cell cell:row){
					
					switch(forlulaEvaluator.evaluateInCell(cell).getCellType()){
					
					case Cell.CELL_TYPE_NUMERIC:
						
						if(row.getRowNum()==0)
							table_name+=cell.getNumericCellValue()+"\tvarchar(200),";
						else
							insert_query+="\'"+cell.getNumericCellValue()+"\',";
							
						break;
					case Cell.CELL_TYPE_STRING:
						
						if(row.getRowNum()==0)
							table_name+=cell.getStringCellValue()+"\tvarchar(200),";
						else
							insert_query+="\'"+cell.getStringCellValue()+"\',";
						break;
						
					
					
					}
					
					
					
					
					
					
					
				}
				
				
				
				
				
			
			
				if(row.getRowNum()==0){
					table_name=table_name.substring(0, table_name.length()-1);
					table_name=table_name+")";
					db.ExecuteQuery(table_name);
				}
				else{
					insert_query=insert_query.substring(0, insert_query.length()-1);
					insert_query=insert_query+")";
					db.ExecuteQuery(insert_query);
				}
				
			}
			
			
			
			
			
			
			 
			
			
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
