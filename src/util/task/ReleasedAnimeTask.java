package util.task;

import java.util.GregorianCalendar;
import javax.swing.SwingWorker;
import main.AnimeIndex;
import util.AnimeData;
import util.MAMUtil;
import util.window.ReleaseNotifierDialog;

public class ReleasedAnimeTask extends SwingWorker
{

	public static boolean enableFilm;
	public static boolean enableOav;
	private ReleaseNotifierDialog dial;

	@Override
	protected Object doInBackground() throws Exception
	{
		ReleaseNotifierDialog.ovaReleased.clear();
		ReleaseNotifierDialog.filmReleased.clear();
		String oggi = MAMUtil.today();
		GregorianCalendar today = MAMUtil.getDate(oggi);
		GregorianCalendar lastControlDate = null;
		if (!AnimeIndex.appProp.getProperty("Date_Release").equalsIgnoreCase("none"))
			lastControlDate = MAMUtil.getDate(AnimeIndex.appProp.getProperty("Date_Release"));
		GregorianCalendar animeDate = null;
		Object[] ovaArray = AnimeIndex.ovaModel.toArray();
		for (int i = 0; i < ovaArray.length; i++)
		{
			String name = (String) ovaArray[i];
			AnimeData data = AnimeIndex.ovaMap.get(name);
			String releaseDate = data.getFinishDate();
			String secondaryReleaseDate = data.getReleaseDate();

			if (releaseDate.contains("?") || today.before(MAMUtil.getDate(releaseDate)))
			{	
				if(!secondaryReleaseDate.contains("?"))
					animeDate = MAMUtil.getDate(secondaryReleaseDate);
			}
			else
				animeDate = MAMUtil.getDate(releaseDate);
			if(animeDate!=null)
			{
				if (AnimeIndex.openReleaseDialog == true)
					if (AnimeIndex.exitDateMap.containsKey(name))
						if (MAMUtil.getDate(AnimeIndex.exitDateMap.get(name)).equals(today))
							if (animeDate.before(today) || animeDate.equals(today))
							{
								ReleaseNotifierDialog.ovaReleased.addElement(name);
								if(!data.getDay().equalsIgnoreCase("Rilasciato"))
								{
									AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
									AnimeIndex.ovaMap.put(name, newData);
								}
							}
								
				if (lastControlDate != null)
				{
					if (animeDate.after(lastControlDate))
					{
						if (AnimeIndex.exitDateMap.containsKey(name))
						{
							if (MAMUtil.getDate(AnimeIndex.exitDateMap.get(name)).before(animeDate))
							{
								if (animeDate.before(today) || animeDate.equals(today))
								{
									ReleaseNotifierDialog.ovaReleased.addElement(name);
									AnimeIndex.exitDateMap.put(name, oggi);
									if(!data.getDay().equalsIgnoreCase("Rilasciato"))
									{
										AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
										AnimeIndex.ovaMap.put(name, newData);
									}
								}
							}
						}
						else if (animeDate.before(today) || animeDate.equals(today))
						{
							ReleaseNotifierDialog.ovaReleased.addElement(name);
							AnimeIndex.exitDateMap.put(name, oggi);
							if(!data.getDay().equalsIgnoreCase("Rilasciato"))
							{
								AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
								AnimeIndex.ovaMap.put(name, newData);
							}
						}
					}
				}
				else if (AnimeIndex.exitDateMap.containsKey(name))
				{
					if (MAMUtil.getDate(AnimeIndex.exitDateMap.get(name)).before(animeDate))
					{	
						if (animeDate.before(today) || animeDate.equals(today))
						{
							ReleaseNotifierDialog.ovaReleased.addElement(name);
							AnimeIndex.exitDateMap.put(name, oggi);
							if(!data.getDay().equalsIgnoreCase("Rilasciato"))
							{
								AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
								AnimeIndex.ovaMap.put(name, newData);
							}
						}
					}
				}
				else if (animeDate.before(today) || animeDate.equals(today))
				{
					ReleaseNotifierDialog.ovaReleased.addElement(name);
					AnimeIndex.exitDateMap.put(name, oggi);
					if(!data.getDay().equalsIgnoreCase("Rilasciato"))
					{
						AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
						AnimeIndex.ovaMap.put(name, newData);
					}
				}
			}
		}
		
		animeDate = null;
		Object[] filmArray = AnimeIndex.filmModel.toArray();
		for (int i = 0; i < filmArray.length; i++)
		{
			String name = (String) filmArray[i];
			AnimeData data = AnimeIndex.filmMap.get(name);
			String releaseDate = data.getFinishDate();
			String secondaryReleaseDate = data.getReleaseDate();

			if (releaseDate.contains("?") || today.before(MAMUtil.getDate(releaseDate)))
			{
				if(!secondaryReleaseDate.contains("?"))
					animeDate = MAMUtil.getDate(secondaryReleaseDate);
			}
			else
				animeDate = MAMUtil.getDate(releaseDate);
			if(animeDate!=null)
			{
				if (AnimeIndex.openReleaseDialog == true)
					if (AnimeIndex.exitDateMap.containsKey(name))
						if (MAMUtil.getDate(AnimeIndex.exitDateMap.get(name)).equals(today))
							if (animeDate.before(today) || animeDate.equals(today))
							{
								ReleaseNotifierDialog.filmReleased.addElement(name);
								if(!data.getDay().equalsIgnoreCase("Rilasciato"))
								{
									AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
									AnimeIndex.filmMap.put(name, newData);
								}
							}
								
				if (lastControlDate != null)
				{
					if (animeDate.after(lastControlDate))
					{	
						if (AnimeIndex.exitDateMap.containsKey(name))
						{
							if (MAMUtil.getDate(AnimeIndex.exitDateMap.get(name)).before(animeDate))
							{	
								if (animeDate.before(today) || animeDate.equals(today))
								{
									ReleaseNotifierDialog.filmReleased.addElement(name);
									AnimeIndex.exitDateMap.put(name, oggi);
									if(!data.getDay().equalsIgnoreCase("Rilasciato"))
									{
										AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
										AnimeIndex.filmMap.put(name, newData);
									}
								}
							}
						}
						else if (animeDate.before(today) || animeDate.equals(today))
						{
							ReleaseNotifierDialog.filmReleased.addElement(name);
							AnimeIndex.exitDateMap.put(name, oggi);
							if(!data.getDay().equalsIgnoreCase("Rilasciato"))
							{
								AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
								AnimeIndex.filmMap.put(name, newData);
							}
						}
					}
				}
				else if (AnimeIndex.exitDateMap.containsKey(name))
				{
					if (MAMUtil.getDate(AnimeIndex.exitDateMap.get(name)).before(animeDate))
					{	
						if (animeDate.before(today) || animeDate.equals(today))
						{
							ReleaseNotifierDialog.filmReleased.addElement(name);
							AnimeIndex.exitDateMap.put(name, oggi);
							if(!data.getDay().equalsIgnoreCase("Rilasciato"))
							{
								AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
								AnimeIndex.filmMap.put(name, newData);
							}
						}
					}
				}
				else if (animeDate.before(today) || animeDate.equals(today))
				{
					ReleaseNotifierDialog.filmReleased.addElement(name);
					AnimeIndex.exitDateMap.put(name, oggi);
					if(!data.getDay().equalsIgnoreCase("Rilasciato"))
					{
						AnimeData newData = new AnimeData(data.getCurrentEpisode(), data.getTotalEpisode(), data.getFansub(), data.getNote(), data.getImageName(), "Rilasciato", data.getId(), data.getLinkName(), data.getLink(), data.getAnimeType(), data.getReleaseDate(), data.getFinishDate(), data.getDurationEp(), data.getBd());
						AnimeIndex.filmMap.put(name, newData);
					}
				}
			}
		}
		if (ReleaseNotifierDialog.ovaReleased.isEmpty())
		{
			ReleaseNotifierDialog.ovaReleased.addElement("Nessun Anime Rilasciato");
			enableOav = false;
		}
		else
			enableOav = true;
		if (ReleaseNotifierDialog.filmReleased.isEmpty())
		{
			ReleaseNotifierDialog.filmReleased.addElement("Nessun Anime Rilasciato");
			enableFilm = false;
		}
		else
			enableFilm = true;
		return null;
	}

	@Override
	protected void done()
	{
		if (!ReleaseNotifierDialog.ovaReleased.contains("Nessun Anime Rilasciato") || !ReleaseNotifierDialog.filmReleased.contains("Nessun Anime Rilasciato"))
		{
			dial = new ReleaseNotifierDialog();
			dial.setLocationRelativeTo(AnimeIndex.mainFrame);
			dial.setVisible(true);
		}
		else if (AnimeIndex.openReleaseDialog == true)
		{
			AnimeIndex.openReleaseDialog = false;
			dial = new ReleaseNotifierDialog();
			dial.setLocationRelativeTo(AnimeIndex.mainFrame);
			dial.setVisible(true);
		}
	}

}
