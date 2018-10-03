package model;

import Photo.PhotoObj;

public interface Drink {

    public void addName(String name);

    public void setType(String type);

    public void setAlcPerc(String alcPerc);

    public void setNotes(String notes);

    public void addPhoto(PhotoObj photoObj);

    public String getName();

    public String getType();

    public String getAlcPerc();

    public String getNotes();

    public PhotoObj getPhoto();
}
