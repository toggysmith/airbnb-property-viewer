package controller;

import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.util.stream.Collectors;

// Project
import model.*;
import view.*;
import controller.*;

/**
 * BoroughWindowController hosts FXML GUI elements.
 * 
 * @author Adam Murray (K21003575)
 * @author Augusto Favero (K21059800)
 * @author Mathew Tran (K21074020)
 * @author Tony Smith (K21064940)
 * @version 1.0
 */
public class BoroughWindowController implements Controller
{
    @FXML public TableView<AirbnbListing> boroughTable;
    @FXML public TableColumn<AirbnbListing, String> nameColumn;
    @FXML public TableColumn<AirbnbListing, String> priceColumn;
    @FXML public TableColumn<AirbnbListing, String> reviewsColumn;
    @FXML public TableColumn<AirbnbListing, String> minNightsColumn;

    @FXML public ComboBox<ComboBoxOrderEnum> orderBox;
    
    @FXML public Label fromPrice;
    @FXML public Label toPrice;
    
    private BoroughWindow boroughWindow;
    private Map<ComboBoxOrderEnum, TableColumn<AirbnbListing, String>> comboBoxOrder;
    private List<Controller> controllers;
    

    @FXML public HBox pieChart;
    @FXML public ComboBox attributeBox;

    public void Initialise(ObservableList<AirbnbListing> listings, BoroughWindow boroughWindow)
    {
        this.boroughWindow = boroughWindow;
        populateTable(listings);
        populateOrderBox();
        setOnRowClicked();
        assignSort();
        assignPriceLabels();
    
        setUpComboBox();
    }
    
    public void setControllers(List<Controller> controllers)
    {
        this.controllers = controllers;
    }
    
    protected void populateTable(ObservableList<AirbnbListing> listings)
    {
        boroughTable.setItems(listings);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("host_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        reviewsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));
        minNightsColumn.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));
    }
    
    protected void populateOrderBox()
    {
        orderBox.getItems().addAll(getSortBoxOptions());
    }
    
    private List<ComboBoxOrderEnum> getSortBoxOptions()
    {
        comboBoxOrder = new HashMap<>();
        
        comboBoxOrder.put(ComboBoxOrderEnum.HOST_NAME_ASCENDING, nameColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.HOST_NAME_DESCENDING, nameColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.PRICE_ASCENDING, priceColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.PRICE_DESCENDING, priceColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.NUMBER_OF_REVIEWS_ASCENDING, reviewsColumn);
        comboBoxOrder.put(ComboBoxOrderEnum.NUMBER_OF_REVIEWS_DESCENDING, reviewsColumn);
        
        List<ComboBoxOrderEnum> options = Arrays.asList(ComboBoxOrderEnum.values());
        
        return options;
    }

    protected void setOnRowClicked()
    {
        boroughTable.setRowFactory(e -> tableClicked());
    }

    private TableRow<AirbnbListing> tableClicked()
    {
        TableRow<AirbnbListing> row = new TableRow<>();
        row.setOnMouseClicked(event -> rowClicked(row));
        return row;
    }

    private void rowClicked(TableRow<AirbnbListing> row)
    {
        if (! row.isEmpty()) {
            AirbnbListing listing = row.getItem();
            boroughWindow.createPropertyWindow(listing);
        }
    }
    
    protected void assignSort()
    {
        orderBox.setOnAction(e -> sort(orderBox.getValue()));
    }
    
    private void sort(ComboBoxOrderEnum comboBoxOrderEnum)
    {
        if (comboBoxOrderEnum == null)
        {
            return;    
        }

        TableColumn<AirbnbListing, String> column = comboBoxOrder.get(comboBoxOrderEnum);
        TableView table = boroughTable;
        column.setSortable(true);
        ObservableList<TableColumn> sortBy = table.getSortOrder();
        sortBy.clear();
        sortBy.add(column);
        column.setSortType(comboBoxOrderEnum.getOrder());
        table.sort();
        column.setSortable(false);
    }
    
    protected void assignPriceLabels()
    {
        fromPrice.setText(boroughWindow.getFromPrice());
        toPrice.setText(boroughWindow.getToPrice());
    }
    
    private void setUpPieChart(String selectedAttribute)
    {
        int[] attributeValues = new int[boroughTable.getItems().size()];   
        
        if(selectedAttribute.equals("Price")){
            attributeValues = boroughTable.getItems().stream()
                                                     .mapToInt(listing -> listing.getPrice())
                                                     .toArray();
        }else if(selectedAttribute.equals("Number of reviews")){
            attributeValues = boroughTable.getItems().stream()
                                                     .mapToInt(listing -> listing.getNumberOfReviews())
                                                     .toArray();
        }else if(selectedAttribute.equals("Min number of nights")){
            attributeValues = boroughTable.getItems().stream()
                                                     .mapToInt(listing -> listing.getNumberOfReviews())
                                                     .toArray();
        }
                                                    
        
        try
        {
            PieChartView view = new PieChartView(attributeValues);
            AnchorPane chartPane = view.setUpPieChart();
            pieChart.getChildren().setAll(chartPane);
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
         
    }
    
    private void setUpComboBox()
    {
        List<String> comboBoxStrings = new ArrayList<String>();
        comboBoxStrings.add("Price");
        comboBoxStrings.add("Number of reviews");
        comboBoxStrings.add("Min number of nights");
        attributeBox.getItems().addAll(comboBoxStrings);
    }
    
    @FXML
    public void selectedBox()
    {
            setUpPieChart(attributeBox.getValue().toString());
    }
}