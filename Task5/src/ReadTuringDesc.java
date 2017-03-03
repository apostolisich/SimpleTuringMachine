import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTuringDesc {

	int startingState, numOfStates;
	Transition transitionMap = new Transition();
	
	public boolean readData(String fileName){
	
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line1 = bufferedReader.readLine();
			numOfStates = Integer.parseInt(line1);
			//Αποθηκεύει το σύνολο των καταστάσεων αλλά δεν χρησιμοποιείται
			
			String line2 = bufferedReader.readLine();
			startingState = Integer.parseInt(line2);
			
			String linesAfter3;
			while((linesAfter3=bufferedReader.readLine()) != null){
				int startState;
				Character inputChar, transitionChar, goalState;
				ArrayList<Character> arrayList = new ArrayList<Character>();
				char[] lineToCharArray = linesAfter3.toCharArray();
				
				startState = Integer.parseInt(String.valueOf(lineToCharArray[0]));
				inputChar = lineToCharArray[2];
				transitionChar = lineToCharArray[4];
				goalState = lineToCharArray[6];
				
				arrayList.add(0, transitionChar);
				arrayList.add(1, goalState);
				transitionMap.setTransition(startState, inputChar, arrayList);
			}
			
			bufferedReader.close();
			return true;
		}catch(FileNotFoundException e) {
			return false;
		}catch(IOException exception){
			return false;
		}
	}

	public Integer getStartingState() {
		return startingState;
	}

	public Transition getTransitionMap() {
		return transitionMap;
	}
	
	
}
