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
    private CompanyService companyService;
    private Company company;

    private Grid grid = new Grid();
    private TextField name = new TextField("Name");
    private TextField website = new TextField("Website");
    private Button save = new Button("Save");

    @Override
    protected void init(VaadinRequest request) {
        TextField filter = new TextField("Filter by name:");
        filter.addTextChangeListener(e -> {
            updateGrid(e.getText());
        });

        grid.addSelectionListener(e -> setCompany((Company) grid.getSelectedRow()));

        save.addClickListener(e -> {
            companyService.update(company);
            updateGrid(filter.getValue());
        });

        VerticalLayout layout = new VerticalLayout(filter, grid, name, website, save);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        updateGrid("");
    }

    private void setCompany(Company company) {
        this.company = company;
        setFormVisible(company != null);
        if (company != null) {
            BeanFieldGroup.bindFieldsUnbuffered(company, this);
        }
    }

    private void updateGrid(String nameFilter) {
        List<Company> companies = companyService.findByName(nameFilter);
        grid.setContainerDataSource(new BeanItemContainer<>(Company.class, companies));
        setFormVisible(false);
    }

    private void setFormVisible(boolean visible) {
        name.setVisible(visible);
        website.setVisible(visible);
        save.setVisible(visible);
    }

}
