package com.example.application.base.ui.view;

import com.example.application.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.streams.DownloadHandler;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@StyleSheet("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Poppins:wght@400;500;600;700&display=swap")

@Route("")
@PermitAll
public final class MainView extends Div {

    // Asset data class
    public static class Asset {
        private String type;
        private String name;
        private String id;
        private LocalDateTime takeTime;
        private LocalDateTime returnTime;
        private String status;
        private String action;

        public Asset(String type, String name, String id, LocalDateTime takeTime, LocalDateTime returnTime, String status, String action) {
            this.type = type;
            this.name = name;
            this.id = id;
            this.takeTime = takeTime;
            this.returnTime = returnTime;
            this.status = status;
            this.action = action;
        }

        // Getters and setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public LocalDateTime getTakeTime() { return takeTime; }
        public void setTakeTime(LocalDateTime takeTime) { this.takeTime = takeTime; }
        public LocalDateTime getReturnTime() { return returnTime; }
        public void setReturnTime(LocalDateTime returnTime) { this.returnTime = returnTime; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
    }

    public MainView() {
        addClassName("main-view");
        Image logo24Assets = new Image(DownloadHandler.forClassResource(getClass(),"/images/logo24Assets.png"), "Logo 24 Assets");
        logo24Assets.setWidth("auto");
        logo24Assets.setHeight("32px");
        ViewToolbar toolbar = new ViewToolbar(logo24Assets); // Gunakan Image sebagai parameter
        add(toolbar);

        setSizeFull();
        getStyle()
                .set("background-color", "#f8f9fa")
                .set("font-family", "'Poppins', sans-serif")
                .set("padding", "0")
                .set("margin", "0");

        add(createLayout());
    }

    private Component createLayout() {
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);
        mainLayout.getStyle()
                .set("background-color", "#f8f9fa")
                .set("padding", "16px");

        // Left sidebar
        VerticalLayout leftSidebar = createLeftContent();
        leftSidebar.setWidth("50%");
        leftSidebar.setHeightFull();

        // Right content area
        VerticalLayout rightContent = createRightContent();
        rightContent.setWidth("50%");
        rightContent.setFlexGrow(1);
        rightContent.setHeightFull();

        mainLayout.add(leftSidebar, rightContent);
        return mainLayout;
    }

    private VerticalLayout createLeftContent() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setPadding(true);
        sidebar.setSpacing(true);
        sidebar.getStyle()
                .set("padding", "16px")
                .set("margin-bottom", "16px");

        // Form fields
        VerticalLayout formSection = createFormSection();

        // History fields
        VerticalLayout historySection = createHistorySection();


        sidebar.add(formSection, historySection);
        return sidebar;
    }

    private VerticalLayout createFormSection() {
        HorizontalLayout orderingHeader = new HorizontalLayout();
        orderingHeader.setWidthFull();
        orderingHeader.setAlignItems(FlexComponent.Alignment.CENTER);
        orderingHeader.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        orderingHeader.getStyle()
                .set("border-radius", "8px")
                .set("padding", "8px 12px")
                .set("margin-bottom", "16px");

        Icon orderIcon = new Icon(VaadinIcon.ALIGN_LEFT);
        orderIcon.setColor("#7c3aed");
        Span orderingText = new Span("Ordering");
        orderingText.getStyle()
                .set("color", "#7c3aed")
                .set("font-weight", "600")
                .set("font-size", "14px");

        orderingHeader.add(orderIcon, orderingText);

        VerticalLayout formSection = new VerticalLayout();
        formSection.setPadding(false);
        formSection.setSpacing(true);
        formSection.setHeight("auto");

        formSection.getStyle()
                .set("background-color", "#ffffff")
                .set("border-radius", "12px")
                .set("padding", "16px")
                .set("margin-bottom", "16px")
                .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");

        // Name field
        TextField nameField = new TextField("Name");
        nameField.setWidthFull();

        // Row 1: Status, Grade, Class, Location
        HorizontalLayout row1 = new HorizontalLayout();
        row1.setWidthFull();
        row1.setSpacing(true);

        ComboBox<String> statusCombo = new ComboBox<>("Status");
        statusCombo.setItems("All", "Available", "In Use", "Returned");
        statusCombo.setValue("All");
        statusCombo.setWidth("125px");

        ComboBox<String> gradeCombo = new ComboBox<>("Grade");
        gradeCombo.setItems("A", "B", "C", "D");
        gradeCombo.setWidth("125px");

        ComboBox<String> classCombo = new ComboBox<>("Class");
        classCombo.setItems("Electronics", "Furniture", "Vehicles");
        classCombo.setWidth("125px");

        ComboBox<String> locationCombo = new ComboBox<>("Location");
        locationCombo.setItems("Room 101", "Room 102", "Storage");
        locationCombo.setWidth("125px");

        row1.add(statusCombo, gradeCombo, classCombo, locationCombo);

        // Row 2: Date Time Pickers
        HorizontalLayout row2 = new HorizontalLayout();
        row2.setWidthFull();
        row2.setSpacing(true);

        // Custom date time layout
        VerticalLayout startDateSection = new VerticalLayout();
        startDateSection.setPadding(false);
        startDateSection.setSpacing(false);

        DateTimePicker startDateField = new DateTimePicker();
        startDateField.setWidth("250px");
        startDateField.getStyle().set("font-size", "12px");

        HorizontalLayout startDateTime = new HorizontalLayout(startDateField);
        startDateTime.setSpacing(true);
        startDateTime.setAlignItems(FlexComponent.Alignment.CENTER);

        Icon angleDoubleRight = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        angleDoubleRight.setSize("16px");
        angleDoubleRight.getStyle().set("color", "#6c757d");

        startDateTime.add(angleDoubleRight);

        // End date time
        DateTimePicker endDateField = new DateTimePicker();
        endDateField.setWidth("250px");
        endDateField.getStyle().set("font-size", "12px");


        HorizontalLayout endDateTime = new HorizontalLayout(endDateField);
        endDateTime.setSpacing(true);
        endDateTime.setAlignItems(FlexComponent.Alignment.CENTER);

        row2.add(startDateTime, endDateTime);

        // Note field
        TextArea noteField = new TextArea("Note");
        noteField.setWidthFull();

        formSection.add(orderingHeader, nameField, row1, row2, noteField);
        return formSection;
    }

    private VerticalLayout createHistorySection() {
        VerticalLayout historySection = new VerticalLayout();
        historySection.setPadding(false);
        historySection.setSpacing(true);
        historySection.setHeight("auto");

        historySection.getStyle()
                .set("background-color", "#ffffff")
                .set("border-radius", "12px")
                .set("padding", "16px")
                .set("margin-bottom", "16px")
                .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");

        // History header
        HorizontalLayout historyHeader = new HorizontalLayout();
        historyHeader.setWidthFull();
        historyHeader.setAlignItems(FlexComponent.Alignment.CENTER);
        historyHeader.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        historyHeader.getStyle()
                .set("border-radius", "8px")
                .set("padding", "8px 12px")
                .set("margin-bottom", "16px");

        Icon historyIcon = new Icon(VaadinIcon.CLOCK);
        historyIcon.setColor("#7c3aed");
        Span historyText = new Span("History");
        historyText.getStyle()
                .set("color", "#7c3aed")
                .set("font-weight", "600")
                .set("font-size", "14px");

        historyHeader.add(historyIcon, historyText);

        // Search field
        TextField historySearch = new TextField();
        historySearch.setPlaceholder("Search goods");
        historySearch.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        historySearch.getStyle()
                .set("margin", "0")
                .set("padding-right", "10px");

        // Sort Button (with icon and text)
        Icon sortIconDown = new Icon(VaadinIcon.ARROW_LONG_DOWN);
//        Icon sortIconUp = new Icon(VaadinIcon.ARROWS_LONG_UP);
//        HorizontalLayout sortIcon = new HorizontalLayout();
//        sortIcon.add(sortIconDown, sortIconUp);
//        sortIcon.setSpacing(false);
        Button sortBtn = new Button("Sort by", sortIconDown);
        sortBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        sortBtn.getStyle()
                .set("border", "1px solid var(--lumo-contrast-20pct)")  // Border seperti pada gambar
                .set("border-radius", "4px")
                .set("margin-right", "10px")
                .set("padding", "0.5em");

        // Filter Button (with icon and text)
        Button filterBtn = new Button("Filter", new Icon(VaadinIcon.ALIGN_CENTER));
        filterBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        filterBtn.getStyle()
                .set("border", "1px solid var(--lumo-contrast-20pct)")
                .set("border-radius", "4px")
                .set("padding", "0.5em");

        // Container untuk semua komponen dalam satu baris
        HorizontalLayout container = new HorizontalLayout();
        container.setWidthFull();
        container.setAlignItems(FlexComponent.Alignment.CENTER);
        container.setSpacing(false);
        container.addAndExpand(historySearch);  // Search field mengambil sisa ruang
        container.add(sortBtn, filterBtn);     // Tombol di sebelah kanan

        Grid<Asset> historyGrid = createHistoryGrid();

        // Tambahkan ke layout utama
        historySection.add(historyHeader, container, historyGrid);
        return historySection;
    }

    private Grid<Asset> createHistoryGrid() {
        Grid<Asset> grid = new Grid<>(Asset.class, false);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        grid.setHeight("300px");
        grid.getStyle().set("font-size", "12px");

        // Add columns with icons
        grid.addComponentColumn(asset -> {
            Icon icon = new Icon(VaadinIcon.PACKAGE);
            icon.setSize("16px");
            return icon;
        }).setHeader("").setWidth("40px").setFlexGrow(0);

        grid.addColumn(Asset::getName).setHeader("Product name").setWidth("120px");
        grid.addColumn(Asset::getId).setHeader("Take").setWidth("80px");
        grid.addColumn(asset -> formatDateTime(asset.getTakeTime())).setHeader("Return").setWidth("100px");
        grid.addColumn(asset -> formatDateTime(asset.getReturnTime())).setHeader("Status").setWidth("100px");

        grid.addComponentColumn(asset -> {
            Span statusBadge = new Span(asset.getStatus());
            if ("Finished".equals(asset.getStatus())) {
                statusBadge.getStyle()
                        .set("background-color", "#fee2e2")
                        .set("color", "#dc2626")
                        .set("padding", "2px 8px")
                        .set("border-radius", "12px")
                        .set("font-size", "10px");
            } else if ("In use".equals(asset.getStatus())) {
                statusBadge.getStyle()
                        .set("background-color", "#dcfce7")
                        .set("color", "#16a34a")
                        .set("padding", "2px 8px")
                        .set("border-radius", "12px")
                        .set("font-size", "10px");
            }
            return statusBadge;
        }).setHeader("Status").setWidth("80px");

        grid.addComponentColumn(asset -> {
            Button actionBtn = new Button("Continue");
            actionBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);
            actionBtn.getStyle().set("font-size", "10px");
            return actionBtn;
        }).setHeader("Action").setWidth("80px");

        // Sample data
        List<Asset> assets = Arrays.asList(
                new Asset("📦", "Speaker", "#A0001", LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(1), "Finished", "Continue"),
                new Asset("📦", "Monitor", "#B0001", LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(2), "In use", "Continue"),
                new Asset("📦", "Tab", "#C0001", LocalDateTime.now().minusMinutes(30), LocalDateTime.now().plusHours(3), "In use", "Continue"),
                new Asset("📦", "Projector", "#D0001", LocalDateTime.now().minusHours(3), LocalDateTime.now().plusHours(1), "Finished", "Continue"),
                new Asset("📦", "Projector", "#E0001", LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(2), "In use", "Continue"),
                new Asset("📦", "Monitor", "#F0001", LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(4), "In use", "Continue")
        );

        grid.setItems(assets);
        return grid;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.getDayOfMonth() + " April 2025\n" +
                String.format("%02d:%02d %s",
                        dateTime.getHour() > 12 ? dateTime.getHour() - 12 : dateTime.getHour(),
                        dateTime.getMinute(),
                        dateTime.getHour() >= 12 ? "PM" : "AM");
    }

    private VerticalLayout createRightContent() {
        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);
        content.getStyle()
                .set("padding", "16px")
                .set("margin-bottom", "16px");

        VerticalLayout goodSection = goodSection();

        content.add(goodSection);
        return content;
    }

    private VerticalLayout goodSection() {
        VerticalLayout container = new VerticalLayout();
        container.getStyle()
                .set("background-color", "#ffffff")
                .set("border-radius", "12px")
                .set("padding", "16px")
                .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");


        HorizontalLayout goodsHeader = createGoodsHeader();

        //  Name Filters
        HorizontalLayout nameFilter = createNameFilters();
        nameFilter.setWidthFull();

        // Category filters
        HorizontalLayout categories = createCategoryFilters();

        // Products grid
        VerticalLayout productsGrid = createProductsGrid();

        // Bottom section with submit
        HorizontalLayout bottomSection = createBottomSection();

        container.add(goodsHeader, nameFilter, categories, productsGrid, bottomSection);
        return container;
    }

    private HorizontalLayout createGoodsHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.getStyle()
                .set("padding", "16px");

        // Goods title with icon
        HorizontalLayout titleSection = new HorizontalLayout();
        titleSection.setAlignItems(FlexComponent.Alignment.CENTER);
        titleSection.setSpacing(true);

        Icon goodsIcon = new Icon(VaadinIcon.PACKAGE);
        goodsIcon.setColor("#7c3aed");
        Span goodsTitle = new Span("Goods");
        goodsTitle.getStyle()
                .set("color", "#7c3aed")
                .set("font-weight", "600")
                .set("font-size", "16px");

        titleSection.add(goodsIcon, goodsTitle);

        header.add(titleSection);
        return header;
    }

    private HorizontalLayout createNameFilters() {
        // Search field - now with proper full width behavior
        HorizontalLayout goodsSearch = new HorizontalLayout();
        goodsSearch.setWidthFull();
        goodsSearch.setPadding(false);
        goodsSearch.setSpacing(false);

        TextField nameSearch = new TextField();
        nameSearch.setWidthFull();
        nameSearch.setPlaceholder("Search goods");
        nameSearch.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        nameSearch.getStyle()
                .set("margin", "0");

        goodsSearch.add(nameSearch);
        goodsSearch.setFlexGrow(1, nameSearch);
        return goodsSearch;
    }

    private HorizontalLayout createCategoryFilters() {
        HorizontalLayout filters = new HorizontalLayout();
        filters.setWidthFull();
        filters.setSpacing(true);
        filters.setAlignItems(FlexComponent.Alignment.CENTER);
        filters.getStyle()
                .set("flex-wrap", "wrap")
                .set("gap", "8px")
                .set("padding-top", "10px");

        Button allBtn = createCategoryButton("🔄", "All", true);
        Button soundBtn = createCategoryButton("🔊", "Sound System", false);
        Button electronicBtn = createCategoryButton("💻", "Electronic", false);
        Button keysBtn = createCategoryButton("🔑", "Room keys", false);
        Button projectorBtn = createCategoryButton("📽️", "Projector", false);

        filters.add(allBtn, soundBtn, electronicBtn, keysBtn, projectorBtn);
        return filters;
    }

    private Button createCategoryButton(String icon, String text, boolean active) {
        Button btn = new Button();
        btn.setWidth("auto"); // Let buttons size based on content

        HorizontalLayout content = new HorizontalLayout();
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        content.setSpacing(true);
        content.setPadding(false);

        Span iconSpan = new Span(icon);
        Span textSpan = new Span(text);
        textSpan.getStyle()
                .set("font-size", "12px")
                .set("white-space", "nowrap"); // Prevent text wrapping

        content.add(iconSpan, textSpan);
        btn.getElement().appendChild(content.getElement());

        btn.getStyle()
                .set("border", "1px solid #e9ecef")
                .set("border-radius", "5px")
                .set("padding", "0px 12px")
                .set("font-size", "12px")
                .set("min-height", "32px")
                .set("margin", "0"); // Remove any default margins

        if (active) {
            btn.getStyle()
                    .set("background-color", "#7c3aed")
                    .set("color", "white")
                    .set("border-color", "#7c3aed");
        } else {
            btn.getStyle()
                    .set("background-color", "white")
                    .set("color", "#6c757d");
        }

        return btn;
    }

    private VerticalLayout createProductsGrid() {
        VerticalLayout gridSection = new VerticalLayout();
        gridSection.setPadding(false);
        gridSection.setSpacing(true);
        gridSection.getStyle()
                .set("overflow-y", "auto")  // Enable vertical scrolling
                .set("max-height", "575px") // Set max height for scrollable area
                .set("margin-bottom", "16px");

        // Category sections
        VerticalLayout soundSystemSection = createCategorySection("Sound System", Arrays.asList(
                createProductCard("Speaker", "Audio/Visual", "Available"),
                createProductCard("Microphone", "Audio/Visual", "Available"),
                createProductCard("Kabel HDMI", "Audio/Visual", "Available")
        ));

        VerticalLayout electronicSection = createCategorySection("Electronic", Arrays.asList(
                createProductCard("Monitor", "Audio/Visual", "Available"),
                createProductCard("Tab", "Audio/Visual", "Available"),
                createProductCard("Projector", "Audio/Visual", "Available")
        ));

        VerticalLayout keysSection = createCategorySection("Keys", Arrays.asList(
                createProductCard("Room keys", "Keys", "Available")
        ));

        gridSection.add(soundSystemSection, electronicSection, keysSection);
        return gridSection;
    }

    private VerticalLayout createCategorySection(String categoryName, List<VerticalLayout> products) {
        VerticalLayout section = new VerticalLayout();
        section.setPadding(false);
        section.setSpacing(true);
        section.getStyle()
                .set("padding", "10px")
                .set("margin-bottom", "16px");

        H4 categoryTitle = new H4(categoryName);
        categoryTitle.getStyle()
                .set("margin", "0 0 12px 0")
                .set("font-size", "14px")
                .set("color", "#374151");

        Div productGrid = new Div();
        productGrid.getStyle()
                .set("display", "flex")
                .set("flex-direction", "row")
                .set("flex-wrap", "wrap")
                .set("gap", "12px")
                .set("align-items", "flex-start");

        for (VerticalLayout product : products) {
            productGrid.add(product);
        }

        section.add(categoryTitle, productGrid);
        return section;
    }

    private VerticalLayout createProductCard(String name, String category, String status) {
        VerticalLayout card = new VerticalLayout();
        card.setPadding(true);
        card.setSpacing(true);
        card.setAlignItems(FlexComponent.Alignment.CENTER);
        card.setWidth("140px");
        card.setHeight("auto");
        card.getStyle()
                .set("border", "1px solid #e9ecef")
                .set("border-radius", "8px")
                .set("background-color", "#fafafa")
                .set("cursor", "pointer")
                .set("text-align", "center");

        // Product image placeholder
        Div imagePlaceholder = new Div();
        imagePlaceholder.setWidth("60px");
        imagePlaceholder.setHeight("60px");
        imagePlaceholder.getStyle()
                .set("background-color", "#e9ecef")
                .set("border-radius", "8px")
                .set("margin-bottom", "8px");

        // Product name
        Span nameSpan = new Span(name);
        nameSpan.getStyle()
                .set("font-weight", "600")
                .set("font-size", "14px")
                .set("color", "#374151")
                .set("text-align", "center");

        // Category
        Span categorySpan = new Span("Kategori\n#" + category);
        categorySpan.getStyle()
                .set("font-size", "10px")
                .set("color", "#6b7280")
                .set("text-align", "center")
                .set("white-space", "pre-line");

        // Status badge
        Span statusBadge = new Span(status);
        statusBadge.getStyle()
                .set("background-color", "#dbeafe")
                .set("color", "#1d4ed8")
                .set("padding", "2px 8px")
                .set("border-radius", "12px")
                .set("font-size", "10px")
                .set("margin-top", "auto");

        card.add(imagePlaceholder, nameSpan, categorySpan, statusBadge);
        return card;
    }

    private HorizontalLayout createBottomSection() {
        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setWidthFull();
        bottom.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        bottom.setAlignItems(FlexComponent.Alignment.CENTER);
        bottom.getStyle().set("margin-top", "20px");

        // Quantity selector
        HorizontalLayout qtySection = new HorizontalLayout();
        qtySection.setAlignItems(FlexComponent.Alignment.CENTER);
        qtySection.setSpacing(true);

        Span qtyLabel = new Span("Qty");
        qtyLabel.getStyle().set("font-size", "14px");

        ComboBox<Integer> qtyCombo = new ComboBox<>();
        qtyCombo.setItems(1, 2, 3, 4, 5);
        qtyCombo.setValue(1);
        qtyCombo.setWidth("80px");

        qtySection.add(qtyLabel, qtyCombo);

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitBtn.getStyle()
                .set("background-color", "#7c3aed")
                .set("border-radius", "8px")
                .set("padding", "10px 24px");

        bottom.add(qtySection, submitBtn);
        return bottom;
    }

    public static void showMainView() {
        UI.getCurrent().navigate(MainView.class);
    }
}