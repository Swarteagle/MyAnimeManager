package util.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.swing.SwingWorker;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import main.AnimeIndex;
import util.DriveUtil;

public class DriveFileFetcherTask extends SwingWorker
{

	private TreeMap<String, ArrayList<String>> map;
	public double albumNumber;
	public double count;

	@Override
	protected Object doInBackground() throws Exception
	{
		String date = AnimeIndex.appProp.getProperty("Last_Music_Check");
		if (date.equalsIgnoreCase("null"))
		{
			map = getMusicFolderChildren();
		}
		else
			updateMusicList(date);
		return null;
	}

	@Override
	protected void done()
	{
		if(map!=null)
			AnimeIndex.musicDialog.songsMap.putAll(map);
	}

	private TreeMap<String, ArrayList<String>> getMusicFolderChildren() throws IOException
	{
		TreeMap<String, ArrayList<String>> fileParentMap = new TreeMap<String, ArrayList<String>>();
		FileList request = DriveUtil.service.files().list().setQ("mimeType = 'application/vnd.google-apps.folder' and title != 'Musica'").execute();
		List<File> children = request.getItems();
		if (children == null || children.size() == 0)
			System.out.println("No files found.");
		else
		{
			count = 1;
			albumNumber = children.size();
			for (File child : children)
			{
				String childName = child.getTitle();
				System.out.println(childName);
				ArrayList<String> childList = new ArrayList<String>();
				ChildList resultSubFolder = DriveUtil.service.children().list(child.getId()).execute();
				List<ChildReference> childrenSubFolder = resultSubFolder.getItems();
				for (ChildReference childSubFolder : childrenSubFolder)
				{
					String childSubFolderName = DriveUtil.service.files().get(childSubFolder.getId()).execute().getTitle();
					childList.add(childSubFolderName.substring(0, childSubFolderName.length() - 4));
				}
				childList.sort(String.CASE_INSENSITIVE_ORDER);
				fileParentMap.put(childName, childList);
				count++;
				int progress = (int)(((count - 1)/albumNumber) * 100);
				setProgress(progress);
			}
		}
		
		return fileParentMap;
	}
	

	private void updateMusicList(String date) throws IOException
	{
		ArrayList<String> newFolderSong = new ArrayList<String>();
		//cartelle
		FileList requestFolder = DriveUtil.service.files().list().setQ("mimeType = 'application/vnd.google-apps.folder' and modifiedDate >= '" + date + "'").execute();
		List<File> newFolderList = requestFolder.getItems();
		if (newFolderList == null || newFolderList.size() == 0)
			System.out.println("No files found.");
		else
		{
			count = 1;
			albumNumber = newFolderList.size();
			for (File newFolder : newFolderList)
			{
				String albumName = newFolder.getTitle();
				ArrayList<String> list = new ArrayList<String>();
				ChildList resultSubFolder = DriveUtil.service.children().list(newFolder.getId()).execute();
				List<ChildReference> childrenSubFolder = resultSubFolder.getItems();
				for (ChildReference childSubFolder : childrenSubFolder)
				{
					String childSubFolderName = DriveUtil.service.files().get(childSubFolder.getId()).execute().getTitle();
					newFolderSong.add(childSubFolderName);
					list.add(childSubFolderName.substring(0, childSubFolderName.length() - 4));
				}
				list.sort(String.CASE_INSENSITIVE_ORDER);
				AnimeIndex.musicDialog.songsMap.put(albumName, list);
				count++;
				int progress = (int)(((count - 1)/albumNumber) * 100);
				setProgress(progress);
			}
		}
		//canzoni singole
		FileList request = DriveUtil.service.files().list().setQ("mimeType != 'application/vnd.google-apps.folder' and modifiedDate >= '" + date + "'").execute();
		List<File> newSongsList = request.getItems();
		if (newSongsList == null || newSongsList.size() == 0)
			System.out.println("No files found.");
		else
		{
			count = 1;
			albumNumber = newSongsList.size();
			for (File newSong : newSongsList)
			{
				String songName = newSong.getTitle();
				if (!newFolderSong.contains(songName))
				{
					String albumName = DriveUtil.getFirstParentName(newSong);
					if (AnimeIndex.musicDialog.songsMap.containsKey(albumName))
					{
					ArrayList<String> list = AnimeIndex.musicDialog.songsMap.get(albumName);
					list.add(songName);
					list.sort(String.CASE_INSENSITIVE_ORDER);
					AnimeIndex.musicDialog.songsMap.put(albumName, list);
					}
					else
					{
						ArrayList<String> list = new ArrayList<String>();
						list.add(songName);
						list.sort(String.CASE_INSENSITIVE_ORDER);
						AnimeIndex.musicDialog.songsMap.put(albumName, list);
					}
				}
				count++;
				int progress = (int)(((count - 1)/albumNumber) * 100);
				setProgress(progress);
			}
		}
		
	}

}
