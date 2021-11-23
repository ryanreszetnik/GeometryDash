package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class Main extends Application {
	static double jumpvel = 0;
	boolean jump = true;
	static int xvel = 0;
	static boolean lose = false;
	static boolean onbox = false;
	static int boxnum = 0;
	int numofobsticles = 20;
	int numofboxes = 20;
	static ArrayList obsticles = new ArrayList(10);
	static ArrayList boxes = new ArrayList(10);
	static int playersize = 25;
	static Rectangle player = new Rectangle(playersize, playersize);
	static ArrayList overbox = new ArrayList(10);
	static boolean onplatform = false;
	static int currentbox = -1;

	@Override

	public void start(Stage primaryStage) {
		try {

			Rectangle ground = new Rectangle(400, 10);
			ground.setTranslateY(390);

			player.setTranslateY(365);
			player.setTranslateX(10);
			player.setFill(Color.RED);

			// Obstacles

			for (int i = 0; i < numofobsticles; i++) {
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(new Double[] { 0.0, 0.0, 25.0, 0.0, 12.5, -24.9 });
				triangle.setTranslateY(390);
				triangle.setTranslateX(250 * (i + 1));
				obsticles.add(triangle);
			}
			for (int i = 0; i < numofboxes; i++) {
				Rectangle square = new Rectangle(25, 25);
				square.setTranslateY(365);
				square.setTranslateX(250 * (i + 1) + 25);
				boxes.add(square);
				overbox.add(false);
			}

			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					if (lose == false) {
						move();
						onplatform = false;
						boolean test = false;
						for (int i = 0; i < boxes.size(); i++) {
							Rectangle temp = new Rectangle();
							temp = (Rectangle) boxes.get(i);
							
							
							if (player.getTranslateY()+ playersize > temp.getTranslateY()
									&& ball.getTranslateY() + ysize / 2 + ballrad <= recstarty[i][p]
									&& platform[i][p].getTranslateX() + recstartx[i][p] < balldistance
									&& platform[i][p].getTranslateX() + recstartx[i][p]
											+ platform[i][p].getWidth() > balldistance
									&& vel > 0) {
							
							
							
							System.out.println(onplatform +""+ i);
							if (player.getTranslateX() > temp.getTranslateX() - 25
									&& player.getTranslateX() < temp.getTranslateX() + 25) {
								currentbox = i;
								
								if (temp.getTranslateY() <= player.getTranslateY() - (jumpvel)
										&& temp.getTranslateY() >= player.getTranslateY()) {
									onplatform = true;
									player.setTranslateY(temp.getTranslateY() - 25);
									jumpvel = 0;
									jump = true;
									test = true;
									break;
								}

							}

						}
						if (onplatform == false) {
							player.setTranslateY(player.getTranslateY() - jumpvel);
						}
						if (jumpvel > -10 && jump == false && onplatform == false) {
							jumpvel -= 0.75;
						}

						if (player.getTranslateY() >= 365 && onplatform == false) {
							player.setTranslateY(365);
							jump = true;
						}
						

					}

				}
			};

			Pane root = new Pane();
			

			Scene scene = new Scene(root, 400, 400);
			root.getChildren().addAll(ground, player);
			for (int i = 0; i < obsticles.size(); i++) {
				root.getChildren().add((Polygon) (obsticles.get(i)));
			}
			for (int i = 0; i < boxes.size(); i++) {
				root.getChildren().add((Rectangle) (boxes.get(i)));
			}
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			timer.start();
			scene.setOnKeyPressed(e -> {
				switch (e.getCode()) {
				case UP:
					if (jump == true) {
						jumpvel = 10;
						jump = false;
					}
					break;
				case RIGHT:
					xvel = -7;
					break;
				case LEFT:
					xvel = 7;
					break;
				case R:
					rotate(1);

				}
			});
			scene.setOnKeyReleased(e -> {
				switch (e.getCode()) {
				case RIGHT:

				case LEFT:
					xvel = 0;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void rotate(int x) {
		Polygon polygon = (Polygon) obsticles.get(x);
		polygon.getPoints().setAll(new Double[] { 0.0, -12.5, 25.0, 0.0, 25.0, -25.0 });
		obsticles.set(x, polygon);
	}

	public static void move() {
		for (int i = 0; i < obsticles.size(); i++) {
			Polygon temp = new Polygon();
			temp = (Polygon) obsticles.get(i);
			temp.setTranslateX(temp.getTranslateX() + xvel);
			obsticles.set(i, temp);
		}
		for (int i = 0; i < boxes.size(); i++) {
			Rectangle temp = new Rectangle();
			temp = (Rectangle) boxes.get(i);
			temp.setTranslateX(temp.getTranslateX() + xvel);
			boxes.set(i, temp);
		}
		for (int i = 0; i < obsticles.size(); i++) {
			Polygon temp = new Polygon();
			temp = (Polygon) obsticles.get(i);
			if (temp.getBoundsInParent().intersects(player.getBoundsInParent())) {
				lose = true;
			}
		}

	}
	
	
}
