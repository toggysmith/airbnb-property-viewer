import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import java.awt.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.application.Platform;

public class MapController extends Pane
{
    @FXML private AnchorPane boroughMap;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane root;

    @FXML
    public void initialize()
    {
        scrollPane.prefWidthProperty().bind(root.widthProperty());
        scrollPane.prefHeightProperty().bind(root.heightProperty());
        
        createMap();
    }

    private void createMap()
    {
        BoroughButton.setBoroughMap(boroughMap);
        
        // Row 1:
        new BoroughButton(3, 0, "ENFI", "Enfield");
        // Row 2:
        new BoroughButton(2, 1, "BARN", "Barnet");
        new BoroughButton(3, 1, "HRGY", "Haringey");
        new BoroughButton(4, 1, "WALT", "Waltham Forest");
        // Row 3:
        new BoroughButton(0, 2, "HRRW", "Harrow");
        new BoroughButton(1, 2, "BREN", "Brent");
        new BoroughButton(2, 2, "CAMD", "Camden");
        new BoroughButton(3, 2, "ISLI", "Islington");
        new BoroughButton(4, 2, "HACK", "Hackney");
        new BoroughButton(5, 2, "REDB", "Redbridge");
        new BoroughButton(6, 2, "HAVE", "Havering");
        // Row 4:
        new BoroughButton(0, 3, "HILL", "Hillingdon");
        new BoroughButton(1, 3, "EALI", "Ealing");
        new BoroughButton(2, 3, "KENS", "Kensington & Chelsea");
        new BoroughButton(3, 3, "WSTM", "Westminster");
        new BoroughButton(4, 3, "TOWH", "Tower Hamlets");
        new BoroughButton(5, 3, "NEWH", "Newham");
        new BoroughButton(6, 3, "BARK", "Barking & Dagenham");
        // Row 5:
        new BoroughButton(0, 4, "HOUN", "Hounslow");
        new BoroughButton(1, 4, "HAMM", "Hammersmith & Fulham");
        new BoroughButton(2, 4, "WAND", "Wandsworth");
        new BoroughButton(3, 4, "CITY", "City");
        new BoroughButton(4, 4, "GWCH", "Greenwich");
        new BoroughButton(5, 4, "BEXL", "Bexley");
        // Row 6:
        new BoroughButton(1, 5, "RICH", "Richmond upon Thames");
        new BoroughButton(2, 5, "MERT", "Merton");
        new BoroughButton(3, 5, "LAMB", "Lambeth");
        new BoroughButton(4, 5, "STHW", "Southwark");
        new BoroughButton(5, 5, "LEWS", "Lewisham");
        // Row 7:
        new BoroughButton(1, 6, "KING", "Kingston upon Thames");
        new BoroughButton(2, 6, "SUTT", "Sutton");
        new BoroughButton(3, 6, "CROY", "Croydon");
        new BoroughButton(4, 6, "BROM", "Bromley");
    }
}