package earth;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.management.Query.lt;
import static javax.swing.text.StyleConstants.Background;

public class earth extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        //root.getChildren().add(btn);
        Scene scene = new Scene(root, 1000, 1000);
        PerspectiveCamera camera = new PerspectiveCamera(true);

        //Backs the camera away from the scene by 500 units
        camera.setTranslateZ(-500);
        camera.setTranslateX(500);
        camera.setTranslateY(500);
        //This is the range of which the camera will render objects
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);

        //The default field of view for the scene is 30 but change to suit
        camera.setFieldOfView(100);
        scene.setCamera(camera);

        //setting background
        Image stars = new Image("file:stars.jpg");
        BackgroundImage backgroundimage = new BackgroundImage(stars, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        root.setBackground(background);

        //set astronout image
        Image astronaut = new Image("file:astronaut.png");
        ImageView imageview = new ImageView(astronaut);
        imageview.setFitHeight(70);
        imageview.setFitWidth(60);
        imageview.setTranslateX(-450);
        imageview.setTranslateY(450);

        root.getChildren().add(imageview);

        //make astronoaut spin
        RotateTransition rt = new RotateTransition(Duration.millis(8500), imageview);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();

        //move astronaut
        Path path = new Path();
        path.getElements().add(new MoveTo(20, 20));
        path.getElements().add(new CubicCurveTo(-450, -350, 400, -500, 480, 200));
        path.getElements().add(new CubicCurveTo(450, 350, -400, 500, -480, -200));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setPath(path);
        pathTransition.setNode(imageview);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

//This sets up my sphere
        Sphere mysphere = new Sphere(200);
        mysphere.setTranslateX(0);
        mysphere.setTranslateY(0);
        mysphere.setTranslateZ(150);
        root.getChildren().add(mysphere);

        //This sets up the image of the earth to wrap around my sphere
        Image earthImage = new Image("file:earth.jpg");
        PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        mysphere.setMaterial(earthPhong);

        //This rotates my sphere
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(mysphere);
        rotate.setDuration(Duration.millis(7000));
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setByAngle(-360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
