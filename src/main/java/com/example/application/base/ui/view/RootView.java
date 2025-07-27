package com.example.application.base.ui.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route("")
@PermitAll
public class RootView extends Div {
    public RootView() {
        UI.getCurrent().navigate("home");
        UI.getCurrent().getPage().reload();
    }
}
