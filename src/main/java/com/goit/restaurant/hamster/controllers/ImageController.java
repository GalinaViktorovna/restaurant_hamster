package com.goit.restaurant.hamster.controllers;

import com.goit.restaurant.hamster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/imageController/{imageId}")
    @ResponseBody
    public byte[] getImage(@PathVariable long imageId)  {
        return imageService.getById(imageId).getFile();
    }

}
