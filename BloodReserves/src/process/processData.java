package process;

import java.util.ArrayList;
import enums.BloodType;

import input.DataLoader;

/*
 *Class containing methods, which process full HTML codes to information about blood reserves
 */
public class processData {
	
	//Step 1 - extract actualisation date from full text
	private static String extractActualisationDate(String fullText) {
		int startIndex = fullText.indexOf("Aktualizacja stanu");
		return fullText.substring(startIndex+20, startIndex + 30);
	}
	
	//Step 2 - extract actualisation hour from full text
	private static String extractActualisationHour(String fullText) {
		int startIndex = fullText.indexOf("Aktualizacja stanu");
		return fullText.substring(startIndex+36, startIndex + 41);
	}

	//Step 3 - extract table with actual data from full text
	private static String extractTable(String fullText) {
		int beginIndex = fullText.indexOf("<table>");
		int endIndex = fullText.indexOf("</table>");
		
		return fullText.substring(beginIndex, endIndex);
	}
	
	//Step 4 - get labels with city names
	private static ArrayList<String> extractCityNames (String table){
		
		//Extract header from table
		String substringer = table.substring(table.indexOf("<thead>"), table.indexOf("</thead>"));
		
		//Remove first <span> tag with "Grupa krwi"
		substringer = substringer.substring(substringer.indexOf("</span>")+7);	
		
		//This loop extracts cities, until there's none
		ArrayList <String> ret = new ArrayList<String>();
		while(true) {
			int beginIndex = substringer.indexOf("<span>")+6;
			int endIndex = substringer.indexOf("</span>");
			
			//End condition for loop
			if(endIndex==-1) {
				break;
			}
			
			//Add city name to ArrayList
			ret.add(substringer.substring(beginIndex,endIndex));
			
			//Remove added city name
			substringer=substringer.substring(endIndex+7);
		}
				
		return ret;
	}
	
	//Private inner class, containing row with values and blood type name
	private class TableRow {
		Enum bloodType;
		ArrayList<Integer> values;
		
		
		public TableRow(String bloodType, ArrayList<Integer> values) {
			this.bloodType=bloodTypeGenerator(bloodType);
			
			this.values = values;
		}
		
		//Gives BloodType enum using string
		private BloodType bloodTypeGenerator (String bloodType) {
			switch (bloodType) {
			case "0 Rh-" :  return BloodType.ZERO_RH_N; 
			case "0 Rh+" :  return BloodType.ZERO_RH_P; 
			case "A Rh-" :  return BloodType.A_RH_N; 
			case "A Rh+" :  return BloodType.A_RH_P; 
			case "B Rh-" :  return BloodType.B_RH_N; 
			case "B Rh+" :  return BloodType.B_RH_P; 
			case "AB Rh-" :  return BloodType.AB_RH_N; 
			case "AB Rh+" :  return BloodType.AB_RH_P; 
			default: throw new IllegalArgumentException("There's no such blood type");
			}
		}
		
		//Generate table row object from raw table row
		public TableRow (String tableRow) {
			
			String row = tableRow;
			
			//Get string containing blood type ie. "A Rh+"
			this.bloodType=bloodTypeGenerator(tableRow.substring(tableRow.indexOf("<h3>")+4, tableRow.indexOf("</h3>")));
			
			this.values = new ArrayList<Integer>();
			
			while(true) {
				int beginIndex = row.indexOf("src=\"")+5;
				int endIndex=row.indexOf("\" alt");
				
				//End condition for loop
				if(endIndex==-1) {
					break;
				}
				
				String value = row.substring(beginIndex, endIndex);
				
				//Cut data
				row = row.substring(endIndex);
				
				//Give actual value
				switch (value) {
					case "img/krew1.png": values.add(Integer.valueOf(4)); break;
					case "img/krew2.png": values.add(Integer.valueOf(3)); break;
					case "img/krew3.png": values.add(Integer.valueOf(2)); break;
					case "img/krew4.png": values.add(Integer.valueOf(1)); break;
				}
			}
			
		}
	}
	
	//Step 5 - get values for every blood type
	private static ArrayList<TableRow> extractBloodLevels (String table, int numberOfCities){
		
		
		ArrayList<TableRow> ret = new ArrayList<TableRow>();
		
		
		//Extract all data rows from table
		String substringer = table.substring(table.indexOf("<tbody>"), table.indexOf("<t/body>"));
		
		
		/*
		 * KACZKA KACZKA KACZKA
		 */
		
		//This loop extracts rows with data, until there's none
				while(true) {
					int rowEnd = substringer.indexOf("</tr>");
					
					//End condition for loop
					if(rowEnd==-1) {
						break;
					}
					
					String row = substringer.substring(substringer.indexOf("<tr>")+4, substringer.indexOf("</tr>")); 
					
					//Add to processed row arraylist new row. Constructor process data, it requires only raw data row :)
					ret.add(new TableRow(row));
					
					
					//Cut data.
					substringer=substringer.substring(rowEnd);
					
					
					
				
				}
		
				/*
				 * KACZKA KACZKA KACZKA
				 */
		
		return null;
	}
	
	public static void main (String[] args) {
		String text = DataLoader.loadData("2019_10_16"); 
		
		System.out.println(processData.extractActualisationDate(text));
		System.out.println(processData.extractActualisationHour(text));
		
		String table = processData.extractTable(text);
		System.out.println(table);
		
		ArrayList<String> labels = processData.extractCityNames(table);
		
		
	}
}
