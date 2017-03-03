import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transition {

	private Map<Integer, Map<Character, ArrayList<Character>>> transitionMap = new HashMap<>();
	
	public void setTransition(Integer startState, Character inputCharacter, ArrayList<Character> arrayList){
		transitionMap.putIfAbsent(startState, new HashMap<>());
		transitionMap.get(startState).put(inputCharacter, arrayList);
	}
	
	public ArrayList<Character> getGoalState(Integer startState, Character inputCharacter){
		if(!transitionMap.containsKey(startState)){
			return null;
		}
		
		return transitionMap.get(startState).get(inputCharacter);
	}
}
