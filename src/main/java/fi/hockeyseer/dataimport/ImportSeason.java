package fi.hockeyseer.dataimport;

public enum ImportSeason {

	SEASON_2019_2020("startDate=2019-10-01&endDate=2020-04-07", "20192020"),
	SEASON_2018_2019("startDate=2018-10-02&endDate=2019-04-09", "20182019"),
	SEASON_2017_2018("startDate=2017-10-02&endDate=2018-04-09", "20172018"),
	SEASON_2016_2017("startDate=2016-10-10&endDate=2017-04-13", "20162017"),
	SEASON_2015_2016("startDate=2015-10-07&endDate=2016-04-11", "20152016"),
	SEASON_2014_2015("startDate=2014-10-08&endDate=2015-04-13", "20142015"),
	SEASON_2013_2014("startDate=2014-10-08&endDate=2015-04-13", "20132014");

	
	
	private ImportSeason(final String url, final String databaseFormat) {
		this.url = url;
		this.databaseFormat = databaseFormat;
	}
	private final String url;
	private final String databaseFormat;
	
	public String getUrl() {
		return url;
	}
	public String getDatabaseFormat() {
		return databaseFormat;
	}
	
	
	
}
