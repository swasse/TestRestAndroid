package be.ehb.testrest.model;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class RandomJoke {
    private String imageUrl, jokeText;

    public RandomJoke(String imageUrl, String jokeText) {
        this.imageUrl = imageUrl;
        this.jokeText = jokeText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }
}
