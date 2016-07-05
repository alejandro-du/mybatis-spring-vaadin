package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private CompanyService service;

    private Company company;

    private Grid grid = new Grid();
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
        List<Company> companies = service.findAll();
        grid.setContainerDataSource(new BeanItemContainer<>(Company.class, companies));
        setFormVisible(false);
    }

    private void updateForm() {
        setFormVisible(!grid.getSelectedRows().isEmpty());
        if (!grid.getSelectedRows().isEmpty()) {
            company = (Company) grid.getSelectedRow();
            BeanFieldGroup.bindFieldsUnbuffered(company, this);
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
