package userUI.information;

public class PlayerMessages {
	public static MessageData directoryNoFile = new MessageData(
			"There is no file in Directory "
			, "there aren't any file with supporten music extensions(.mp3) "
					+ "etc etc... in this direction");
	public static MessageData playlistNoFile = new MessageData(
			"There is no file in Playlist "
			, "there isn't any music added to playlist. You can start with clicking (+) Button ");
	public static MessageData noChoice = new MessageData(
			"playlist or directory hasn't choiced "
			, "you didn't choice any directory nor playlist, if you didn't create any "
					+ "of them you can start by clicking (+) button on playlist or directory");
}
