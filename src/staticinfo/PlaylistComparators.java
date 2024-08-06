package staticinfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import userUI.information.Musicinfo;

public class PlaylistComparators {
	private static HashMap<Integer, Comparator<Musicinfo>> CompareTypes = new HashMap<>();
	
	public static Comparator<Musicinfo> compareNames = new Comparator<Musicinfo>() {
		
		@Override
		public int compare(Musicinfo i, Musicinfo j) {
			return compareNames(i.getName(), j.getName());
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Names A->Z";
		}
	};
	public static Comparator<Musicinfo> compareNamesReverse = new Comparator<Musicinfo>() {
		
		@Override
		public int compare(Musicinfo i, Musicinfo j) {
			return compareNames(j.getName(), i.getName());
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Names Z->A";
		}
	};
	public static Comparator<Musicinfo> compareID = new Comparator<Musicinfo>() {
		
		@Override
		public int compare(Musicinfo i, Musicinfo j) {
			if(i.getId() > j.getId()) return 1;
			else return -1;
		}
		public String toString() {
			// TODO Auto-generated method stub
			return "ID";
		}
	};
	public static Comparator<Musicinfo> compareSinger = new Comparator<Musicinfo>() {
		
		@Override
		public int compare(Musicinfo i, Musicinfo j) {
			return compareNames(i.getSinger(), j.getSinger());
		}
		public String toString() {
			// TODO Auto-generated method stub
			return "Singer";
		}
	};
	private static int compareNames(String name1,String name2) {
		char[] name1array = name1.toCharArray();
		char[] name2array = name2.toCharArray();
		for (int k = 0; k < name1array.length; k++) {
			if(name1array[k] > name2array[k]) {
				return 1;
			}
			else if(name1array[k] < name2array[k]) {
				return -1;
			}
		}
		return -1;
	}
	public static void  setCompareTypes(){
		if(CompareTypes.size() != 0) return;
		CompareTypes.put(0, compareID);
		CompareTypes.put(1, compareNames);
		CompareTypes.put(2, compareNamesReverse);
		CompareTypes.put(3, compareSinger);
	}
	public static Comparator<Musicinfo> getCompareType(int ID){
		return CompareTypes.get(ID);
	}
	public static int getSize() {
		return CompareTypes.size();
	}
}
