package com.goit.restaurant.hamster.service;

import com.goit.restaurant.hamster.dao.DAO;
import com.goit.restaurant.hamster.model.Image;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ImageService {
    private DAO<Image> imageDao;

    @Transactional
    public void save(Image image){
        imageDao.save(image);
    }

    @Transactional
    public void delete(long id){
        imageDao.delete(id);
    }

    @Transactional
    public Image getById(long id){
        return imageDao.getById(id);
    }

    @Transactional
    public List<Image> getAll(){
        return imageDao.getAll();
    }

    @Transactional
    public Image createImage(byte[] file){
        if (file.length == 0){
            return null;
        }
        Image image = new Image();
        image.setFile(file);
        save(image);
        return image;
    }

    public DAO<Image> getImageDao() {
        return imageDao;
    }

    public void setImageDao(DAO<Image> imageDao) {
        this.imageDao = imageDao;
    }

}
