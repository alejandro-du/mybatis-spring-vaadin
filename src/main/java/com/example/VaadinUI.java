package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private CityMapper cityMapper;

    @Override
    protected void init(VaadinRequest request) {
        List<Company> allCompanies = cityMapper.findAll();
        Grid grid = new Grid(new BeanItemContainer<>(Company.class, allCompanies));

        TextField filter = new TextField();
        filter.setInputPrompt("filter by name...");
        filter.addTextChangeListener(e -> {
            List<Company> companies = cityMapper.findByName(e.getText());
            grid.setContainerDataSource(new BeanItemContainer<>(Company.class, companies));
        });

        VerticalLayout layout = new VerticalLayout(filter, grid);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }
}
