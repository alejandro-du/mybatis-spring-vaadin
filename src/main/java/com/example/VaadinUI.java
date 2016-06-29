package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private CityMapper cityMapper;

    @Override
    protected void init(VaadinRequest request) {
        List<Company> companies = cityMapper.findAll();
        Grid grid = new Grid(new BeanItemContainer<Company>(Company.class, companies));
        setContent(grid);
    }
}
