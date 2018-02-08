package org.esia.hien_tran.pokepokedex.models;

/**
 * Created by alexhien on 22/01/2018.
 */

//Représente les informations que l'on veut récupérer du pokemon du fichier json
public class Pokemon {
    private String name;
    private String url;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlParts = url.split("/");
        return Integer.parseInt(urlParts[urlParts.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
