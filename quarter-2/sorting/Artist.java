class Artist implements Comparable<Artist> {
  private String name;
  private String hitSong;
  private long sales;

  // artists with more sales are greater
  public static final int SORT_SALES_UP = 1;

  // artists with less sales are greater
  public static final int SORT_SALES_DOWN = 2;

  // names that come later in the alphabet are greater
  public static final int SORT_NAME = 3;

  public static int sortType = SORT_SALES_UP;

  public static void setSortType(int type) { sortType = type; }

  @Override
  public int compareTo(Artist other) {
    if (sortType == SORT_SALES_UP) {
      return (int)(sales - other.sales);
    } else if (sortType == SORT_SALES_DOWN) {
      return (int)(other.sales - sales);
    } else if (sortType == SORT_NAME) {
      return name.compareTo(other.name);
    } else {
      return 0;
    }
  }

  public Artist(String name, String hitSong, long sales) {
    this.name = name;
    this.hitSong = hitSong;
    this.sales = sales;
  }

  public String getSongName() { return hitSong; }
  public String getArtistName() { return name; }
  public long getYearlySales() { return sales; }

  @Override
  public String toString() {
    return String.format("Artist: %s, sales=%d, hitSong: %s", name, sales,
                         hitSong);
  }
}
