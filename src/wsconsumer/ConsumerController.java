/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsconsumer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wsconsumer.models.Produto;
import wsconsumer.providers.ProdutoProvider;

/**
 * FXML Controller class
 *
 * @author drink
 */
public class ConsumerController implements Initializable {

    @FXML
    private Button btnListar;
    @FXML
    private TableView<Produto> tableViewProdutos;
    @FXML
    private TableColumn<?, ?> tableColumnId;
    @FXML
    private TableColumn<?, ?> tableColumnNome;
    @FXML
    private Label lblTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableViewProdutos.itemsProperty().addListener((observable, oldValue, newValue) -> {
            lblTotal.setText(Integer.toString(newValue.size()));
        });
        this.pp = new ProdutoProvider();
    }

    private ProdutoProvider pp;

    @FXML
    private void btnListarClick(ActionEvent event) {
        try {
            tableViewProdutos.setItems(FXCollections.observableArrayList(this.pp.getProdutos()));
        } catch (Exception ex) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setContentText("Houve algum problema! \r\n" + ex.getMessage());
            erro.showAndWait();
        }
    }

}
