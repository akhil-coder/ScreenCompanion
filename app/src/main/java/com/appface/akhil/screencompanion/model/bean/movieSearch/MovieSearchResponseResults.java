package com.appface.akhil.screencompanion.model.bean.movieSearch;

public class MovieSearchResponseResults implements java.io.Serializable {
    private static final long serialVersionUID = 3491627527151151835L;
    private String logo_path;
    private String name;
    private int id;
    private String origin_country;

    public MovieSearchResponseResults(String logo_path, String name, int id, String origin_country) {
        this.logo_path = logo_path;
        this.name = name;
        this.id = id;
        this.origin_country = origin_country;
    }

    public String getLogo_path() {
        return this.logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin_country() {
        return this.origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    @Override
    public String toString() {
        return "MovieSearchResponseResults{" +
                "logo_path='" + logo_path + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", origin_country='" + origin_country + '\'' +
                '}';
    }
}
