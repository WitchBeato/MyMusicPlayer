package backend;

public class StringEditor {
	//it found last iteratives of letter according to qualitive
	private static int[] lastCharLocation(String text, char letter, int qualitive) {
		int[] location = new int[2];
		int counter = 0;
		for (int i = text.length()-1; i > 0; i--) {
			char c = text.charAt(i);
			if(c == letter) {
				if(counter == 0) location[0] = i;
				counter++;
				if(counter == qualitive && counter != 1) {
					location[1] = i;
					break;
				}
			}
		}
		return location;	
	}
	public static String giveStringforIterative(String text, char letter, int qualitive) {
		int[] location = lastCharLocation(text, letter, qualitive);
		String newText = (qualitive != 1) ?
				text.substring(location[1], location[0]) : 
				text.substring(location[0], text.length()-1);
		return newText;
		
	}
	//give me reverse of the string
	public static String reverseString(String input){
        byte[] strAsByteArray = input.getBytes();

        byte[] result = new byte[strAsByteArray.length];

        for (int i = 0; i < strAsByteArray.length; i++)
            result[i] = strAsByteArray[strAsByteArray.length - i - 1];

       return new String(result);
    }	
}
