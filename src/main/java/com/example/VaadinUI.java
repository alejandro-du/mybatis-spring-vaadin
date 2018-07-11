package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("")
public class VaadinUI extends VerticalLayout {

    private final CompanyService service;

    private Company company;

    private Grid<Company> grid = new Grid(Company.class);
    private TextField name = new TextField("Name");
    private TextField website = new TextField("Website");
    private Button save = new Button("Save", e -> saveCompany());

    public VaadinUI(CompanyService service) {
        this.service = service;

        updateGrid();
        grid.addSelectionListener(e -> updateForm());

        add(grid, name, website, save);
        setMargin(true);
        setSpacing(true);
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
