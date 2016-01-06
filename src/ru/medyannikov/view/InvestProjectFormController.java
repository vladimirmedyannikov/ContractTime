package ru.medyannikov.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ru.medyannikov.application.Main;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.InvestProjectDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.model.InvestProject;
import ru.medyannikov.model.StageProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 03.01.2016.
 */
public class InvestProjectFormController {
    @FXML
    private TableView<InvestProject> investProjectTableView;
    @FXML
    private TableColumn<InvestProject, String> investProjectName;
    @FXML
    private TableColumn<InvestProject, String> investProjectNumber;
    @FXML
    private TableColumn<InvestProject, String> investProjectDept;
    @FXML
    private TableColumn<InvestProject, String> investProjectUser;
    @FXML
    private TableColumn<InvestProject, String> investProjectDateBeginPlan;
    @FXML
    private TableColumn<InvestProject, String> investProjectDateEndPlan;
    @FXML
    private TableColumn<InvestProject, String> investProjectDateBeginProg;
    @FXML
    private TableColumn<InvestProject, String> investProjectDateEndProg;
    @FXML
    private TableColumn<InvestProject, String> investProjectAbout;


    @FXML
    private TableView<StageProject> stageProjectTableView;
    @FXML
    private TableColumn<StageProject, String> stageProjectName;
    @FXML
    private TableColumn<StageProject, String> stageProjectUser;
    @FXML
    private TableColumn<StageProject, String> stageProjectDateBeginPlan;
    @FXML
    private TableColumn<StageProject, String> stageProjectDateEndPlan;
    @FXML
    private TableColumn<StageProject, String> stageProjectDateBeginUser;
    @FXML
    private TableColumn<StageProject, String> stageProjectDateEndUser;
    @FXML
    private TableColumn<StageProject, String> stageProjectDateBeginProg;
    @FXML
    private TableColumn<StageProject, String> stageProjectDateEndProg;
    @FXML
    private TableColumn<StageProject, String> stageProjectStatus;
    @FXML
    private TableColumn<StageProject, String> stageProjectCommentUser;

    @FXML
    private ContextMenu investProjectMenu;
    @FXML
    private MenuItem addInvestProject;
    @FXML
    private MenuItem editInvestProject;

    @FXML
    private Button buttonOpen;

    @FXML
    private InvestProject investProject;

    @FXML
    private TreeTableView<StageProject> treeTableViewStage;
    @FXML
    private TreeTableColumn<StageProject, String> nameStage;
    @FXML
    private TreeTableColumn<StageProject, String> idParent;


    public InvestProjectFormController() {
    }

    private Main mainApp;

    @FXML
    private void initialize(){
        investProjectName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return investProjectStringCellDataFeatures.getValue().nameProjectProperty();
            }
        });
        investProjectNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return investProjectStringCellDataFeatures.getValue().numberProjectProperty();
            }
        });
        investProjectDept.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return investProjectStringCellDataFeatures.getValue().departmentProperty().getValue().nameDepartmentProperty();
            }
        });
        investProjectUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return investProjectStringCellDataFeatures.getValue().userProperty().getValue().fullNameProperty();
            }
        });
        investProjectDateBeginPlan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return new SimpleStringProperty(investProjectStringCellDataFeatures.getValue().dateBeginProperty().getValue().toString());
            }
        });
        investProjectDateEndPlan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return new SimpleStringProperty(investProjectStringCellDataFeatures.getValue().dateEndProperty().getValue().toString());
            }
        });
        investProjectDateBeginProg.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return new SimpleStringProperty(investProjectStringCellDataFeatures.getValue().dateBeginProgProperty().getValue().toString());
            }
        });
        investProjectDateEndProg.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return new SimpleStringProperty(investProjectStringCellDataFeatures.getValue().dateEndProgProperty().getValue().toString());
            }
        });
        investProjectAbout.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvestProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvestProject, String> investProjectStringCellDataFeatures) {
                return investProjectStringCellDataFeatures.getValue().aboutProjectProperty();
            }
        });


        stageProjectName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return stageProjectStringCellDataFeatures.getValue().nameStageProperty();
            }
        });

        stageProjectUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return stageProjectStringCellDataFeatures.getValue().userProperty().getValue().fullNameProperty();
            }
        });

        stageProjectDateBeginPlan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().dateBeginPlanProperty().getValue().toString());
            }
        });

        stageProjectDateEndPlan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().dateEndPlanProperty().getValue().toString());
            }
        });

        stageProjectDateBeginUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().dateBeginUserProperty().getValue().toString());
            }
        });

        stageProjectDateEndUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().dateEndUserProperty().getValue().toString());
            }
        });

        stageProjectDateBeginProg.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().dateBeginProgProperty().getValue().toString());
            }
        });

        stageProjectDateEndProg.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().dateEndProgProperty().getValue().toString());
            }
        });

        stageProjectStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().statusStageProperty().toString());
            }
        });

        stageProjectCommentUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return stageProjectStringCellDataFeatures.getValue().commentUserProperty();
            }
        });

        nameStage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> param) {
                return param.getValue().getValue().nameStageProperty();
            }
        });

        idParent.setCellValueFactory(value -> value.getValue().getValue().nameStageProperty());


        investProjectTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvestProject>() {
            @Override
            public void changed(ObservableValue<? extends InvestProject> observableValue, InvestProject prev, InvestProject t1) {
                try {
                    List<StageProject> list = t1.getProjectList();
                    //stageProjectTableView.setItems(FXCollections.observableList(list));

                    TreeItem<StageProject> root = new TreeItem();
                    root.setValue(new StageProject());
                    treeTableViewStage.setRoot(root);
                    for (StageProject stage : list) {
                        TreeItem<StageProject> stageRoot = new TreeItem<StageProject>();
                        stageRoot.setValue(stage);
                        if (stage.getSubStage() != null) {
                            for (StageProject sub : stage.getSubStage()) {
                                TreeItem<StageProject> subStage = new TreeItem<StageProject>(sub);
                                stageRoot.getChildren().add(subStage);
                            }
                        }
                        //stageRoot.getChildren().addAll(stage.getSubStage())
                        root.getChildren().add(stageRoot);


                    }
//                    prev.setProjectList(new ArrayList());
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                //System.out.println(t1.getFullName());
            }
        });

    }

    public void setMainApp() {
        InvestProjectDAO investProjectDAO = new InvestProjectDAO();
        //ObservableList<User> list = FXCollections.observableList(userDAO.getAll());
        try {
            investProjectTableView.setItems(FXCollections.observableArrayList(investProjectDAO.getAll()));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void dialogIvestProject(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/investProjectDialog.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Создание проекта");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(investProjectTableView.getScene().getWindow());
        //stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
        stage.show();
    }

    public void dialogEditInvestProject() throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/investProjectDialog.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        ((InvestProjectDialogController)loader.getController()).setInvestProject(investProjectTableView.getSelectionModel().getSelectedItem());
        stage.setScene(scene);
        //((InvestProjectDialogController)loader.getController()).setUserData(investProjectTableView.getSelectionModel().getSelectedItem());
        stage.setTitle("Изменение проекта");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.initOwner(investProjectTableView.getScene().getWindow());
        //stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
        stage.show();
    }
}
