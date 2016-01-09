package ru.medyannikov.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ru.medyannikov.application.Main;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.InvestProjectDAO;
import ru.medyannikov.model.InvestProject;
import ru.medyannikov.model.StageProject;

import java.io.IOException;
import java.util.Date;
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
    private TreeTableView<StageProject> stageProjectTableView;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectName;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectUser;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectDateBeginPlan;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectDateEndPlan;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectDateBeginUser;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectDateEndUser;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectDateBeginProg;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectDateEndProg;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectStatus;
    @FXML
    private TreeTableColumn<StageProject, String> stageProjectCommentUser;

    @FXML
    private ContextMenu investProjectMenu;
    @FXML
    private MenuItem addInvestProject;
    @FXML
    private MenuItem editInvestProject;

    @FXML
    private ContextMenu stageMenu;
    @FXML
    private MenuItem stageAdd;
    @FXML
    private MenuItem stageAddSub;
    @FXML
    private MenuItem stageEdit;
    @FXML
    private MenuItem stageDelete;

    @FXML
    private Button buttonOpen;

    @FXML
    private InvestProject investProject;

    @FXML
    private TreeTableView<StageProject> stageProjectTreeTableView;
    @FXML
    private TreeTableColumn<StageProject, String> nameStage;
    @FXML
    private TreeTableColumn<StageProject, String> idParent;

    private Stage stage;


    public InvestProjectFormController() {
    }

    private Main mainApp;

    @FXML
    private void initialize(){

        stageProjectTreeTableView.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> c) {
                if (stageProjectTreeTableView.getSelectionModel().getSelectedIndex() == -1){
                    stageDelete.setDisable(true);
                    stageAddSub.setDisable(true);
                    stageEdit.setDisable(true);
                }else if (stageProjectTreeTableView.getSelectionModel().getSelectedItem().getValue().getIdParentStage() == 0){
                    stageDelete.setDisable(true);
                    stageAddSub.setDisable(false);
                    stageEdit.setDisable(false);
                    stageDelete.setDisable(false);
                } else {
                    stageDelete.setDisable(false);
                    stageAddSub.setDisable(true);
                    stageEdit.setDisable(false);
                    stageDelete.setDisable(false);
                }

            }
        });

        investProjectTableView.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Integer> change) {
                if (investProjectTableView.getSelectionModel().getSelectedItem().getDateBegin().compareTo(new Date()) == 1){
                    investProjectMenu.getItems().get(0).setDisable(true);
                }
                else
                {
                    investProjectMenu.getItems().get(0).setDisable(false);
                }
            }
        });
        investProjectTableView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                 //   investProjectTableView.getContextMenu().getItems().get(0).setDisable(false);
                //System.out.print(event.toString());
            }
        });

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


        stageProjectName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return stageProjectStringCellDataFeatures.getValue().getValue().nameStageProperty();
            }
        });

        stageProjectUser.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return stageProjectStringCellDataFeatures.getValue().getValue().userProperty().getValue().fullNameProperty();
            }
        });

        stageProjectDateBeginPlan.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().dateBeginPlanProperty().getValue().toString());
            }
        });

        stageProjectDateEndPlan.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().dateEndPlanProperty().getValue().toString());
            }
        });

        stageProjectDateBeginUser.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().dateBeginUserProperty().getValue().toString());
            }
        });

        stageProjectDateEndUser.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().dateEndUserProperty().getValue().toString());
            }
        });

        stageProjectDateBeginProg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().dateBeginProgProperty().getValue().toString());
            }
        });

        stageProjectDateEndProg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().dateEndProgProperty().getValue().toString());
            }
        });

        stageProjectStatus.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return new SimpleStringProperty(stageProjectStringCellDataFeatures.getValue().getValue().statusStageProperty().toString());
            }
        });

        stageProjectCommentUser.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StageProject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StageProject, String> stageProjectStringCellDataFeatures) {
                return stageProjectStringCellDataFeatures.getValue().getValue().commentUserProperty();
            }
        });

        investProjectTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvestProject>() {
            @Override
            public void changed(ObservableValue<? extends InvestProject> observableValue, InvestProject prev, InvestProject t1) {
                try {
                    List<StageProject> list = t1.getProjectList();
                    TreeItem<StageProject> root = new TreeItem();
                    root.setValue(new StageProject());
                    stageProjectTreeTableView.setRoot(root);
                    for (StageProject stage : list) {
                        TreeItem<StageProject> stageRoot = new TreeItem<StageProject>();
                        stageRoot.setValue(stage);
                        if (stage.getSubStage() != null) {
                            for (StageProject sub : stage.getSubStage()) {
                                TreeItem<StageProject> subStage = new TreeItem<StageProject>(sub);
                                stageRoot.getChildren().add(subStage);
                            }
                        }
                        root.getChildren().add(stageRoot);
                    }
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setMainApp() {
        InvestProjectDAO investProjectDAO = new InvestProjectDAO();
        try {
            investProjectTableView.setItems(FXCollections.observableArrayList(investProjectDAO.getAll()));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void dialogIvestProject(ActionEvent event) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/investProjectDialog.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Создание проекта");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(investProjectTableView.getScene().getWindow());
        //stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
        stage.showAndWait();
        investProjectTableView.getItems().add(((InvestProjectDialogController)loader.getController()).getInvestProject());
        investProjectTableView.refresh();
    }

    public void dialogEditInvestProject() throws IOException{
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/investProjectDialog.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        InvestProject investProject = investProjectTableView.getSelectionModel().getSelectedItem();
        int index = investProjectTableView.getItems().indexOf(investProject);
        ((InvestProjectDialogController)loader.getController()).setInvestProject(investProjectTableView.getSelectionModel().getSelectedItem());
        stage.setScene(scene);
        //((InvestProjectDialogController)loader.getController()).setUserData(investProjectTableView.getSelectionModel().getSelectedItem());
        stage.setTitle("Изменение проекта");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.initOwner(investProjectTableView.getScene().getWindow());
        //stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
        stage.showAndWait();
        investProjectTableView.refresh();
    }



    public void dialogAddStage() throws IOException
    {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/stageProjectDialog.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        InvestProject investProject = investProjectTableView.getSelectionModel().getSelectedItem();
        int index = investProjectTableView.getItems().indexOf(investProject);
        //((InvestProjectDialogController)loader.getController()).setInvestProject(investProjectTableView.getSelectionModel().getSelectedItem());
        stage.setScene(scene);
        //((InvestProjectDialogController)loader.getController()).setUserData(investProjectTableView.getSelectionModel().getSelectedItem());
        stage.setTitle("Добавление нового этапа");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.initOwner(investProjectTableView.getScene().getWindow());
        //stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
        stage.showAndWait();
        investProjectTableView.refresh();
    }

    public void dialogAddSubStage(){

    }

    public void dialogEditStage(){

    }

    public void dialogDeleteStage(){

    }
}
