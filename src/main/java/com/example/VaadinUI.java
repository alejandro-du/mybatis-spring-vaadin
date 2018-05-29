package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private CompanyService service;

    private Company company;

    private Grid<Company> grid = new Grid(Company.class);
    private TextField name = new TextField("Name");
    private TextField website = new TextField("Website");
    private Button save = new Button("Save", e -> saveCompany());

    @Override
    protected void init(VaadinRequest request) {
        updateGrid();
        grid.addSelectionListener(e -> updateForm());

        VerticalLayout layout = new VerticalLayout(grid, name, website, save);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }

    private void updateGrid() {
        grid.setItems(service.findAll());
        setFormVisible(false);
    }

    private void updateForm() {
        setFormVisible(!grid.getSelectedItems().isEmpty());
        if (!grid.getSelectedItems().isEmpty()) {
            company = grid.asSingleSelect().getValue();
            Binder<Company> binder = new Binder<>(Company.class);
            binder.bindInstanceFields(this);
            binder.setBean(company);
        }
    }

    private void setFormVisible(boolean visible) {
        name.setVisible(visible);
        website.setVisible(visible);
        save.setVisible(visible);
    }

    private void saveCompany() {
        service.update(company);
        updateGrid();
    }
}
