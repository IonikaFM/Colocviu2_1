package ro.pub.cs.systems.eim.practicaltest02;

public class Pokemon {
    private String name = null;
    private String imageUrl = null;
    private String ability = null;

    public Pokemon() {
        this.name = null;
        this.imageUrl = null;
        this.ability = null;
    }

    public Pokemon(String name, String imageUrl, String ability) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ability='" + ability + '\'' +
                '}';
    }
}
